import java.util.Scanner;

/**
 * Inventory and Billing Management System
 * A console-based Java application for small shop owners to manage
 * product stock and generate customer bills.
 *
 * Course: Object-Oriented Programming II Lab (CSE 2110)
 */
public class InventoryBillingSystem {
    private static final Inventory inventory = new Inventory();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("   INVENTORY AND BILLING MANAGEMENT SYSTEM");
        System.out.println("=================================================");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1: addProduct(); break;
                case 2: searchProduct(); break;
                case 3: sellProduct(); break;
                case 4: viewReport(); break;
                case 5:
                    running = false;
                    System.out.println("\nThank you for using the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select 1-5.\n");
            }
        }
        sc.close();
    }

    private static void printMenu() {
        System.out.println("\n--------------- MENU ---------------");
        System.out.println("1. Add Product");
        System.out.println("2. Search Product");
        System.out.println("3. Sell Product (Billing)");
        System.out.println("4. View Stock Report (sorted by price)");
        System.out.println("5. Exit");
        System.out.println("-------------------------------------");
    }

    private static void addProduct() {
        System.out.println("\n-- Add Product --");
        if (inventory.isFull()) {
            System.out.println("Inventory is full. Cannot add more products.");
            return;
        }
        System.out.print("Enter product name: ");
        String name = sc.nextLine();
        double price = readDouble("Enter price: Tk. ");
        int qty = readInt("Enter quantity: ");

        int id = inventory.addProduct(name, price, qty);
        System.out.println("Product added successfully. Assigned ID: " + id);
    }

    private static void searchProduct() {
        System.out.println("\n-- Search Product --");
        System.out.print("Search by (1) ID or (2) Name? ");
        int mode = readInt("");
        Product p;
        if (mode == 1) {
            int id = readInt("Enter product ID: ");
            p = inventory.findById(id);
        } else {
            System.out.print("Enter product name: ");
            String name = sc.nextLine();
            p = inventory.findByName(name);
        }

        if (p == null) {
            System.out.println("Product not found.");
        } else {
            System.out.println("ID    Name               Price        Qty");
            System.out.println(p);
        }
    }

    private static void sellProduct() {
        System.out.println("\n-- Sell Product / Billing --");
        int id = readInt("Enter product ID to sell: ");
        Product p = inventory.findById(id);
        if (p == null) {
            System.out.println("Product not found.");
            return;
        }
        int qty = readInt("Enter quantity to sell: ");
        if (qty > p.getQuantity()) {
            System.out.println("Insufficient stock. Available: " + p.getQuantity());
            return;
        }

        Billing bill = new Billing(p.getPrice(), qty);
        p.reduceStock(qty);

        System.out.println("\n============ RECEIPT ============");
        System.out.println("Product   : " + p.getName());
        System.out.println("Quantity  : " + qty);
        System.out.printf ("Subtotal  : Tk. %.2f%n", bill.getSubtotal());
        System.out.printf ("Discount  : Tk. %.2f%n", bill.getDiscount());
        System.out.printf ("Tax (5%%)  : Tk. %.2f%n", bill.getTax());
        System.out.printf ("TOTAL     : Tk. %.2f%n", bill.getTotal());
        System.out.println("==================================");
    }

    private static void viewReport() {
        System.out.println("\n-- Stock Report (sorted by price, ascending) --");
        Product[] sorted = inventory.getProductsSortedByPrice();
        if (sorted.length == 0) {
            System.out.println("No products in inventory.");
            return;
        }
        System.out.println("ID    Name               Price        Qty");
        for (Product p : sorted) {
            System.out.println(p);
        }
        System.out.printf("%nTotal Inventory Value: Tk. %.2f%n", inventory.getTotalInventoryValue());
    }

    // ---- Input helper methods ----
    private static int readInt(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine(); // consume newline
        return val;
    }

    private static double readDouble(String prompt) {
        System.out.print(prompt);
        while (!sc.hasNextDouble()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }
        double val = sc.nextDouble();
        sc.nextLine();
        return val;
    }
}