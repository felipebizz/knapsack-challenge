package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Solution;
import com.mobiquityinc.packer.strategy.KnapsackDynamic;
import org.apache.log4j.Logger;


public class Packer {

    private static Logger logger = Logger.getLogger(Packer.class);

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
        logger.info(stringBuilderResult.toString());
        return stringBuilderResult.toString();
    }

    public static void main(String[] args) throws APIException {
        if (args == null) {
            logger.info("You have not provided any arguments!");
        } else {
            pack(args[0]);
        }
    }

}

