package tuxedo.wheel.array;

import java.lang.reflect.Array;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import lombok.NonNull;

public class ArrayUtil {
    @SuppressWarnings("unchecked")
    public static <T> T[] arrayFilledBySupplier(@NonNull Class<T> componentType, int length, @NonNull Supplier<? extends T> supplier) {
        if (length < 0) {
            throw new IllegalArgumentException("Size should be non-negative!");
        }
        T[] array = (T[]) Array.newInstance(componentType, length);
        IntStream.range(0, length).forEach(i -> array[i] = supplier.get());
        return array;
    }

    public static <T> T[] arrayFilledByValue(Class<T> componentType, int length, T value) {
        return arrayFilledBySupplier(componentType, length, () -> value);
    }
}
