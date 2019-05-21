package com.mobiquityinc.packer.model;

import java.util.List;

public class Solution {

    private List<Item> items;
    private Double totalValuePackage;
    private Double maxWeightPackage;

    public Solution(List<Item> _items, double _totalValuePackage, double _maxWeightPackage) {
        this.items = _items;
        this.totalValuePackage = _totalValuePackage;
        this.maxWeightPackage = _maxWeightPackage;
    }

    public Double getTotalValuePackage() {
        return totalValuePackage;
    }

    public void display() {
        if (items != null && !items.isEmpty()) {
            System.out.println("\n-------------------------");
            System.out.println("Max Package Size : " + maxWeightPackage + " KG");
            System.out.println("Items to pick :");

            for (Item item : items) {
                System.out.println("- " + item.toString());
            }
            System.out.println("Total Package Value = " + totalValuePackage);
        }
    }

    public void buildReturn(StringBuilder result) {
        if (items != null && !items.isEmpty()) {
            result.append("-").append("\n");
            for (Item item : items)
                result.append(item.getIndex()).append(",");
        }
        String noComma = result.substring(0, result.length() - 1);
        result.replace(0, result.length(), noComma);
        result.append("\n");
    }
}
