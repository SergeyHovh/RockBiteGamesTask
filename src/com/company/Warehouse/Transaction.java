package com.company.Warehouse;

public class Transaction {
    private Warehouse from, to;
    private Material material;
    private int quantity;

    public Transaction(Warehouse from, Warehouse to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Call this method to start the transaction from one warehouse to another
     * <pre>
     * Transaction transaction = new Transaction(A, B);
     * transaction.execute(material, quantity);
     * </pre>
     *
     * @param material material type to be transferred from one warehouse to another
     * @param quantity material quantity to be transferred
     */
    public void execute(Material material, int quantity) {
        // basic checks
        assert from != null;
        assert to != null;
        assert material != null;
        assert quantity > 0;

        if (from.exists(material)) {
            int availableCapacity = to.getAvailableCapacity(material);
            if (availableCapacity == 0) {
                System.out.println("------");
                System.out.println("\tWAREHOUSE " + to.getName() + " DOES NOT HAVE ANY CAPACITY FOR MATERIAL " + material.getName());
                System.out.println("\tTHE TRANSACTION WILL BE TERMINATED");
                System.out.println("------");
                return;
            } else if (quantity > availableCapacity) {
                System.out.println("------");
                System.out.println("\tWAREHOUSE " + to.getName() + " HAS AVAILABLE CAPACITY OF " + availableCapacity + " UNITS FOR MATERIAL " + material.getName());
                System.out.println("\tTHE TRANSACTION WILL BE EXECUTED PARTIALLY");
                System.out.println("------");
                quantity = availableCapacity;
            }
            if (from.removeMaterial(material, quantity) && to.addMaterial(material, quantity)) {
                System.out.println("SUCCESSFULLY TRANSFERRED " + quantity + " UNITS OF " + material.getName() + " FROM: " + from.getName() + " TO: " + to.getName());
            } else {
                System.out.println("\tTRANSACTION FAILED");
            }
        } else {
            System.out.println("------");
            System.out.println("\tMATERIAL: " + material.getName() + " IS NOT AVAILABLE FOR TRANSACTION");
            System.out.println("------");
        }
    }

    /**
     * Use this method to start a transaction if the material and quantity are initially provided using setter methods.
     * <pre>
     * Transaction transaction = new Transaction(A, B);
     * transaction.setMaterial(material);
     * transaction.setQuantity(quantity);
     * transaction.execute();
     * </pre>
     */
    public void execute() {
        execute(material, quantity);
    }

    // getters and setters
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
