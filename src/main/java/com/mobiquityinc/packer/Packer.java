package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Solution;
import com.mobiquityinc.packer.strategy.KnapsackDynamic;


public class Packer {

    private static int position = 1;

    public static String pack(String filePath) throws APIException {

        InputReader reader = new InputReader();
        StringBuilder stringBuilderResult = new StringBuilder();

        reader.readFile(filePath).forEach(scenario -> {
            Knapsack knapsack = new Knapsack(new KnapsackDynamic());
            Item[] items = new Item[]{};
            Solution solution = knapsack.executeStrategy(scenario.getItems().toArray(items), scenario.getTotalWeight());
            stringBuilderResult.append("\n").append(position).append("\n");
            solution.buildReturn(stringBuilderResult);
            position++;
        });
        System.out.println(stringBuilderResult.toString());
        return stringBuilderResult.toString();
    }

    public static void main(String[] args) throws APIException {
        if (args == null) {
            System.out.println("You have not provided any arguments!");
        } else {
            pack(args[0]);
        }
    }

}

