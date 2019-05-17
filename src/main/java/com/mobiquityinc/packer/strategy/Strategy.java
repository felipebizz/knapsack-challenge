package com.mobiquityinc.packer.strategy;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Solution;

public interface Strategy {

    Solution execute(Item[] items, double capacity);
}
