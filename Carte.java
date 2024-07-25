// auteurs: Liwaa Zebian(20213839) Tarek Radwan(20231177)
// date: 2024-07-25

import java.util.*;

public class Carte {
    private List<Site> sites;
    private List<Rue> rues;

    public Carte() {
        sites = new ArrayList<>();
        rues = new ArrayList<>();
    }

    public void addSite(Site site) {
        sites.add(site);
    }

    public void addRue(Rue rue) {
        rues.add(rue);
    }

    public List<Site> getSites() {
        return sites;
    }

    public List<Rue> getRues() {
        return rues;
    }

    public List<Rue> kruskalMST() {
        List<Rue> mst = new ArrayList<>();
        Collections.sort(rues);

        DisjointSet<Site> disjointSet = new DisjointSet<>();
        for (Site site : sites) {
            disjointSet.makeSet(site);
        }

        for (Rue rue : rues) {
            Site root1 = disjointSet.find(rue.getSite1());
            Site root2 = disjointSet.find(rue.getSite2());

            if (!root1.equals(root2)) {
                mst.add(rue);
                disjointSet.union(root1, root2);
            }
        }

        return mst;
    }

    public int getTotalCost(List<Rue> mst) {
        return mst.stream().mapToInt(Rue::getCost).sum();
    }
}
