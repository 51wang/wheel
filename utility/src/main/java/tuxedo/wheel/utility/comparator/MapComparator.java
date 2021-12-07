package tuxedo.wheel.utility.comparator;

import lombok.NonNull;

import java.util.Map;
import java.util.stream.Stream;

public interface MapComparator<K, V> extends ObjectComparator<Map<K, V>> {
    @Override
    default void compare(@NonNull Map<K, V> from, @NonNull Map<K, V> to) {
        Stream.concat(from.keySet().stream(), to.keySet().stream()).filter(key -> key != null).distinct().forEach(k -> {
            V _from = from.get(k);
            V _to = to.get(k);
            if (_from == null) {
                if (_to != null) {
                    added(k, _to);
                }
            } else {
                if (_to == null) {
                    removed(k, _from);
                } else {
                    if (!equals(_from, _to)) {
                        changed(k, _from, _to);
                    }
                }
            }
        });
    }

    default boolean equals(V _from, V _to) {
        return _from.equals(_to);
    }

    void added(K key, V value);

    void removed(K key, V value);

    void changed(K key, V fromValue, V toValue);
}
