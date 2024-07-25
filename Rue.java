// auteurs: Liwaa Zebian(20213839) Tarek Radwan(20231177)
// date: 2024-07-25

public class Rue implements Comparable<Rue> {
    private String name;
    private Site site1;
    private Site site2;
    private int cost;

    public Rue(String name, Site site1, Site site2, int cost) {
        this.name = name;
        this.site1 = site1;
        this.site2 = site2;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public Site getSite1() {
        return site1;
    }

    public Site getSite2() {
        return site2;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Rue other) {
        if (this.cost != other.cost) {
            return Integer.compare(this.cost, other.cost);
        } else {
            int site1Comparison = this.site1.getName().compareTo(other.site1.getName());
            if (site1Comparison != 0) {
                return site1Comparison;
            } else {
                return this.site2.getName().compareTo(other.site2.getName());
            }
        }
    }

    @Override
    public String toString() {
        return name + " " + site1 + " " + site2 + " " + cost;
    }
}
