package tuxedo.wheel.utility.property;

import java.util.HashMap;
import java.util.Map;

public class EmptyProperties implements Properties {
    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Map<String, String> getProperties() {
        return new HashMap<>();
    }

    @Override
    public String getProperty(String name) {
        return null;
    }
}
