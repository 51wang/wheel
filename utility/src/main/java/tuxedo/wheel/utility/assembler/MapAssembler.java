package tuxedo.wheel.utility.assembler;

import java.util.HashMap;
import java.util.Map;

public class MapAssembler<K, V> extends ObjectAssembler<Map<K, V>> {
    public MapAssembler(Map<K, V> target) {
        super(target);
    }

    public MapAssembler() {
        this(new HashMap<K, V>());
    }

    public MapAssembler<K, V> put(K key, V value) {
        target.put(key, value);
        return this;
    }

    public MapAssembler<K, V> putAll(Map<? extends K, ? extends V> m) {
        target.putAll(m);
        return this;
    }
}
