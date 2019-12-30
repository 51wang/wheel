package tuxedo.wheel.utility.property;

import java.util.HashMap;
import java.util.Map;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BasicProperties implements Properties {
    protected final @NonNull Map<String, String> source;

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
