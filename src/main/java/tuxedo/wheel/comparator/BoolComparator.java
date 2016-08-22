package tuxedo.wheel.comparator;

import lombok.NonNull;

public interface BoolComparator extends ObjectComparator<Boolean> {
    @Override
    default void compare(@NonNull Boolean from, @NonNull Boolean to) {
        if (from) {
            if (!to) {
                changedToFalse();
            }
        } else {
            if (to) {
                changedToTrue();
            }
        }
    }

    void changedToFalse();

    void changedToTrue();
}
