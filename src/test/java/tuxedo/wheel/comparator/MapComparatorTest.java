package tuxedo.wheel.comparator;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import tuxedo.wheel.base.Pair;

public class MapComparatorTest {
    @Test
    public void test() {
        __MapComparator<String, Integer> c = new __MapComparator<>();
        Map<String, Integer> from = new HashMap<>();
        from.put("a", 1);
        from.put("b", 2);
        from.put("c", 3);
        Map<String, Integer> to = new HashMap<>();
        to.put("b", 2);
        to.put("c", 33);
        to.put("d", 4);
        Map<String, Integer> added = new HashMap<>();
        added.put("d", 4);
        Map<String, Integer> removed = new HashMap<>();
        removed.put("a", 1);
        Map<String, Pair<Integer, Integer>> changed = new HashMap<>();
        changed.put("c", new Pair<>(3, 33));
        c.compare(from, to);
        Assert.assertEquals(c.added, added);
        Assert.assertEquals(c.removed, removed);
        Assert.assertEquals(c.changed, changed);
    }

    private static class __MapComparator<K, V> implements MapComparator<K, V> {
        Map<K, V> added = new HashMap<>();
        Map<K, V> removed = new HashMap<>();
        Map<K, Pair<V, V>> changed = new HashMap<>();

        @Override
        public void added(K key, V value) {
            added.put(key, value);
        }

        @Override
        public void removed(K key, V value) {
            removed.put(key, value);
        }

        @Override
        public void changed(K key, V fromValue, V toValue) {
            changed.put(key, new Pair<>(fromValue, toValue));
        }
    }
}
