package com.mobiquityinc.packer.test;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Solution;
import com.mobiquityinc.packer.strategy.KnapsackDynamic;
import junit.framework.TestCase;
import org.junit.Test;

public class KnapsackDynamicTest {

    @Test
    public void test_DynamicAlgorithm() {

        Item[] items = {new Item(1, 85.31, 29),
                new Item(2, 14.55, 74),
                new Item(3, 3.98, 16),
                new Item(4, 26.24, 55),
                new Item(5, 63.69, 52)};

        KnapsackDynamic knapsackDynamic = new KnapsackDynamic();
        Solution solution = knapsackDynamic.execute(items, 75);
        TestCase.assertEquals("Equals", solution.getTotalValuePackage(), 145, 0.0);

    }
}
