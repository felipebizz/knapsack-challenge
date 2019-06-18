package com.mobiquityinc.packer.strategy;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Solution;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * This class will implement the Greedy solution: Form a price density array, sort it in non-ascending order,
 * and use a greedy strategy to fit the greatest value into the knapsack.
 */
public class KnapsackGreedy implements Strategy {

    private static final int CAPACITY = 50;
    private static Logger logger = Logger.getLogger(KnapsackGreedy.class);

    public static void main(String[] args) {

        KnapsackGreedy knapsackGreedy = new KnapsackGreedy();

        Item[] items = {new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)};

        float timeInSeconds = knapsackGreedy.timeToFind(items);
        logger.info("Time to Run Greedy Algorithm : " + timeInSeconds + " seconds");
    }

    private static int comparePackageValues(Item kPackA, Item kPackB) {
        return kPackB.getCost().compareTo(kPackA.getCost());
    }

    public Solution execute(Item[] items, double capacity) {

        Arrays.sort(items, KnapsackGreedy::comparePackageValues);

        double remainingWeight = capacity;
        double packageValue = 0d;

        int i = 0;
        boolean stopProcess = false;
        while (!stopProcess) {
            if (items[i].getWeight() <= remainingWeight) {
                remainingWeight -= items[i].getWeight();
                packageValue += items[i].getValue();

                logger.info("Package: " + i + " - Weight " + items[i].getWeight() + " - Value: " + items[i].getValue());
                i++;
            }

            if (items[i].getWeight() > remainingWeight) {
                stopProcess = true;
            }
        }

        logger.info("Max Value: £ \t" + packageValue);
        return new Solution(Arrays.asList(items), packageValue, capacity);
    }

    private float timeToFind(Item[] items) {
        long start = System.currentTimeMillis();
        execute(items, CAPACITY);
        long end = System.currentTimeMillis();
        return (end - start) / 1000f;
    }
}