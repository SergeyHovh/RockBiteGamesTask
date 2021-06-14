package com.company.ClickerMaths;

public class Main {

    public static void main(String[] args) {
        CrazyBigNumber a = new CrazyBigNumber("1.2 M");
        CrazyBigNumber b = new CrazyBigNumber("155 K");
        System.out.print("1.2 M - 155 K = ");
        System.out.println(CrazyBigNumber.diff(a, b));
        System.out.print("1.2 M + 155 K = ");
        System.out.println(CrazyBigNumber.add(a, b));
        System.out.print("1.2 M * 155 K = ");
        System.out.println(CrazyBigNumber.mult(a, b));
        System.out.print("1.2 M * 15 = ");
        System.out.println(CrazyBigNumber.mult(a, 15));
        System.out.print("155 K * 20 = ");
        System.out.println(CrazyBigNumber.mult(b, 20));
    }
}
