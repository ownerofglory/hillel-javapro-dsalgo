package ua.ithillel.dsalgo.container;

public class NumberContainer<T extends Number> {
    private  T value;

    public NumberContainer(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

}
