// auteurs: Liwaa Zebian(20213839) Tarek Radwan(20231177)
// date: 2024-07-25

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a disjoint set (union-find) data structure.
 * It is used to manage the connected components in Kruskal's algorithm.
 */
public class DisjointSet<T> {
    // Inner class to represent a node in the disjoint set
    private class Node {
        T data;
        Node parent;
        int rank;
    }

    // Map to store the nodes corresponding to the data elements
    private Map<T, Node> map = new HashMap<>();

    //Creates a new set containing the specified data element.
    public void makeSet(T data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    //Finds the representative of the set containing the specified data element
    public T find(T data) {
        return find(map.get(data)).data;
    }

    // Helper method to find the representative of the set containing the specified node
    private Node find(Node node) {
        if (node.parent == node) {
            return node;
        }
        node.parent = find(node.parent); // Path compression
        return node.parent;
    }

    //Unites the sets containing the two specified data elements.
    public void union(T data1, T data2) {
        Node node1 = map.get(data1);
        Node node2 = map.get(data2);

        Node parent1 = find(node1);
        Node parent2 = find(node2);

        if (parent1 == parent2) {
            return;
        }

        // Union by rank
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
