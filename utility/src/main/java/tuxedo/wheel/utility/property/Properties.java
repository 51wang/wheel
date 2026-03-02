package tuxedo.wheel.utility.property;

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
        return Integer.parseInt(getProperty(name));
    }

    default int getInt(String name, int defaultValue) {
        String property = getProperty(name);
        if (property != null) {
            try {
                return Integer.parseInt(property);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    default long getLong(String name) {
        return Long.parseLong(getProperty(name));
    }

    default long getLong(String name, long defaultValue) {
        String property = getProperty(name);
        if (property != null) {
            try {
                return Long.parseLong(property);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    default double getDouble(String name) {
        return Double.parseDouble(getProperty(name));
    }

    default double getDouble(String name, double defaultValue) {
        String property = getProperty(name);
        if (property != null) {
            try {
                return Double.parseDouble(property);
            } catch (NumberFormatException e) {
            }
        }
        return defaultValue;
    }

    default boolean getBoolean(String name) {
        return Boolean.parseBoolean(getProperty(name));
    }

    default boolean getBoolean(String name, boolean defaultValue) {
        String property = getProperty(name);
        return property != null ? Boolean.parseBoolean(property) : defaultValue;
    }
}
