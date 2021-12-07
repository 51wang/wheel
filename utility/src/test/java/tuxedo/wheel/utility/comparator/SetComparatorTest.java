package tuxedo.wheel.utility.comparator;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SetComparatorTest {
    @Test
    public void test() {
        __SetComparator<String> c = new __SetComparator<>();
        c.compare(Stream.of("a", "b", "c", "d").collect(Collectors.toSet()),
                Stream.of("c", "d", "e", "f").collect(Collectors.toSet()));
        Assert.assertEquals(c.added, Stream.of("e", "f").collect(Collectors.toSet()));
        Assert.assertEquals(c.removed, Stream.of("a", "b").collect(Collectors.toSet()));
    }

    private static class __SetComparator<E> implements SetComparator<E> {
        Set<E> added = new HashSet<>();
        Set<E> removed = new HashSet<>();

        @Override
        public void added(E element) {
            added.add(element);
        }

        @Override
        public void removed(E element) {
            removed.add(element);
        }
    }
}
