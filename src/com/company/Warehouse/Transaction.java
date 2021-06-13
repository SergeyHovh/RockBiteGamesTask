package com.company.Warehouse;

public class Transaction {
    private Warehouse from, to;
    private Material material;
    private int quantity;

    public Transaction(Warehouse from, Warehouse to, Material material, int quantity) {
        this.from = from;
        this.to = to;
        this.material = material;
        this.quantity = quantity;
    }

    public Transaction(Warehouse from, Warehouse to) {
        this.from = from;
        this.to = to;
    }

    public Transaction() {
    }

    public void execute(Material material, int quantity) {
        // basic checks
        assert from != null;
        assert to != null;
        assert material != null;
        assert quantity > 0;

        if (from.exists(material)) {
            if (from.removeMaterial(material, quantity) && to.addMaterial(material, quantity)) {
                System.out.println("SUCCESSFULLY TRANSFERRED " + quantity + " UNITS OF " + material.getName() + " FROM: " + from.getName() + " TO: " + to.getName());
            } else {
                System.out.println("TRANSACTION FAILED");
            }
        } else {
            System.out.println("------");
            System.out.println("MATERIAL: " + material.getName() + " IS NOT AVAILABLE FOR TRANSACTION");
            System.out.println("------");
        }
    }

    public void execute() {
        execute(material, quantity);
    }

    public Warehouse getFrom() {
        return from;
    }

    public void setFrom(Warehouse from) {
        this.from = from;
    }

    public Warehouse getTo() {
        return to;
    }

    public void setTo(Warehouse to) {
        this.to = to;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
