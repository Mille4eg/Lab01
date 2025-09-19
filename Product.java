public class Product {
    private final String id;
    private final String name;
    private final String description;
    private final double cost;

    public Product(String id, String name, String description, double cost) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.cost = cost;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - %s : $%.2f", name, id, description, cost);
    }
}
