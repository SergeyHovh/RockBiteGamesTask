package com.company.Warehouse;

public class Player {

    public static void main(String[] args) {
        Warehouse A = new Warehouse("a");
        Warehouse B = new Warehouse("b");
        A.addMaterial(Material.IRON, 400);
        B.addMaterial(Material.IRON, 400);
        Transaction transactionAtoB = new Transaction(A, B);
        transactionAtoB.execute(Material.IRON, 50);
        A.addMaterial(Material.COPPER, 150);
        A.removeMaterial(Material.COPPER, 250);
        System.out.println(A);

        transactionAtoB.setMaterial(Material.BOLT);
        transactionAtoB.setQuantity(100);
        transactionAtoB.execute();
        transactionAtoB.execute(Material.COPPER, 15);

        System.out.println(A);
        System.out.println(B);
    }
}
