import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ProductReader {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        File csvFile = chooseCSVFile();
        if (csvFile == null) {
            System.out.println("No file selected. Exiting.");
            return;
        }

        ArrayList<Product> products = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty())
                    continue;

                if (firstLine && looksLikeHeader(line)) {
                    firstLine = false;
                    continue;
                }
                firstLine = false;

                String[] cols = line.split(",", -1);
                if (cols.length < 4) {
                    System.err.println("Skipping malformed row: " + line);
                    continue;
                }

                String id = cols[0].trim();
                String name = cols[1].trim();
                String desc = cols[2].trim();
                double cost;

                try {
                    cost = Double.parseDouble(cols[3].trim());
                } catch (NumberFormatException nfe) {
                    System.err.println("Invalid cost, skipping row: " + line);
                    continue;
                }

                products.add(new Product(id, name, desc, cost));
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        printTable(products);
    }

    private static File chooseCSVFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Product CSV");
        chooser.setAcceptAllFileFilterUsed(true);
        chooser.setFileFilter(new FileNameExtensionFilter("CSV files", "csv"));
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        }
        return null;
    }

    private static boolean looksLikeHeader(String line) {
        String lower = line.toLowerCase();
        return lower.contains("id") && lower.contains("name");
    }

    private static void printTable(ArrayList<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        String fmtHeader = "%-8s %-15s %-25s %-8s%n";
        String fmtRow = "%-8s %-15s %-25s $%-8.2f%n";

        System.out.println();
        System.out.printf("Loaded %d Product record%s%n",
                products.size(), products.size() == 1 ? "" : "s");
        System.out.println(".¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°");
        System.out.printf(fmtHeader, "ID", "Name", "Description", "Cost");
        System.out.println("-.¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°");

        for (Product p : products) {
            System.out.printf(fmtRow,
                    p.getID(), p.getName(), p.getDescription(), p.getCost());
        }
        System.out.println(".¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°");
    }
}
