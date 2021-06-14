package com.company.Warehouse;

import java.util.EnumMap;
import java.util.Map;

public class Warehouse {
    private final Map<Material, Integer> materialMap = new EnumMap<>(Material.class) {
        @Override
        public String toString() {
            StringBuilder stb = new StringBuilder();
            stb.append("{");
            for (Map.Entry<Material, Integer> entry : this.entrySet()) {
                stb.append("\n\t\t").append(entry.getKey()).append(": ")
                        .append(entry.getValue());
            }
            stb.append("\n\t}");
            return stb.toString();
        }
    };
    private final String name;

    public Warehouse(String name) {
        this.name = name;
    }

    /**
     * @param material material type
     * @return check if the material is present in this warehouse
     */
    public boolean exists(Material material) {
        return materialMap.containsKey(material);
    }

    /**
     * @param material material type
     * @return current balance of give material in this warehouse
     */
    public int lookUp(Material material) {
        return materialMap.get(material) == null ? 0 : materialMap.get(material);
    }

    /**
     * @param material material type
     * @return available capacity of given material in this warehouse
     */
    public int getAvailableCapacity(Material material) {
        return material.getMaxCapacity() - lookUp(material);
    }

    /**
     * @param material material type to be added to this warehouse
     * @param quantity quantity of the material to be added to this warehouse
     * @return true if the operation was successful
     */
    public boolean addMaterial(Material material, int quantity) {
        System.out.println("======");
        int maxCapacity = material.getMaxCapacity();
        int current = lookUp(material);
        int availableCapacity = getAvailableCapacity(material);
        String materialName = material.getName();
        if (availableCapacity >= quantity) {
            System.out.println("INSERTING " + quantity + " UNITS OF " + materialName + " TO: " + name);
            System.out.println("CURRENT BALANCE OF " + materialName + " IN WAREHOUSE " + name + " IS : " + (current + quantity) + "/" + maxCapacity);
            if (materialMap.containsKey(material)) {
                materialMap.put(material, current + quantity);
            } else {
                materialMap.put(material, quantity);
            }
            return true;
        } else {
            System.out.println("------");
            System.out.println("\tFAILED TO INSERT " + quantity + " UNITS OF " + materialName + " TO: " + name);
            System.out.println("\tMAX CAPACITY FOR " + materialName + " IS: " + maxCapacity);
            System.out.println("\tCURRENT BALANCE OF " + materialName + " IN WAREHOUSE " + name + " IS : " + current + "/" + maxCapacity);
            System.out.println("------");
            return false;
        }
    }

    /**
     * @param material material type to be removed from this warehouse
     * @param quantity quantity of the material to be removed from this warehouse
     * @return true if the operation was successful
     */
    public boolean removeMaterial(Material material, int quantity) {
        System.out.println("======");
        int maxCapacity = material.getMaxCapacity();
        int current = lookUp(material);
        String materialName = material.getName();
        if (materialMap.containsKey(material)) {
            if (current >= quantity) {
                System.out.println("REMOVING " + quantity + " UNITS OF " + materialName + " FROM: " + name);
                System.out.println("CURRENT BALANCE OF " + materialName + " IN WAREHOUSE " + name + " IS : " + (current - quantity) + "/" + maxCapacity);
                materialMap.put(material, current - quantity);
                return true;
            } else {
                System.out.println("------");
                System.out.println("\tFAILED TO REMOVE " + quantity + " UNITS OF " + materialName + " FROM: " + name);
                System.out.println("\tCANNOT REMOVE MORE THAN AVAILABLE");
                System.out.println("\tCURRENT BALANCE OF " + materialName + " IN WAREHOUSE " + name + " IS : " + current + "/" + maxCapacity);
                System.out.println("------");
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Warehouse {" +
                "\n\tName: " + name +
                "\n\tMaterials: " + materialMap +
                "\n}";
    }

    public String getName() {
        return name;
    }
}
