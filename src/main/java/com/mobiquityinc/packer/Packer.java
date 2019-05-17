package com.mobiquityinc.packer;

import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Solution;
import com.mobiquityinc.packer.strategy.KnapsackDynamic;


public class Packer {

    private static int position = 1;

    public static String build(String filePath) throws APIException {

        InputReader reader = new InputReader();
        StringBuffer stringBufferResult = new StringBuffer();

        reader.readFile(filePath).forEach(scenario -> {
            Knapsack knapsack = new Knapsack(new KnapsackDynamic());
            Item[] items = new Item[]{};
            Solution solution = knapsack.executeStrategy(scenario.getItems().toArray(items), scenario.getTotalWeight());
            solution.display();
            stringBufferResult.append("\n").append(position).append("\n");
            solution.buildReturn(stringBufferResult);
            position++;
        });
        System.out.println(stringBufferResult.toString());
        return stringBufferResult.toString();
    }

    public static void main(String[] args) throws APIException {
        if(args == null) {
            System.out.println("You have not provided any arguments!");
        }else {
            build(args[0]);
        }
    }

}

