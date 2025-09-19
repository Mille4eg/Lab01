import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();

        System.out.println("Slap ya info in. Leave ID empty to stop.");

        while (true) {
            System.out.print("ID (blank to quit): ");
            String id = in.nextLine().trim();
            if (id.isEmpty())
                break;

            System.out.print("First Name: ");
            String first = in.nextLine().trim();

            System.out.print("Last Name: ");
            String last = in.nextLine().trim();

            System.out.print("Title: ");
            String title = in.nextLine().trim();

            System.out.print("Year of Birth (YOB): ");
            int yob = Integer.parseInt(in.nextLine().trim());

            people.add(new Person(id, first, last, title, yob));
            System.out.println("Record added!\n");
        }

        System.out.print("Enter output file name (without .csv): ");
        String filename = in.nextLine().trim() + ".csv";

        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println("ID,Firstname,Lastname,Title,YOB");
            for (Person p : people) {
                out.printf("%s,%s,%s,%s,%d%n",
                        p.getID(), p.getFirstName(), p.getLastName(),
                        p.getTitle(), p.getYOB());
            }
            System.out.println("Data written to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
