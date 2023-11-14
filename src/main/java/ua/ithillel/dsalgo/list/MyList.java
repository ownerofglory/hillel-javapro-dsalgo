package ua.ithillel.dsalgo.list;

public interface MyList<T> extends Iterable<T> {
    boolean isEmpty();
    void add(T el);
    void set(int index, T el);
    T get(int index);
    T remove(int index);
}
