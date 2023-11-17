package ua.ithillel.dsalgo.map;


import java.lang.reflect.Array;
import java.util.Arrays;

public class MyHashMap<K, V> implements MyMap<K, V> {
    private static final int INIT_CAPACITY = 10;
    private static final double LOAD_FACTOR = 0.75;
    private static final double SCALE_FACTOR = 1.5;
    private Node<Entry<K, V>>[] buckets;
    private int load;

    public MyHashMap() {
        final Node<Entry<K, V>>[] genericArray = getGenericArray(new Node<Entry<K, V>>(null));
        buckets = (Node<Entry<K, V>>[]) Array.newInstance(genericArray.getClass().getComponentType(), INIT_CAPACITY);
    }

    @Override
    public boolean isEmpty() {
        return load == 0;
    }

    @Override
    public void put(K key, V value) {
        ensureCapacity();

        Entry<K, V> newEntry = new Entry<>(key, value);
        Node<Entry<K, V>> newNode = new Node<>(newEntry);

        putNode(newNode);
    }

    private void putNode(Node<Entry<K, V>> newNode) {
        final int bucketIdx = hash(newNode.entry.key);
        var existingValue = buckets[bucketIdx];
        if (existingValue == null) {
            buckets[bucketIdx] = newNode;
            load++;
            return;
        }

        newNode.next = existingValue;
        buckets[bucketIdx] = newNode;
        load++;
    }

    @Override
    public V get(K key) {
        int bucketIdx = hash(key);

        var existingNode = buckets[bucketIdx];
        if (existingNode != null) {
            var curNode = existingNode;
            while (curNode != null) {
                if (curNode.entry.key == key || curNode.entry.key.equals(key)) {
                    return curNode.entry.value;
                }

                curNode = curNode.next;
            }
        }

        return null;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    private void ensureCapacity() {
        // load == 75% of lenght
        if (load < LOAD_FACTOR * buckets.length) {
            return;
        }

        scale();
    }

    private void scale() {
        int newLength = (int) (buckets.length * SCALE_FACTOR);

        var oldBuckets = buckets;

        final Node<Entry<K, V>>[] genericArray = getGenericArray(new Node<Entry<K, V>>(null));
        buckets = (Node<Entry<K, V>>[]) Array.newInstance(genericArray.getClass().getComponentType(), newLength);

        for (var node :
                oldBuckets) {
            if (node != null) {
                var curNode = node;
                while (curNode != null) {
                    var newNode = new Node<Entry<K, V>>(curNode.entry);
                    putNode(newNode);

                    curNode = curNode.next;
                }
            }
        }
    }

    private int hash(K key) {
        // 0 ... buckets.length - 1
        return key.hashCode() & (buckets.length - 1);
    }

    private class Node<T> {
        T entry;
        Node<T> next;

        public Node(T entry) {
            this.entry = entry;
        }

        @Override
        public String toString() {
            return "[" + entry.toString() + "]";
        }
    }

    private class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + key + ": " + value + ")";
        }
    }

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
