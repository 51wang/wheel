package tuxedo.wheel.utility.property;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class JointPropertiesTest {
    @Test
    public void testEmpty() {
        Properties properties = new JointProperties(new EmptyProperties(), new EmptyProperties());
        Assert.assertTrue(properties.isEmpty());
        Assert.assertEquals(properties.getProperties(), new HashMap<>());
        Assert.assertNull(properties.getProperty("key"));
    }

    @Test
    public void testNormal() {
        Map<String, String> source1 = new HashMap<>();
        source1.put("a", "a1");
        source1.put("b", "b1");
        Map<String, String> source2 = new HashMap<>();
        source2.put("a", "a2");
        source2.put("c", "c2");
        Map<String, String> expected = new HashMap<>();
        expected.put("a", "a1");
        expected.put("b", "b1");
        expected.put("c", "c2");
        Properties properties = new JointProperties(new BasicProperties(source1), null, new BasicProperties(source2));
        Assert.assertFalse(properties.isEmpty());
        Assert.assertEquals(properties.getProperties(), expected);
        Assert.assertEquals(properties.getProperty("a"), "a1");
        Assert.assertEquals(properties.getProperty("b"), "b1");
        Assert.assertEquals(properties.getProperty("c"), "c2");
    }
}
