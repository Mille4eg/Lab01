import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();

        System.out.println("Enter product records. Leave ID empty to stop.");

        while (true) {
            System.out.print("ID (blank to quit): ");
            String id = in.nextLine().trim();
            if (id.isEmpty())
                break;

            System.out.print("Name: ");
            String name = in.nextLine().trim();

            System.out.print("Description: ");
            String desc = in.nextLine().trim();

            System.out.print("Cost: ");
            double cost = Double.parseDouble(in.nextLine().trim());

            products.add(new Product(id, name, desc, cost));
            System.out.println("Product added!\n");
        }

        System.out.print("Enter output file name (without .csv): ");
        String filename = in.nextLine().trim() + ".csv";

        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            out.println("ID,Name,Description,Cost");
            for (Product p : products) {
                out.printf("%s,%s,%s,%.2f%n",
                        p.getID(), p.getName(), p.getDescription(), p.getCost());
            }
            System.out.println("Data written to " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
