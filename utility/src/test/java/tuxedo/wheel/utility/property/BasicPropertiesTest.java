package tuxedo.wheel.utility.property;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class BasicPropertiesTest {
    @Test
    public void test() {
        Map<String, String> source = new HashMap<>();
        Properties properties = new BasicProperties(source);
        Assert.assertTrue(properties.isEmpty());
        Assert.assertEquals(properties.getProperties(), source);
        Assert.assertNull(properties.getProperty("key"));
        source.put("key", "value");
        Assert.assertFalse(properties.isEmpty());
        Assert.assertEquals(properties.getProperties(), source);
        Assert.assertEquals(properties.getProperty("key"), "value");
    }
}
