package ua.ithillel.dsalgo.container;

public class NumberUtils {
    public static <T extends Number> NumberContainer<T> createContainer(T value) {
        return new NumberContainer<>(value);
    }
}
