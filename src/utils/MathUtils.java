package utils;

/**
 * Created by connected on 11/14/16.
 */
public class MathUtils {

    public static long factorial(final int number) {
        long factorial = number;
        for (int i = 1; i < number; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
