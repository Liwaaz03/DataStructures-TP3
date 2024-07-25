// auteurs: Liwaa Zebian(20213839) Tarek Radwan(20231177)
// date: 2024-07-25

import java.io.*;
import java.util.*;

public class Tp3 {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.out.println("Usage: java Tp3 carte.txt arm.txt");
            return;
        }

        String inputFile = args[0];
        String outputFile = args[1];

        Carte carte = new Carte();
        readInputFile(inputFile, carte);
        List<Rue> mst = carte.kruskalMST();
        writeOutputFile(outputFile, carte, mst);
    }

    private static void readInputFile(String inputFile, Carte carte) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            boolean readingSites = true;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.equals("---")) {
                    readingSites = !readingSites;
                    continue;
                }

                if (readingSites) {
                    carte.addSite(new Site(line));
                } else {
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

    private static void writeOutputFile(String outputFile, Carte carte, List<Rue> mst) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            for (Site site : carte.getSites()) {
                writer.write(site.getName());
                writer.newLine();
            }

            mst.sort((r1, r2) -> {
                String site1NameR1 = r1.getSite1().getName();
                String site2NameR1 = r1.getSite2().getName();
                if (site1NameR1.compareTo(site2NameR1) > 0) {
                    String temp = site1NameR1;
                    site1NameR1 = site2NameR1;
                    site2NameR1 = temp;
                }
                String site1NameR2 = r2.getSite1().getName();
                String site2NameR2 = r2.getSite2().getName();
                if (site1NameR2.compareTo(site2NameR2) > 0) {
                    String temp = site1NameR2;
                    site1NameR2 = site2NameR2;
                    site2NameR2 = temp;
                }

                int compareSite1 = site1NameR1.compareTo(site1NameR2);
                if (compareSite1 != 0) {
                    return compareSite1;
                } else {
                    return site2NameR1.compareTo(site2NameR2);
                }
            });

            for (Rue rue : mst) {
                String site1Name = rue.getSite1().getName();
                String site2Name = rue.getSite2().getName();
                if (site1Name.compareTo(site2Name) > 0) {
                    String temp = site1Name;
                    site1Name = site2Name;
                    site2Name = temp;
                }
                writer.write(rue.getName() + "\t" + site1Name + "\t" + site2Name + "\t" + rue.getCost());
                writer.newLine();
            }

            writer.write("---");
            writer.newLine();
            writer.write(String.valueOf(carte.getTotalCost(mst)));
        }
    }
}
