package com.mobiquityinc.packer.test;

import com.mobiquityinc.packer.InputReader;
import com.mobiquityinc.packer.Packer;
import com.mobiquityinc.packer.exception.APIException;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test suite for the {@link InputReader} class.
 */
public class InputReaderTest extends AnyTestcase {

    @Test
    public void checkWhen_FileArgIsNull() {
        try {
            Packer.pack(null);
        } catch (APIException ex1) {
            assertEquals(ex1.getMessage(), "File must exist.");
        } catch (Exception ex2) {
            ex2.printStackTrace();
            fail("Not expecting exceptions of type" + ex2.getClass());
        }
    }

    @Test
    public void checkWhen_FileDoesNotExist() {
        try {
            Packer.pack("/file/doesnot/exist.txt");
        } catch (APIException ex1) {
            assertEquals(ex1.getMessage(), "File must exist.");
        } catch (Exception ex2) {
            ex2.printStackTrace();
            fail("Not expecting exceptions of type " + ex2.getClass());
        }
    }

    @Test
    public void loadSuccessfully_AOneItemRow() {
        try {
            Collection<Package> scenarios = this.getRowReader().readFile("8 : (1,5.3,€34)");
            assertEquals(1, scenarios.size());
            Package pack = scenarios.iterator().next();
            assertEquals(8, pack.getTotalWeight(), 0.01);
            assertEquals(1, pack.getItems().size());
            Item singleItem = pack.getItems().iterator().next();
            assertEquals(1, singleItem.getIndex());
            assertEquals(5.3, singleItem.getWeight(), 0.01);
            assertEquals(34, singleItem.getValue());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not expecting exceptions of type " + e.getClass());
        }
    }

    @Test
    public void loadSuccessfully_AMultiItemRow() {
        try {
            Collection<Package> scenario =
                    this.getRowReader().readFile("75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52)");
            assertEquals(1, scenario.size());
            Package pack = scenario.iterator().next();
            assertEquals(75, pack.getTotalWeight(), 0.01);
            assertEquals(5, pack.getItems().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not expecting exceptions of type " + e.getClass());
        }
    }

    @Test
    public void loadSuccessfully_aRowWith_TrailingWhitespaces() {
        try {
            Collection<Package> scenarios = this.getRowReader().readFile("75 : (1,55.31,€29) (2,14.55,€74) ");
            assertEquals(1, scenarios.size());
            Package singeScenario = scenarios.iterator().next();
            assertEquals(75, singeScenario.getTotalWeight(), 0.01);
            assertEquals(2, singeScenario.getItems().size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not expecting exceptions of type " + e.getClass());
        }
    }

    @Test
    public void loadSuccessfully_items_and_totalWeight_FromFile() {
        try {
            InputReader reader = new InputReader();
            Collection<Package> scenarios = reader.readFile("./src/test/resources/input.txt");
            assertEquals(4, scenarios.size());
            for (Package singeScenario : scenarios) {
                if (singeScenario.getTotalWeight() == 81) {
                    assertEquals(6, singeScenario.getItems().size());
                } else if (singeScenario.getTotalWeight() == 8) {
                    assertEquals(1, singeScenario.getItems().size());
                } else if (singeScenario.getTotalWeight() == 75) {
                    assertEquals(9, singeScenario.getItems().size());
                } else if (singeScenario.getTotalWeight() == 56) {
                    assertEquals(9, singeScenario.getItems().size());
                } else {
                    fail("Not expecting to load a scenario with max weight of " + singeScenario.getTotalWeight());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail("Not expecting exceptions of type " + e.getClass());
        }
    }
}
