package tuxedo.wheel.property;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PropertiesTest {
    private final Properties properties = new __Properties();

    @Test
    public void test() throws IOException {
        Assert.assertEquals(properties.getProperty("string", "default"), "test");
        Assert.assertEquals(properties.getProperty("string0", "default"), "default");
        Assert.assertEquals(properties.getInt("int", 100), 1);
        Assert.assertEquals(properties.getInt("int0", 100), 100);
        Assert.assertEquals(properties.getLong("long", 200), 22);
        Assert.assertEquals(properties.getLong("long0", 200), 200);
        Assert.assertEquals(properties.getDouble("double", 333.3), 33.3);
        Assert.assertEquals(properties.getDouble("double0", 333.3), 333.3);
        Assert.assertEquals(properties.getBoolean("boolean_t", false), true);
        Assert.assertEquals(properties.getBoolean("boolean_f", true), false);
        Assert.assertEquals(properties.getBoolean("boolean0", true), true);
    }

    private static class __Properties implements Properties {
        final @NonNull Map<String, String> source = new HashMap<>();

        __Properties() {
            source.put("string", "test");
            source.put("int", "1");
            source.put("long", "22");
            source.put("double", "33.3");
            source.put("boolean_t", "true");
            source.put("boolean_f", "false");
        }

        @Override
        public boolean isEmpty() {
            return source.isEmpty();
        }

        @Override
        public Map<String, String> getProperties() {
            return new HashMap<>(source);
        }

        @Override
        public String getProperty(String name) {
            return source.get(name);
        }
    }
}
