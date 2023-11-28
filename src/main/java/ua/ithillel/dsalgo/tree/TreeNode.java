package ua.ithillel.dsalgo.tree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class TreeNode<V> implements Iterable<V> {
    private V value;
    private TreeNode<V> left;
    private TreeNode<V> right;

    public TreeNode(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;
    }

    public TreeNode<V> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<V> left) {
        this.left = left;
    }

    public TreeNode<V> getRight() {
        return right;
    }

    public void setRight(TreeNode<V> right) {
        this.right = right;
    }

    @Override
    public Iterator<V> iterator() {
        return new BreadthFirstIterator();
    }

    private class BreadthFirstIterator implements Iterator<V> {
        private Queue<TreeNode<V>> queue = new LinkedList<>();

        public BreadthFirstIterator() {
            queue.add(TreeNode.this);
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public V next() {
            final TreeNode<V> first = queue.remove();

            final TreeNode<V> left1 = first.getLeft();
            final TreeNode<V> right1 = first.getRight();

            if (left1 != null)
                queue.add(left1);

            if (right1 != null)
                queue.add(right1);

            return first.value;
        }
    }
}
