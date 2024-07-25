// auteurs: Liwaa Zebian(20213839) Tarek Radwan(20231177)
// date: 2024-07-25

//This class represents a Site (node) in the graph.
public class Site {
    // The name of the site
    private String name;

    public Site(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Overrides the equals method to compare Sites based on their name.
     * @return True if the names are the same, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Site site = (Site) obj;
        return name.equals(site.name);
    }

    /**
     * Overrides the hashCode method to generate a hash based on the site's name.
     * @return The hash code of the site's name
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Overrides the toString method to return the site's name.
     * @return The name of the site
     */
    @Override
    public String toString() {
        return name;
    }
}
