// auteurs: Liwaa Zebian(20213839) Tarek Radwan(20231177)
// date: 2024-07-25

import java.util.HashMap;
import java.util.Map;

public class DisjointSet<T> {
    private class Node {
        T data;
        Node parent;
        int rank;
    }

    private Map<T, Node> map = new HashMap<>();

    public void makeSet(T data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    public T find(T data) {
        return find(map.get(data)).data;
    }

    private Node find(Node node) {
        if (node.parent == node) {
            return node;
        }
        node.parent = find(node.parent);
        return node.parent;
    }

    public void union(T data1, T data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = find(node1);
        Node parent2 = find(node2);

        if (parent1 == parent2) {
            return;
        }

        if (parent1.rank >= parent2.rank) {
            if (parent1.rank == parent2.rank) {
                parent1.rank++;
            }
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
    }
}
