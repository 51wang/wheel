package tuxedo.wheel.utility.array;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArrayUtil {
    @SuppressWarnings("unchecked")
    public static <T> T[] arrayFilledByGenerator(@NonNull Class<T> componentType, int length, IntFunction<? extends T> generator) {
        if (length < 0) {
            throw new IllegalArgumentException("Size should be non-negative!");
        }
        T[] array = (T[]) Array.newInstance(componentType, length);
        Arrays.setAll(array, generator);
        return array;
    }

    public static <T> T[] arrayFilledBySupplier(Class<T> componentType, int length, @NonNull Supplier<? extends T> supplier) {
        return arrayFilledByGenerator(componentType, length, i -> supplier.get());
    }

    public static <T> T[] arrayFilledByValue(Class<T> componentType, int length, T value) {
        return arrayFilledByGenerator(componentType, length, i -> value);
    }

    public static <T> void reverse(@NonNull T[] array) {
        final int length = array.length;
        T tmp;
        for (int i = 0; i < length / 2; i++) {
            tmp = array[i];
            array[i] = array[length - 1 - i];
            array[length - 1 - i] = tmp;
        }
    }

    public static <T> T[] copyAndReverse(@NonNull T[] original) {
        T[] copy = Arrays.copyOf(original, original.length);
        reverse(copy);
        return copy;
    }

    public static <T> List<T> asList(@NonNull T[] array) {
        List<T> list = new ArrayList<T>(array.length);
        Collections.addAll(list, array);
        return list;
    }

    public static <T> List<T> unmodifiableList(T[] array) {
        return Collections.unmodifiableList(Arrays.asList(array));
    }

    public static <T, R> List<R> unmodifiableList(T[] array, Function<? super T, ? extends R> mapper) {
        return Collections.unmodifiableList(Arrays.asList(array).stream().map(mapper).collect(Collectors.toList()));
    }

    public static <T> Set<T> unmodifiableSet(T[] array) {
        return Collections.unmodifiableSet(new HashSet<>(Arrays.asList(array)));
    }

    public static <T, R> Set<R> unmodifiableSet(T[] array, Function<? super T, ? extends R> mapper) {
        return Collections.unmodifiableSet(Arrays.asList(array).stream().map(mapper).collect(Collectors.toSet()));
    }
}
