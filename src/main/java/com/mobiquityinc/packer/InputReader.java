package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Reads the lines from the input file and translates each one of them into an instance of {@link Package}. If
 * the file does not exist, is not a real file or has on line that do not match the predefined format, then an
 * {@link APIException} will be thrown.
 * <br/>
 * The format of each line should be:
 * <p>
 * <code>{maxPackageWeight}: ({itemIndex},{itemWeight},{itemCost}), ...</code>
 * </p>
 */
public class InputReader {

    private final Pattern linePattern = Pattern.compile("\\((\\d+),(\\d+\\.?\\d*?),€?(\\d+)\\)");
    private static final int TOTAL_VALUE = 100;
    private static final int TOTAL_WEIGHT = 100;
    private static final int TOTAL_ITEMS = 15;

    /**
     * @param pathName pathName
     * @return Collection<Package>
     * @throws APIException APIException if File don not exist.
     */
    public Collection<Package> readFile(String pathName) throws APIException {

        if (pathName == null) {
            throw new APIException("File must exist.");
        }

        File inputFile = new File(pathName);
        if (!inputFile.exists() || !inputFile.isFile()) {
            throw new APIException("File must exist.");
        }

        Collection<Package> scenarios = new LinkedList<>();
        try {
            int lineNumber = 1;
            for (String testScenario : FileUtils.readLines(inputFile, Charset.forName("UTF-8"))) {
                scenarios.add(this.parseLine(testScenario.trim(), lineNumber++));
            }
        } catch (IOException ioe) {
            throw new APIException("Error while reading the input file", ioe);
        }
        return scenarios;
    }

    /**
     * @param line       line
     * @param lineNumber lineNumber
     * @return Package
     * @throws APIException if is not possible to read package
     */

    protected Package parseLine(String line, int lineNumber) throws APIException {

        int idx = line.indexOf(":");

        if (idx < 0) {
            throw new APIException("Line #" + lineNumber + " does not conform to the predefined format. Cannot read package max weight!");
        }

        try {
            int pkgMaxWeight = Integer.valueOf(line.substring(0, idx).trim());

            Matcher matcher = this.linePattern.matcher(line.substring(idx + 1));
            Package itemsPackage = new Package(pkgMaxWeight);
            while (matcher.find()) {
                int index = Integer.valueOf(matcher.group(1));
                double weight = Double.parseDouble(matcher.group(2));
                int value = Integer.valueOf(matcher.group(3));
                Item item = new Item(index, weight, value);
                itemsPackage.addItems(item);
            }

            validateBusinessRules(itemsPackage);

            return itemsPackage;
        } catch (RuntimeException re) {
            throw new APIException("Error while reading line #" + lineNumber, re);
        }
    }

    /**
     * Additional constraints
     *
     * @param pack Package
     * @throws APIException if some validation is not attended
     */
    private void validateBusinessRules(final Package pack) throws APIException {
        if (pack.getTotalWeight() > TOTAL_WEIGHT)
            throw new APIException("The maximum weight a packet can carry must be ≤ 100");
        if (pack.getItems().size() > TOTAL_ITEMS)
            throw new APIException("You cannot choose more than 15 items");
        for (Item item : pack.getItems()) {
            if (item.getWeight() > TOTAL_WEIGHT)
                throw new APIException("Max weight must be <= 100");
            if (item.getValue() > TOTAL_VALUE)
                throw new APIException("Item cost cannot be > 100");
        }
    }
}