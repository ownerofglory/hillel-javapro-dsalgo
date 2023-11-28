package ua.ithillel.dsalgo.tree;

import java.util.*;

public class TreeUtils {
    public static <V> List<V> depthFirst(TreeNode<V> root) {
        List<V> values = new ArrayList<>();
        Stack<TreeNode<V>> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            final TreeNode<V> top = stack.pop();

            final TreeNode<V> left = top.getLeft();
            final TreeNode<V> right = top.getRight();

            if (right != null)
                stack.push(right);
            if (left != null)
                stack.push(left);

            values.add(top.getValue());
        }

        return values;
    }

    public static <V> List<V> depthFirstRec(TreeNode<V> root) {
        // base case
        if (root == null) {
            return new ArrayList<>();
        }

        final TreeNode<V> left = root.getLeft();
        final TreeNode<V> right = root.getRight();

        final List<V> leftPart = depthFirstRec(left);
        final List<V> rightPart = depthFirstRec(right);

        return new ArrayList<>() {{
            add(root.getValue());
            addAll(leftPart);
            addAll(rightPart);
        }};
    }

    public static <V> List<V> breadthFirst(TreeNode<V> root) {
        List<V> values = new ArrayList<>();
        Queue<TreeNode<V>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            final TreeNode<V> first = queue.remove();

            final TreeNode<V> left = first.getLeft();
            final TreeNode<V> right = first.getRight();



            if (left != null) {
                queue.add(left);
            }

            if (right != null) {
                queue.add(right);
            }

            values.add(first.getValue());
        }

        return values;
    }
}
