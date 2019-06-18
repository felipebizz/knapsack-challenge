package com.mobiquityinc.packer.model;

public class Item {

    private double value;
    private double weight;
    private int index;
    private double cost;

    public Item(int _index, double _weight, double _value) {
        this.index = _index;
        this.value = _value;
        this.weight = _weight;
        this.cost = value / weight;
    }

    public Item(double _value, double _weight) {
        this.value = _value;
        this.weight = _weight;
        this.cost = value / weight;
    }

    public double getValue() {
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
