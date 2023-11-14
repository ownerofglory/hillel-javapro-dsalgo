package ua.ithillel.dsalgo.util;

import java.lang.reflect.Array;


public class ArrayUtil {
    public static <T> T[] getGenericArray(int size) {
        final T[] emptyArr = getGenericArray();
        final Class<?> componentType = emptyArr.getClass().getComponentType();

        return (T[]) Array.newInstance(componentType, size);
    }

    // var arg - variable count of arguments
    private static <T> T[] getGenericArray(T ... args) {
        return args;
    }
}
