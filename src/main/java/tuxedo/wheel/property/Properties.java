package tuxedo.wheel.property;

import java.util.Map;

public interface Properties {
    boolean isEmpty();

    Map<String, String> getProperties();

    String getProperty(String name);

    default String getProperty(String name, String defaultValue) {
        String property = getProperty(name);
        return property != null ? property : defaultValue;
    }

    default int getInt(String name) {
        return Integer.valueOf(getProperty(name));
    }

    default int getInt(String name, int defaultValue) {
        String property = getProperty(name);
        if (property != null) {
            try {
                return Integer.valueOf(property);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    default long getLong(String name) {
        return Long.valueOf(getProperty(name));
    }

    default long getLong(String name, long defaultValue) {
        String property = getProperty(name);
        if (property != null) {
            try {
                return Long.valueOf(property);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    default double getDouble(String name) {
        return Double.valueOf(getProperty(name));
    }

    default double getDouble(String name, double defaultValue) {
        String property = getProperty(name);
        if (property != null) {
            try {
                return Double.valueOf(property);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    default boolean getBoolean(String name) {
        return Boolean.valueOf(getProperty(name));
    }

    default boolean getBoolean(String name, boolean defaultValue) {
        String property = getProperty(name);
        return property != null ? Boolean.valueOf(property) : defaultValue;
    }
}
