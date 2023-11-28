package ua.ithillel.dsalgo.util;

import javax.xml.stream.XMLInputFactory;
import java.util.Arrays;

public class SearchUtils {
    // [ 0, 4, 5, 7, 9] 4
    // binarySearch(arr, 4, 0, arr.length - 1)
    public static int binarySearch(int[] arr, int value, int low, int high) {
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        final int midVal = arr[mid];

        if (midVal > value) {
            // go left
            return binarySearch(arr, value, low, mid);

        } else if (midVal < value) {
            // go right
            return binarySearch(arr, value, mid + 1, high);

        } else {
            return mid;
        }

    }
}
