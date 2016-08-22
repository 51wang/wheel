package tuxedo.wheel.property;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.NonNull;

public class JointProperties implements Properties {
    protected final List<Properties> sources = new ArrayList<>();

    public JointProperties(@NonNull Properties... sources) {
        this(Arrays.asList(sources));
    }

    public JointProperties(@NonNull List<Properties> sources) {
        for (Properties properties : sources) {
            if (properties != null) {
                this.sources.add(properties);
            }
        }
    }

    @Override
    public boolean isEmpty() {
        for (Properties properties : sources) {
            if (!properties.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Map<String, String> getProperties() {
        Map<String, String> all = new HashMap<>();
        for (Properties properties : sources) {
            properties.getProperties().forEach((key, value) -> {
                all.putIfAbsent(key, value);
            });
        }
        return all;
    }

    @Override
    public String getProperty(String name) {
        for (Properties properties : sources) {
            String property = properties.getProperty(name);
            if (property != null) {
                return property;
            }
        }
        return null;
    }
}
