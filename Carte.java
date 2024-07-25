// auteurs: Liwaa Zebian(20213839) Tarek Radwan(20231177)
// date: 2024-07-25

import java.util.*;

//This class represents a Carte (graph) consisting of sites (nodes) and rues (edges).
public class Carte {
    // List of sites in the graph
    private List<Site> sites;
    // List of rues in the graph
    private List<Rue> rues;

    public Carte() {
        sites = new ArrayList<>();
        rues = new ArrayList<>();
    }

    //Adds a site to the graph.
    public void addSite(Site site) {
        sites.add(site);
    }

    //Adds a rue to the graph.
    public void addRue(Rue rue) {
        rues.add(rue);
    }

    public List<Site> getSites() {
        return sites;
    }

    public List<Rue> getRues() {
        return rues;
    }

    //Implements Kruskal's algorithm to find the Arbre de Recouvrement Minimal (ARM) of the graph.
    public List<Rue> kruskalARM() {
        List<Rue> arm = new ArrayList<>();
        Collections.sort(rues);

        // Disjoint set to manage the connected components of the graph
        DisjointSet<Site> disjointSet = new DisjointSet<>();
        for (Site site : sites) {
            disjointSet.makeSet(site);
        }

        for (Rue rue : rues) {
            Site root1 = disjointSet.find(rue.getSite1());
            Site root2 = disjointSet.find(rue.getSite2());

            // Add the rue to the ARM if it connects two different components
            if (!root1.equals(root2)) {
                arm.add(rue);
                disjointSet.union(root1, root2);
            }
        }

        return arm;
    }

    //Calculates the total cost of the rues in the ARM.
    public int getTotalCost(List<Rue> arm) {
        return arm.stream().mapToInt(Rue::getCost).sum();
    }
}
