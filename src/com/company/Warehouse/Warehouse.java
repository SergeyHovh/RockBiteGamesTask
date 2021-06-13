package com.company.Warehouse;

import java.util.EnumMap;
import java.util.Map;

public class Warehouse {
    private Map<Material, Integer> materialMap = new EnumMap<>(Material.class);
    private String name;

    public Warehouse(String name) {
        this.name = name;
    }

    public boolean exists(Material material) {
        return materialMap.containsKey(material);
    }

    public int lookUp(Object key) {
        return materialMap.get(key) == null ? 0 : materialMap.get(key);
    }

    public boolean addMaterial(Material key, Integer value) {
        System.out.println("======");
        int maxCapacity = key.getMaxCapacity();
        int current = lookUp(key);
        int availableCapacity = maxCapacity - current;
        String materialName = key.getName();
        if (availableCapacity > value) {
            System.out.println("INSERTING " + value + " UNITS OF " + materialName + " TO: " + name);
            System.out.println("CURRENT BALANCE OF " + materialName + " IS : " + (current + value) + "/" + maxCapacity);
            if (materialMap.containsKey(key)) {
                materialMap.put(key, current + value);
            } else {
                materialMap.put(key, value);
            }
            return true;
        } else {
            System.out.println("------");
            System.out.println("FAILED TO INSERT " + value + " UNITS OF " + materialName + " TO: " + name);
            System.out.println("MAX CAPACITY FOR " + materialName + " IS: " + maxCapacity);
            System.out.println("CURRENT BALANCE OF " + materialName + " IS : " + current + "/" + maxCapacity);
            System.out.println("------");
            return false;
        }
    }

    public boolean removeMaterial(Material key, Integer value) {
        System.out.println("======");
        int maxCapacity = key.getMaxCapacity();
        int current = lookUp(key);
        String materialName = key.getName();
        if (materialMap.containsKey(key)) {
            if (current >= value) {
                System.out.println("REMOVING " + value + " UNITS OF " + materialName);
                System.out.println("CURRENT BALANCE OF " + materialName + " IS : " + (current - value) + "/" + maxCapacity);
                materialMap.put(key, current - value);
                return true;
            } else {
                System.out.println("------");
                System.out.println("FAILED TO REMOVE " + value + " UNITS OF " + materialName);
                System.out.println("CANNOT REMOVE MORE THAN AVAILABLE");
                System.out.println("CURRENT BALANCE OF " + materialName + " IS : " + current + "/" + maxCapacity);
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
