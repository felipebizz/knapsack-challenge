package com.mobiquityinc.packer.test;

import com.mobiquityinc.packer.Packer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test suite for the {@link Packer} class.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Packer.class)
public class PackerTest extends AnyTestcase {

    private Packer packer = new Packer();

    @Test
    public void test_ReturnExpected() {
        try {

            String packageInfo = packer.pack("./src/test/resources/input.txt");

            String expectedResult = "\n" + "1\n" + "-\n" + "4\n" +
                    "\n" + "2\n" + "\n" + "3\n" + "-\n" + "4,3,2\n" +
                    "\n" + "4\n" + "-\n" + "9,6\n";

            assertEquals(expectedResult, packageInfo);

        } catch (Exception e) {
            e.printStackTrace();
            fail("Not expecting exceptions of type " + e.getClass());
        }
    }
}
