package com.mobiquityinc.packer;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Solution;
import com.mobiquityinc.packer.strategy.Strategy;

public class Knapsack {

    private final Strategy strategy;

    public Knapsack(final Strategy _strategy) {
        this.strategy = _strategy;
    }

    public Solution executeStrategy(Item[] items, double capacity) {
        return this.strategy.execute(items, capacity);
    }
}
