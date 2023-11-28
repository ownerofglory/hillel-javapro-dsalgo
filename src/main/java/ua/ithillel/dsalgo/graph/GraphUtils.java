package ua.ithillel.dsalgo.graph;

import java.util.*;

public class GraphUtils {
    public static <V> List<V> depthFirst(Map<V, List<V>> graph, V from) {
        List<V> values = new ArrayList<>();
        Stack<V> stack = new Stack<>();

        stack.push(from);

        while (!stack.isEmpty()) {
            final V top = stack.pop();

            if (values.contains(top)) {
                continue;
            }

            final List<V> neighbours = graph.get(top);
            for (V neighbour :
                    neighbours) {
                stack.push(neighbour);
            }

            values.add(top);
        }


        return values;
    }

    public static <V> Map<V, List<V>> edgeListToAdjacency(V[][] edgeList) {
        Map<V,List<V>> adjList = new HashMap<>();

        for (V[] edge:
             edgeList) {

            final V begin = edge[0];
            final V end = edge[1];

            if (!adjList.containsKey(begin)) {
                adjList.put(begin, new ArrayList<>());
            }

            if (!adjList.containsKey(end)) {
                adjList.put(end, new ArrayList<>());
            }

            adjList.get(begin).add(end);
            adjList.get(end).add(begin);

        }

        return adjList;
    }
}
