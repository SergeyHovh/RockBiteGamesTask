package com.company.Warehouse;

import java.util.ArrayList;
import java.util.List;

public class Player {
    List<Warehouse> warehouses = new ArrayList<>();

    public static void main(String[] args) {
        Warehouse A = new Warehouse("A");
        Warehouse B = new Warehouse("B");
        A.addMaterial(Material.IRON, 100);
        A.addMaterial(Material.IRON, 100);
        A.addMaterial(Material.IRON, 100);
        A.addMaterial(Material.IRON, 500);
        A.addMaterial(Material.COPPER, 150);
        A.removeMaterial(Material.IRON, 50);
        A.removeMaterial(Material.COPPER, 250);
        System.out.println(A);

        Transaction transaction = new Transaction(A, B);
        transaction.setMaterial(Material.IRON);
        transaction.setQuantity(100);
        transaction.execute(Material.COPPER, 15);

        System.out.println(A);
        System.out.println(B);
    }
}
