package tuxedo.wheel.property;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FilePropertiesTest {
    @Test(expectedExceptions = { FileNotFoundException.class })
    public void testNonExistent() throws IOException {
        new FileProperties("src/test/resources/nonExistent.properties");
    }

    @Test
    public void testEmpty() throws IOException {
        FileProperties properties = new FileProperties("src/test/resources/property/empty.properties");
        Assert.assertTrue(properties.isEmpty());
        Assert.assertEquals(properties.getProperties(), new HashMap<>());
        Assert.assertNull(properties.getProperty("key"));
    }

    @Test
    public void testNormal() throws IOException {
        Map<String, String> expected = new HashMap<>();
        expected.put("key", "value");
        FileProperties properties = new FileProperties("src/test/resources/property/normal.properties");
        Assert.assertFalse(properties.isEmpty());
        Assert.assertEquals(properties.getProperties(), expected);
        Assert.assertEquals(properties.getProperty("key"), "value");
    }
}
