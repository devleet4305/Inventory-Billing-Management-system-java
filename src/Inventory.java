/**
 * Manages the in-memory array of Product records: adding, searching,
 * sorting (bubble sort), and reporting.
 */
public class Inventory {
    private static final int MAX_PRODUCTS = 100;
    private final Product[] products;
    private int count;
    private int nextId;

    public Inventory() {
        products = new Product[MAX_PRODUCTS];
        count = 0;
        nextId = 1;
    }

    public boolean isFull() { return count >= MAX_PRODUCTS; }
    public int getCount() { return count; }

    /** Adds a new product to the array and returns its assigned ID. */
    public int addProduct(String name, double price, int quantity) {
        if (isFull()) return -1;
        int id = nextId++;
        products[count] = new Product(id, name, price, quantity);
        count++;
        return id;
    }

    /** Linear search by product ID. Returns null if not found. */
    public Product findById(int id) {
        for (int i = 0; i < count; i++) {
            if (products[i].getId() == id) {
                return products[i];
            }
        }
        return null;
    }

    /** Linear search by product name (case-insensitive, partial match). */
    public Product findByName(String name) {
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equalsIgnoreCase(name)) {
                return products[i];
            }
        }
        return null;
    }

    /**
     * Returns a copy of the current product array sorted by price
     * (ascending) using bubble sort.
     */
    public Product[] getProductsSortedByPrice() {
        Product[] sorted = new Product[count];
        for (int i = 0; i < count; i++) {
            sorted[i] = products[i];
        }
        // Bubble sort
        for (int i = 0; i < sorted.length - 1; i++) {
            for (int j = 0; j < sorted.length - 1 - i; j++) {
                if (sorted[j].getPrice() > sorted[j + 1].getPrice()) {
                    Product temp = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = temp;
                }
            }
        }
        return sorted;
    }

    public double getTotalInventoryValue() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total += products[i].getPrice() * products[i].getQuantity();
        }
        return total;
    }
}