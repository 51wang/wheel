package tuxedo.wheel.comparator;

import java.util.Set;
import java.util.stream.Stream;

import lombok.NonNull;

public interface SetComparator<E> extends ObjectComparator<Set<E>> {
    @Override
    default void compare(@NonNull Set<E> from, @NonNull Set<E> to) {
        Stream.concat(from.stream(), to.stream()).filter(e -> e != null).distinct().forEach(e -> {
            if (!from.contains(e)) {
                added(e);
            } else {
                if (!to.contains(e)) {
                    removed(e);
                }
            }
        });
    }

    void added(E element);

    void removed(E element);
}
