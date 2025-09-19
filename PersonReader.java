import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PersonReader {

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

        ArrayList<Person> people = new ArrayList<>();

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
                if (cols.length < 5) {
                    System.err.println("Skipping malformed row (need 5 columns): " + line);
                    continue;
                }

                String firstName = cols[0].trim();
                String lastName = cols[1].trim();
                String id = cols[2].trim();
                String title = cols[3].trim();
                int yob;

                try {
                    yob = Integer.parseInt(cols[4].trim());
                } catch (NumberFormatException nfe) {
                    System.err.println("Invalid YOB, skipping row: " + line);
                    continue;
                }

                Person p = new Person(firstName, lastName, id, title, yob);
                people.add(p);
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        printTable(people);
    }

    private static File chooseCSVFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select Person CSV");
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

        return (lower.contains("first") && lower.contains("last"))
                || lower.contains("yob")
                || lower.contains("title")
                || lower.contains("id");
    }

    private static void printTable(ArrayList<Person> people) {
        if (people.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        String fmtHeader = "%-12s %-12s %-10s %-8s %4s%n";
        String fmtRow = "%-12s %-12s %-10s %-8s %4d%n";

        System.out.println();
        System.out.printf("Loaded %d Person record%s%n", people.size(), people.size() == 1 ? "" : "s");
        System.out.println(".¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°");
        System.out.printf(fmtHeader, "First Name", "Last Name", "ID", "Title", "YOB");
        System.out.println(".¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°");

        for (Person p : people) {
            System.out.printf(
                    fmtRow,
                    p.getFirstName(),
                    p.getLastName(),
                    p.getID(),
                    p.getTitle(),
                    p.getYOB());
        }
        System.out.println(".¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°.¸.·°¯°·.¸.·°¯°·.¸.·°");
    }
}
