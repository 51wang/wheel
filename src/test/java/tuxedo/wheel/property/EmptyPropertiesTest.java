package tuxedo.wheel.property;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

public class EmptyPropertiesTest {
    @Test
    public void test() {
        Properties properties = new EmptyProperties();
        Assert.assertTrue(properties.isEmpty());
        Assert.assertEquals(properties.getProperties(), new HashMap<>());
        Assert.assertNull(properties.getProperty("key"));
    }
}
