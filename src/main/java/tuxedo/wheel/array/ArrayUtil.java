package tuxedo.wheel.array;

import java.lang.reflect.Array;
import java.util.Arrays;
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

    public static <T> void reverse(T[] array) {
        final int length = array.length;
        for (int i = 0; i < length / 2; i++) {
            T tmp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = tmp;
        }
    }

    public static <T> T[] copyAndReverse(T[] original) {
        T[] copy = Arrays.copyOf(original, original.length);
        reverse(copy);
        return copy;
    }
}
