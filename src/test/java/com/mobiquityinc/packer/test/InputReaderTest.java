package com.mobiquityinc.packer.test;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.packer.InputReader;
import com.mobiquityinc.packer.Packer;
import com.mobiquityinc.packer.model.Item;
import com.mobiquityinc.packer.model.Package;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;

/**
 * Test suite for the {@link InputReader} class.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(InputReader.class)
public class InputReaderTest extends AnyTestcase {

    private InputReader reader = new InputReader();

    @Before
    public void setup() {
        PowerMockito.mockStatic(Files.class);
    }

    @Test(expected = APIException.class)
    public void checkWhen_FileArgIsNull() {
        Packer.pack(null);
    }

    @Test(expected = APIException.class)
    public void checkWhen_FileDoesNotExist() {
        Packer.pack("/file/doesnot/exist.txt");
    }

    @Test
    public void loadSuccessfully_AOneItemRow() {
        try {
            Collection<Package> scenarios = this.getRowReader().readFile("8 : (1,5.3,€34)");
            Assert.assertEquals(1, scenarios.size());
            Package pack = scenarios.iterator().next();
            Assert.assertEquals(8, pack.getTotalWeight(), 0.01);
            Assert.assertEquals(1, pack.getItems().size());
            Item singleItem = pack.getItems().iterator().next();
            Assert.assertEquals(1, singleItem.getIndex());
            Assert.assertEquals(5.3, singleItem.getWeight(), 0.01);
            Assert.assertEquals(34, singleItem.getValue());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Not expecting exceptions of type " + e.getClass());
        }
    }

    @Test
    public void loadSuccessfully_AMultiItemRow() {
        try {
            Collection<Package> scenario =
                    this.getRowReader().readFile("75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52)");
            Assert.assertEquals(1, scenario.size());
            Package pack = scenario.iterator().next();
            Assert.assertEquals(75, pack.getTotalWeight(), 0.01);
            Assert.assertEquals(5, pack.getItems().size());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Not expecting exceptions of type " + e.getClass());
        }
    }

    @Test
    public void loadSuccessfully_aRowWith_TrailingWhitespaces() {
        try {
            Collection<Package> scenarios = this.getRowReader().readFile("75 : (1,55.31,€29) (2,14.55,€74) ");
            Assert.assertEquals(1, scenarios.size());
            Package singeScenario = scenarios.iterator().next();
            Assert.assertEquals(75, singeScenario.getTotalWeight(), 0.01);
            Assert.assertEquals(2, singeScenario.getItems().size());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Not expecting exceptions of type " + e.getClass());
        }
    }

    @Test
    public void loadSuccessfully_items_and_totalWeight_FromFile() {
        try {

            Collection<Package> scenarios = reader.readFile("./src/test/resources/input.txt");
            Assert.assertEquals(4, scenarios.size());
            for (Package singeScenario : scenarios) {
                if (singeScenario.getTotalWeight() == 81) {
                    Assert.assertEquals(6, singeScenario.getItems().size());
                } else if (singeScenario.getTotalWeight() == 8) {
                    Assert.assertEquals(1, singeScenario.getItems().size());
                } else if (singeScenario.getTotalWeight() == 75) {
                    Assert.assertEquals(9, singeScenario.getItems().size());
                } else if (singeScenario.getTotalWeight() == 56) {
                    Assert.assertEquals(9, singeScenario.getItems().size());
                } else {
                    Assert.fail("Not expecting to load a scenario with max weight of " + singeScenario.getTotalWeight());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Not expecting exceptions of type " + e.getClass());
        }
    }

    @Test(expected = APIException.class)
    public void testInvalidItemFormat() throws IOException {
        final String line = "58 : (1,53.38)";
        PowerMockito.when(Files.readAllLines(any())).thenReturn(Collections.singletonList(line));

        reader.readFile("anyPath").size();
    }
}
