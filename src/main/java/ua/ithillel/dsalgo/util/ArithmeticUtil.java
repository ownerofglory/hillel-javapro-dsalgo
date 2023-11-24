package ua.ithillel.dsalgo.util;

import ua.ithillel.dsalgo.model.Person;

import java.util.Arrays;
import java.util.Comparator;

public class ArithmeticUtil {

    // 0! = 1 - basic case
    // 1! = 1 * 0!
    // 2! = 2 * 1!
    // 3! = 3 * 2! = 3 * 2 * 1 = 6
    // 4! = 4 * 3 * 2 * 1 = 24
    public static int factorial(int number) {
        // basic case
        if (number == 0) {
            return 1;
        }

        // recursive case
        return number * factorial(number - 1);
    }

    public static int maxInt(int ... nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("empty argument list");
        }

        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
        }

        return max;
    }

    public static <T extends Comparable<T>> T max(T ... values) {
        T max = values[0];

        for (int i = 1; i < values.length; i++) {
            final int cmp = max.compareTo(values[i]);
            if (cmp < 0) {
                max = values[i];
            }
        }

        return max;
    }

    public static <T> T max(Comparator<T> comparator, T ... values) {
        if (values == null || values.length == 0) {
            throw new RuntimeException("empty argument list");
        }

        T max = values[0];

        for (int i = 1; i < values.length; i++) {
            final int cmp = comparator.compare(max, values[i]);
            if (cmp > 0) {
                max = values[i];
            }
        }

        return max;
    }

    public static <T extends Comparable<T>> T maxRec(T ... values) {
        if (values == null || values.length == 0) {
            throw new RuntimeException("empty argument list");
        }

        T max = values[0];

        if (values.length == 1) {
            return max;
        }

        final T[] remainder = Arrays.copyOfRange(values, 1, values.length - 1);
        final T otherMax = maxRec(remainder);

        final int cmp = max.compareTo(otherMax);

        return cmp < 0 ? otherMax : max;
    }

    public static <T> T maxRec(Comparator<T> comparator, T ... values) {
        if (values == null || values.length == 0) {
            throw new RuntimeException("empty argument list");
        }

        T max = values[0];

        if (values.length == 1) {
            return max;
        }

        final T[] remainder = Arrays.copyOfRange(values, 1, values.length);
        final T otherMax = maxRec(comparator, remainder);

        final int cmp = comparator.compare(max, otherMax);
        if (cmp > 0) {
            max = otherMax;
        }

        return max;
    }

    public static int maxIntRec(int ... nums) {
        if (nums == null || nums.length == 0) {
            throw new RuntimeException("empty argument list");
        }

        int max = nums[0];

        // base case
        if (nums.length == 1) {
            return max;
        }


        // recursive case
        int[] remainder = Arrays.copyOfRange(nums, 1, nums.length);
        int otherMax = maxInt(remainder);

        if (max < otherMax) {
            max = otherMax;
        }


        return max;
    }
}
