package com.company.Warehouse;

import java.util.EnumMap;
import java.util.Map;

public class Warehouse {
    private Map<Material, Integer> materialMap = new EnumMap<>(Material.class);

    private int get(Object key) {
        return materialMap.get(key) == null ? 0 : materialMap.get(key);
    }

    public void addMaterial(Material key, Integer value) {
        System.out.println("======");
        int maxCapacity = key.getMaxCapacity();
        int current = get(key);
        int availableCapacity = maxCapacity - current;
        String materialName = key.getName();
        if (availableCapacity > value) {
            System.out.println("INSERTING " + value + " UNITS OF " + materialName);
            System.out.println("FREE STORAGE LEFT FOR " + materialName + ": " + (availableCapacity - value) + "/" + maxCapacity);
            if (materialMap.containsKey(key)) {
                materialMap.put(key, current + value);
            } else {
                materialMap.put(key, value);
            }
        } else {
            System.out.println("FAILED TO INSERT " + value + " UNITS OF " + materialName);
            System.out.println("MAX CAPACITY FOR " + materialName + " IS: " + maxCapacity);
            System.out.println("FREE STORAGE LEFT FOR " + materialName + ": " + availableCapacity + " UNITS");
        }
    }

    public void removeMaterial(Material key, Integer value) {
        System.out.println("======");
        int maxCapacity = key.getMaxCapacity();
        int current = get(key);
        int availableCapacity = maxCapacity - current;
        String materialName = key.getName();
        if (materialMap.containsKey(key)) {
            if (current >= value) {
                System.out.println("REMOVING " + value + " UNITS OF " + materialName);
                System.out.println("FREE STORAGE LEFT FOR " + materialName + ": " + (availableCapacity + value) + "/" + maxCapacity);
                materialMap.put(key, current - value);
            } else {
                System.out.println("FAILED TO REMOVE " + value + " UNITS OF " + materialName);
                System.out.println("CANNOT REMOVE MORE THAN AVAILABLE");
                System.out.println("CURRENT BALANCE OF " + materialName + " IS : " + current + "/" + maxCapacity);
            }
        }
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "materialMap=" + materialMap +
                '}';
    }
}
