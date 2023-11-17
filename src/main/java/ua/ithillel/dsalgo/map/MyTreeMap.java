package ua.ithillel.dsalgo.map;

import java.util.Comparator;

public class MyTreeMap<K, V> implements MyMap<K, V> {
    private TreeNode root;
    private Comparator<K> comparator;

    public MyTreeMap() {
    }

    public MyTreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public void put(K key, V value) {
        final Entry newEntry = new Entry(key, value);
        final TreeNode newNode = new TreeNode(newEntry);

        if (root == null) {
            root = newNode;
            return;
        }

        var curNode = root;
        var parentNode = curNode;
        int cmp = 0;

        if (comparator == null) {
            final Comparable<K> comparableKey = (Comparable<K>) key;
            while (curNode != null) {
                cmp = comparableKey.compareTo(curNode.entry.key);
                parentNode = curNode;
                if (cmp > 0) {
                    curNode = curNode.right;
                } else if (cmp < 0) {
                    curNode = curNode.left;
                } else {
                    curNode.entry.value = value;
                    return;
                }
            }
        } else {
            while (curNode != null) {
                cmp = comparator.compare(key, curNode.entry.key);
                parentNode = curNode;
                if (cmp > 0) {
                    curNode = curNode.right;
                } else if (cmp < 0) {
                    curNode = curNode.left;
                } else {
                    curNode.entry.value = value;
                    return;
                }
            }
        }


        if (cmp > 0) {
            parentNode.right = newNode;
        } else {
            parentNode.left = newNode;
        }

        newNode.parent = parentNode;

    }

    @Override
    public V get(K key) {
        var curNode = root;
        int cmp = 0;

        if (comparator == null) {
            final Comparable<K> comparableKey = (Comparable<K>) key;
            while (curNode != null) {
                cmp = comparableKey.compareTo(curNode.entry.key);
                if (cmp > 0) {
                    curNode = curNode.right;
                } else if (cmp < 0) {
                    curNode = curNode.left;
                } else {
                    return curNode.entry.value;
                }
            }
        } else {
            while (curNode != null) {
                cmp = comparator.compare(key, curNode.entry.key);
                if (cmp > 0) {
                    curNode = curNode.right;
                } else if (cmp < 0) {
                    curNode = curNode.left;
                } else {
                    return curNode.entry.value;
                }
            }
        }

        return null;
    }

    @Override
    public V remove(K key) {
        var curNode = root;
        int cmp = 0;

        if (comparator == null) {
            final Comparable<K> comparableKey = (Comparable<K>) key;
            while (curNode != null) {
                cmp = comparableKey.compareTo(curNode.entry.key);
                if (cmp > 0) {
                    curNode = curNode.right;
                } else if (cmp < 0) {
                    curNode = curNode.left;
                } else {
                    break;
                }
            }
        } else {
            while (curNode != null) {
                cmp = comparator.compare(key, curNode.entry.key);
                if (cmp > 0) {
                    curNode = curNode.right;
                } else if (cmp < 0) {
                    curNode = curNode.left;
                } else {
                    break;
                }
            }
        }

        // return null if not value by key was found
        if (curNode == null)
            return null;

        // check whether curNode is root (no parent)
        var parent = curNode.parent;
        if (parent == null) {
            root = null;
        } else {
            // ... compare
            if (comparator == null) {
                Comparable<K> comparableKey = (Comparable<K>) key;
                cmp = comparableKey.compareTo(parent.entry.key);
                if (cmp > 0) {
                    parent.right = null;
                } else {
                    parent.left = null;
                }
            } else {
                cmp = comparator.compare(key, parent.entry.key);
                if (cmp > 0) {
                    parent.right = null;
                } else {
                    parent.left = null;
                }
            }
        }

        // safe subtrees of deleted node
        putSubTree(curNode.left);
        putSubTree(curNode.right);

        // return  deleted value
        return curNode.entry.value;
    }

    private void putSubTree(TreeNode subTree) {
        if (subTree == null) {
            return;
        }

        put(subTree.entry.key, subTree.entry.value);
        putSubTree(subTree.left);
        putSubTree(subTree.right);
    }

    @Override
    public boolean containsKey(K key) {
        var curNode = root;
        int cmp = 0;

        if (comparator == null) {
            final Comparable<K> comparableKey = (Comparable<K>) key;
            while (curNode != null) {
                cmp = comparableKey.compareTo(curNode.entry.key);
                if (cmp > 0) {
                    curNode = curNode.right;
                } else if (cmp < 0) {
                    curNode = curNode.left;
                } else {
                    return true;
                }
            }
        } else {
            while (curNode != null) {
                cmp = comparator.compare(key, curNode.entry.key);
                if (cmp > 0) {
                    curNode = curNode.right;
                } else if (cmp < 0) {
                    curNode = curNode.left;
                } else {
                    return true;
                }
            }
        }



        return false;
    }

    private class TreeNode {
        Entry entry;

        public TreeNode(Entry entry) {
            this.entry = entry;
        }

        TreeNode left;
        TreeNode right;
        TreeNode parent;

        @Override
        public String toString() {
            return "[" + entry.toString() + "]";
        }
    }

    private class Entry {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "(" + key  + ": " + value + ")";
        }
    }
}
