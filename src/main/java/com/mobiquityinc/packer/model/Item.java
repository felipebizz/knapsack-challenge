package com.mobiquityinc.packer.model;

public class Item {

    private int value;
    private Double weight;
    private int index;
    private Double cost;

    public Item(int _index, double _weight, int _value) {
        this.index = _index;
        this.value = _value;
        this.weight = _weight;
    }

    public Item(int _value, double _weight) {
        this.value = _value;
        this.weight = _weight;
        this.cost = (double) value / weight;
    }

    public int getValue() {
        return value;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getCost() {
        return cost;
    }

    public int getIndex() {
        return index;
    }


    public String toString() {
        return index + " [ value = £" + value + ", weight = " + weight + " Kg]";
    }
}
