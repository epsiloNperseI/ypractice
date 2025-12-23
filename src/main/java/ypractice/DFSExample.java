package ypractice;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DFSExample {

    public static void main(String[] args) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        graph.put(1, List.of(2, 3));
        graph.put(2, List.of(4));
        graph.put(3, List.of(5));
        graph.put(4, List.of());
        graph.put(5, List.of());

        Set<Integer> visited = new HashSet<>();

        dfs(1, graph, visited);
    }

    private static void dfs(int node,
                            Map<Integer, List<Integer>> graph,
                            Set<Integer> visited) {

        System.out.println("visited: " + node);
        System.out.println("visited array: " + visited);
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        System.out.println("visited array added: " + visited);

        for (int neighbor : graph.get(node)) {
            dfs(neighbor, graph, visited);
        }
    }

}
