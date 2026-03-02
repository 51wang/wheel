package tuxedo.wheel.utility.property;

import lombok.NonNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JointProperties implements Properties {
    private final List<Properties> sources;

    public JointProperties(Properties... sources) {
        this(Arrays.asList(sources));
    }

    public JointProperties(@NonNull List<Properties> sources) {
        this.sources = sources.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
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
            properties.getProperties().forEach(all::putIfAbsent);
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
