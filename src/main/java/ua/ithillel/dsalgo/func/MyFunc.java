package ua.ithillel.dsalgo.func;

public interface MyFunc {
    void doSmth();
    default void doSmthWithInt(int i) {
        doSmth();
    }
}
