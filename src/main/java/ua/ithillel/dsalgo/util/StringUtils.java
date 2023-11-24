package ua.ithillel.dsalgo.util;

public class StringUtils {
    public static String reverseIter(String s) {
        final StringBuffer buffer = new StringBuffer();

        for (int i = s.length() - 1; i >= 0; i--) {
            buffer.append(s.charAt(i));
        }

        return buffer.toString();
    }

    public static String reverseRecurse(String s) {
        // base case
        if (s.length() == 0) {
            return s;
        }

        // recursive case
        final char lastChar = s.charAt(s.length() - 1);

        return lastChar + reverseRecurse(s.substring(0, s.length() - 1));
    }
}
