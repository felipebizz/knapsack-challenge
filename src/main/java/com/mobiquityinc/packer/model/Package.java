package com.mobiquityinc.packer.model;

import java.util.ArrayList;
import java.util.List;

public class Package {

    private double totalWeight;
    private List<Item> items;

    public Package(double _totalWeight) {
        this.totalWeight = _totalWeight;
        this.items = new ArrayList<>();
    }

    public double getTotalWeight() {
        return totalWeight;
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItems(Item item) {
        this.items.add(item);
    }
}
