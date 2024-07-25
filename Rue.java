// auteurs: Liwaa Zebian(20213839) Tarek Radwan(20231177)
// date: 2024-07-25

//This class represents a Rue (edge) in the graph with a cost.
public class Rue implements Comparable<Rue> {
    // The name of the rue
    private String name;
    // The first site (node) connected by the rue
    private Site site1;
    // The second site (node) connected by the rue
    private Site site2;
    // The cost of installing the rue
    private int cost;

    //Constructor to initialize the Rue with a name, two sites, and a cost.
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

    /**
     * Overrides the compareTo method to compare Rues based on cost, then by site names.
     * @param other The other Rue to compare to
     * @return A negative integer, zero, or a positive integer as this Rue is less than,
     *         equal to, or greater than the specified Rue
     */
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

    //Overrides the toString method to return the rue's name, sites, and cost.
    @Override
    public String toString() {
        return name + "\t" + site1 + "\t" + site2 + "\t" + cost;
    }
}
