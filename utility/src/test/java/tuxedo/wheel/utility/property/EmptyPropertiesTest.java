package tuxedo.wheel.utility.property;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class EmptyPropertiesTest {
    @Test
    public void test() {
        Properties properties = new EmptyProperties();
        Assert.assertTrue(properties.isEmpty());
        Assert.assertEquals(properties.getProperties(), new HashMap<>());
        Assert.assertNull(properties.getProperty("key"));
    }
}
