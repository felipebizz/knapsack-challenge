package com.mobiquityinc.packer.strategy;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Solution;

import java.util.Arrays;

/**
 * This class will implement the Greedy solution: Form a price density array, sort it in non-ascending order,
 * and use a greedy strategy to fit the greatest value into the knapsack.
 */
public class KnapsackGreedy implements Strategy {

    public static void main(String[] args) {
        KnapsackGreedy knapsackGreedy = new KnapsackGreedy();

        Item[] items = {new Item(4, 12),
                new Item(2, 2),
                new Item(1, 1),
                new Item(2, 1),
                new Item(10, 4)};


        Item[] items2 = {new Item(60, 10),
                new Item(100, 20),
                new Item(120, 30)};

        //float timeInSeconds = knapsackGreedy.TimeToFind(items, 15);
        float timeInSeconds = knapsackGreedy.TimeToFind(items2, 50);
        System.out.println("Time to Run Greedy Algorithm : " + timeInSeconds + " seconds");
    }

    public Solution execute(Item[] items, double capacity) {

        Arrays.sort(items, (kPackA, kPackB) -> kPackB.getCost().compareTo(kPackA.getCost()));

        double remainingWeight = capacity;
        double packageValue = 0d;

        int i = 0;
        boolean stopProc = false;
        while (!stopProc) {
            if (items[i].getWeight() <= remainingWeight) {
                remainingWeight -= items[i].getWeight();
                packageValue += items[i].getValue();

                System.out.println("Package: " + i + " - Weight " + items[i].getWeight() + " - Value: " + items[i].getValue());
                i++;
            }

            if (items[i].getWeight() > remainingWeight) {
                stopProc = true;
            }
        }

        System.out.println("Max Value: £ \t" + packageValue);
        return new Solution(Arrays.asList(items), packageValue, capacity);
    }

    private float TimeToFind(Item[] items, int capacity) {
        long start = System.currentTimeMillis();
        execute(items, capacity);
        long end = System.currentTimeMillis();
        float duration = (end - start) / 1000f;
        return duration;
    }
}