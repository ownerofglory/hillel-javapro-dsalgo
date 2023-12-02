package ua.ithillel.dsalgo.container;

// generic type parameter T
public class Container<T> {
    private final T value;

    public Container(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
