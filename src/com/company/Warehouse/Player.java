package com.company.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class Player {
    List<Warehouse> warehouses = new ArrayList<>();

    public static void main(String[] args) {
        Warehouse warehouse = new Warehouse();
        warehouse.addMaterial(Material.IRON, 100);
        warehouse.addMaterial(Material.IRON, 100);
        warehouse.addMaterial(Material.IRON, 100);
        warehouse.addMaterial(Material.IRON, 500);
        warehouse.addMaterial(Material.COPPER, 150);
        warehouse.removeMaterial(Material.IRON, 250);
        warehouse.removeMaterial(Material.COPPER, 250);
        System.out.println(warehouse);
    }
}
