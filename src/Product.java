/**
 * Represents a single product record in the inventory.
 */
public class Product {
    private final int id;
    private final String name;
    private final double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    public void reduceStock(int amount) { this.quantity -= amount; }

    @Override
    public String toString() {
        return String.format("%-5d %-18s Tk.%-10.2f %-5d", id, name, price, quantity);
    }
}