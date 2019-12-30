package tuxedo.wheel.utility.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StdInUtil {
    public static String nextLine() throws IOException {
        return new BufferedReader(new InputStreamReader(System.in)).readLine();
    }

    public static float nextFloat() throws IOException {
        return Float.parseFloat(nextLine());
    }

    public static double nextDouble() throws IOException {
        return Double.parseDouble(nextLine());
    }

    public static int nextInt() throws IOException {
        return Integer.parseInt(nextLine());
    }

    public static long nextLong() throws IOException {
        return Long.parseLong(nextLine());
    }

    public static boolean nextBoolean() throws IOException {
        return Boolean.parseBoolean(nextLine());
    }
}
