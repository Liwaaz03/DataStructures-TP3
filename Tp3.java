// auteurs: Liwaa Zebian(20213839) Tarek Radwan(20231177)
// date: 2024-07-25

import java.io.*;
import java.util.*;

/**
 * The main class for the program. It reads input, processes the graph, and writes output.
 */
public class Tp3 {
    /**
     * The main method that drives the program.
     * @param args Command line arguments (input file and output file)
     * @throws IOException If an I/O error occurs
     */
    public static void main(String[] args) throws IOException {
        // Check if the correct number of arguments is provided
        if (args.length != 2) {
            System.out.println("Usage: java Tp3 carte.txt arm.txt");
            return;
        }

        // Assign input and output file names from command line arguments
        String inputFile = args[0];
        String outputFile = args[1];

        // Create an instance of Carte to represent the graph
        Carte carte = new Carte();
        // Read the input file to populate the Carte instance
        readInputFile(inputFile, carte);
        // Find the Arbre de Recouvrement Minimal (ARM) using Kruskal's algorithm
        List<Rue> arm = carte.kruskalARM();
        // Write the ARM to the output file
        writeOutputFile(outputFile, carte, arm);
    }

    /**
     * Reads the input file and populates the Carte instance with sites and rues.
     * @param inputFile The input file name
     * @param carte The Carte instance to populate
     * @throws IOException If an I/O error occurs
     */
    private static void readInputFile(String inputFile, Carte carte) throws IOException {
        // Open the input file for reading
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean readingSites = true; // Flag to indicate if we are reading sites

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                line = line.trim(); // Remove leading and trailing whitespace
                if (line.equals("---")) {
                    readingSites = !readingSites; // Toggle the flag when encountering ---
                    continue;
                }

                // If reading sites, add each line as a site to the Carte
                if (readingSites) {
                    carte.addSite(new Site(line));
                } else {
                    // Otherwise, parse the line as a rue and add it to the Carte
                    String[] parts = line.split(":|;");
                    String rueName = parts[0].trim();
                    String[] siteAndCost = parts[1].trim().split(" ");
                    Site site1 = new Site(siteAndCost[0]);
                    Site site2 = new Site(siteAndCost[1]);
                    int cost = Integer.parseInt(siteAndCost[2]);
                    carte.addRue(new Rue(rueName, site1, site2, cost));
                }
            }
        }
    }

    /**
     * Writes the ARM to the output file in the specified format.
     * @param outputFile The output file name
     * @param carte The Carte instance containing the graph
     * @param arm The list of rues in the ARM
     * @throws IOException If an I/O error occurs
     */
    private static void writeOutputFile(String outputFile, Carte carte, List<Rue> arm) throws IOException {
        // Open the output file for writing
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            // Write each site to the file
            for (Site site : carte.getSites()) {
                writer.write(site.getName());
                writer.newLine();
            }

            // Sort the rues in the ARM by the departure site and then by the arrival site
            arm.sort((r1, r2) -> {
                // Get the names of the sites in r1
                String site1NameR1 = r1.getSite1().getName();
                String site2NameR1 = r1.getSite2().getName();
                // Ensure site1NameR1 is lexicographically less than site2NameR1
                if (site1NameR1.compareTo(site2NameR1) > 0) {
                    String temp = site1NameR1;
                    site1NameR1 = site2NameR1;
                    site2NameR1 = temp;
                }

                // Get the names of the sites in r2
                String site1NameR2 = r2.getSite1().getName();
                String site2NameR2 = r2.getSite2().getName();
                // Ensure site1NameR2 is lexicographically less than site2NameR2
                if (site1NameR2.compareTo(site2NameR2) > 0) {
                    String temp = site1NameR2;
                    site1NameR2 = site2NameR2;
                    site2NameR2 = temp;
                }

                // Compare the departure sites
                int compareSite1 = site1NameR1.compareTo(site1NameR2);
                if (compareSite1 != 0) {
                    return compareSite1;
                } else {
                    // Compare the arrival sites if departure sites are the same
                    return site2NameR1.compareTo(site2NameR2);
                }
            });

            // Write each rue in the ARM to the file
            for (Rue rue : arm) {
                String site1Name = rue.getSite1().getName();
                String site2Name = rue.getSite2().getName();
                // Ensure site1Name is lexicographically less than site2Name
                if (site1Name.compareTo(site2Name) > 0) {
                    String temp = site1Name;
                    site1Name = site2Name;
                    site2Name = temp;
                }
                writer.write(rue.getName() + "\t" + site1Name + "\t" + site2Name + "\t" + rue.getCost());
                writer.newLine();
            }

            // Write the separator and the total cost of the ARM
            writer.write("---");
            writer.newLine();
            writer.write(String.valueOf(carte.getTotalCost(arm)));
        }
    }
}
