package com.company.Warehouse;

public enum Material {
    IRON("iron", "base metal", "path/to/iron_icon.png", 420),
    COPPER("copper", "base metal", "path/to/copper_icon.png", 750),
    BOLT("bolt", "complex material", "path/to/bolt_icon.png", 1000),
    CARBON("carbon", "base nonmetallic", "path/to/carbon_icon.png", 1500),
    STEEL("steel", "complex metal", "path/to/steel_icon.png", 500),
    SILICON("silicon", "base metalloid", "path/to/silicon_icon.png", 200),
    SULFUR("sulfur", "base nonmetallic", "path/to/sulfur_icon.png", 300);

    private String name;
    private String description;
    private String icon;
    private int maxCapacity;

    Material(String name, String description, String icon, int maxCapacity) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.maxCapacity = maxCapacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
