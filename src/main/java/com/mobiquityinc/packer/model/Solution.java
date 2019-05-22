package com.mobiquityinc.packer.model;

import org.apache.log4j.Logger;

import java.util.List;

public class Solution {

    private static Logger logger = Logger.getLogger(Solution.class);
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
            logger.info("\n-------------------------");
            logger.info("Max Package Size : " + maxWeightPackage + " KG");
            logger.info("Items to pick :");

            for (Item item : items) {
                logger.info("- " + item.toString());
            }
            logger.info("Total Package Value = " + totalValuePackage);
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
