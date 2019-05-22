package com.mobiquityinc.packer.strategy;

import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Knapsack Dynamic Algorithm
 *
 * <p>The algorithm below is an adaptation of the dynamic programming solution for the knapsack problem.
 * * The original algorithm considers only integer weights and is only looking for the highest cost (the number!)
 * * and not which items were summed to get to such cost.</p>
 */
public class KnapsackDynamic implements Strategy {

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    @Override
    public Solution execute(Item[] items, double capacity) {
        int i, j;
        int numberOfItems = items.length;
        int matrix[][] = new int[numberOfItems + 1][(int) capacity + 1];

        for (i = 0; i <= numberOfItems; i++) {
            for (j = 0; j <= capacity; j++) {
                if (i == 0 || j == 0)
                    matrix[i][j] = 0;
                else if (items[i - 1].getWeight() <= j)
                    matrix[i][j] = max(items[i - 1].getValue() + matrix[i - 1][(int) (j - items[i - 1].getWeight())], matrix[i - 1][j]);
                else
                    matrix[i][j] = matrix[i - 1][j];
            }
        }

        int res = matrix[numberOfItems][(int) capacity];
        int weight = (int) capacity;
        List<Item> itemsSolution = new ArrayList<>();

        for (int n = numberOfItems; n > 0 && res > 0; n--) {
            if (res != matrix[n - 1][weight]) {
                itemsSolution.add(items[n - 1]);
                res -= items[n - 1].getValue();
                weight -= items[n - 1].getWeight();
            }
        }
        return new Solution(itemsSolution, matrix[numberOfItems][(int) capacity], capacity);
    }
}
