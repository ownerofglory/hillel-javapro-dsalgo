package ua.ithillel.dsalgo.map;

public interface MyMap<K, V> {
    boolean isEmpty();
    void put(K key, V value);
    V get(K key);
    V remove(K key);
    boolean containsKey(K key);
}
