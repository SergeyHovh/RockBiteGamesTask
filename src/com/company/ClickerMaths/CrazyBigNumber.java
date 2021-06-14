package com.company.ClickerMaths;

import java.util.Objects;

public class CrazyBigNumber {
    private final char[] CRAZY_BIG_UNITS =
            {
                    ' ', 'K', 'M', 'B',
                    'T', 'q', 'Q', 's',
                    'S', 'O', 'N', 'd',
                    'U', 'D', '!', '@',
                    '#', '$', '%', '^',
                    '&', '*'
            };

    private double number;
    private int numberOfThousands;


    private CrazyBigNumber(double number, int numberOfThousands) {
        this.number = number;
        this.numberOfThousands = numberOfThousands;
    }

    /**
     * @param crazyBigNumber string representation of a crazy big number
     */
    public CrazyBigNumber(String crazyBigNumber) {
        crazyBigNumber = crazyBigNumber.replaceAll("\\s+", ""); // removing whitespaces
        String unit = crazyBigNumber.substring(crazyBigNumber.length() - 1);
        int unitId = findUnitId(unit);
        if (unitId == -1) {
            this.number = Double.parseDouble(crazyBigNumber);
            this.numberOfThousands = 0;

        } else {
            this.number = Double.parseDouble(crazyBigNumber.substring(0, crazyBigNumber.length() - 1));
            this.numberOfThousands = unitId;
        }
    }

    public static CrazyBigNumber add(CrazyBigNumber number1, CrazyBigNumber number2) {
        CrazyBigNumber res = new CrazyBigNumber(0, 0);
        int power1 = number1.getNumberOfThousands();
        int power2 = number2.getNumberOfThousands();
        if (power1 == power2) {
            double resultingNumber = number1.getNumber() + number2.getNumber();
            if (resultingNumber < 1000) {
                res.setNumber(resultingNumber);
                res.setNumberOfThousands(power1);
            } else {
                resultingNumber /= 1000;
                res.setNumber(resultingNumber);
                res.setNumberOfThousands(power1 + 1);
            }
        } else if (Math.abs(power1 - power2) == 1) {
            if(number1.isLess(number2)) {
                double resultingNumber = number2.getNumber() * 1000 + number1.getNumber();
                res.setNumber(resultingNumber / 1000);
                res.setNumberOfThousands(power2);
            } else {
                double resultingNumber = number1.getNumber() * 1000 + number2.getNumber();
                res.setNumber(resultingNumber / 1000);
                res.setNumberOfThousands(power1);
            }
        } else {
            res = getMax(number1, number2);
        }
        res.round();

        return res;
    }

    public static CrazyBigNumber diff(CrazyBigNumber number1, CrazyBigNumber number2) {
        CrazyBigNumber res = new CrazyBigNumber(0, 0);
        int power1 = number1.getNumberOfThousands();
        int power2 = number2.getNumberOfThousands();
        if (power1 == power2) {
            double resultingNumber = number1.getNumber() - number2.getNumber();
            if (resultingNumber > 0) {
                res.setNumber(resultingNumber);
                res.setNumberOfThousands(power1);
            } else { // if dealt damage is more than the health of the enemy it dies - value becomes 0
                res.setNumber(0);
                res.setNumberOfThousands(0);
            }
        } else if (power1 > power2) {
            if (power1 - power2 == 1) {
                double resultingNumber = number1.getNumber() * 1000 - number2.getNumber();
                if (resultingNumber < 1000) {
                    res.setNumber(resultingNumber);
                    res.setNumberOfThousands(power2);
                } else {
                    resultingNumber /= 1000;
                    res.setNumber(resultingNumber);
                    res.setNumberOfThousands(power1);
                }
            } else {
                res.setNumber(number1.getNumber());
                res.setNumberOfThousands(power1);
            }
        }

        if (res.getNumber() < 1.0) {
            while (res.getNumber() < 1.0 && res.getNumberOfThousands() > 0) {
                res.setNumber(res.getNumber() * 1000);
                res.setNumberOfThousands(res.getNumberOfThousands() - 1);
            }
        }
        res.round();

        return res;
    }

    public static CrazyBigNumber mult(CrazyBigNumber number1, CrazyBigNumber number2) {
        return mult(number1, number2.getNumber(), number2.getNumberOfThousands());
    }

    public static CrazyBigNumber mult(CrazyBigNumber number1, double number2) {
        return mult(number1, number2, 0);
    }

    /**
     * general method for multiplication
     */
    private static CrazyBigNumber mult(CrazyBigNumber number1, double number2, int power) {
        CrazyBigNumber res = new CrazyBigNumber(0, 0);
        double resultingNumber = number1.getNumber() * number2;
        int resultingPower = number1.getNumberOfThousands() + power;
        while (resultingNumber > 1000) {
            resultingNumber /= 1000;
            resultingPower += 1;
        }
        res.setNumber(resultingNumber);
        res.setNumberOfThousands(resultingPower);
        return res;
    }

    /**
     * @param number1 first crazy big number
     * @param number2 second crazy big number
     * @return maximum of two provided crazy big numbers
     */
    public static CrazyBigNumber getMax(CrazyBigNumber number1, CrazyBigNumber number2) {
        if (number1.getNumberOfThousands() == number2.getNumberOfThousands()) {
            return number1.getNumber() > number2.getNumber() ? number1 : number2;
        } else {
            return number1.getNumberOfThousands() > number2.getNumberOfThousands() ? number1 : number2;
        }
    }

    /**
     * @param number other Crazy big number to be compared to this one
     * @return true if this number is less than the other one
     */
    public boolean isLess(CrazyBigNumber number) {
        if (this.getNumberOfThousands() == number.getNumberOfThousands()) {
            return this.getNumber() < number.getNumber();
        } else {
            return this.getNumberOfThousands() < number.getNumberOfThousands();
        }
    }

    public int getNumberOfThousands() {
        return numberOfThousands;
    }

    public double getNumber() {
        return number;
    }

    public void round() {
        double scale = Math.pow(10, 3);
        number = Math.round(number * scale) / scale;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public void setNumberOfThousands(int numberOfThousands) {
        this.numberOfThousands = numberOfThousands;
    }

    @Override
    public String toString() {
        return number + " " + CRAZY_BIG_UNITS[numberOfThousands];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrazyBigNumber that = (CrazyBigNumber) o;
        return Double.compare(that.number, number) == 0 && numberOfThousands == that.numberOfThousands;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, numberOfThousands);
    }

    private int findUnitId(String unit) {
        for (int i = 0; i < CRAZY_BIG_UNITS.length; i++) {
            char crazyBigUnit = CRAZY_BIG_UNITS[i];
            if (unit.charAt(0) == crazyBigUnit) {
                return i;
            }
        }
        return -1;
    }
}
