# Inventory and Billing Management System

A console-based Java application for small shop owners to manage product stock and generate customer bills.

**Course:** Object-Oriented Programming II Lab (CSE 2110)

## Features

- **Add Products** - Add new products to the inventory with name, price, and quantity
- **Search Products** - Search products by ID or name
- **Sell Products** - Process sales with automatic billing and stock reduction
- **Generate Billing** - Calculate subtotal, discount (for orders ≥ Tk. 1000), and tax (5%)
- **View Stock Report** - Display all products sorted by price (ascending order)
- **Inventory Management** - Track stock levels and prevent overselling

## Project Structure

```
src/
├── InventoryBillingSystem.java  - Main application with menu and I/O handling
├── Inventory.java               - Manages product array and operations
├── Product.java                 - Represents a single product record
└── Billing.java                 - Handles bill calculation and tax/discount
```

## Compilation

```bash
javac -d bin src/*.java
```

This compiles all Java source files and places the .class files in the `bin/` directory.

## Running the Application

```bash
java -cp bin InventoryBillingSystem
```

## Usage

### Main Menu Options

1. **Add Product** - Add a new product to the inventory
   - Enter product name, price (in Tk.), and quantity
   - System assigns an automatic ID

2. **Search Product** - Find a product by ID or name
   - Displays product details if found

3. **Sell Product (Billing)** - Process a sale and generate a receipt
   - Enter product ID and quantity
   - Automatic discount applied for orders ≥ Tk. 1000 (5% discount)
   - Tax calculated at 5% on discounted amount
   - Receipt displayed with all details

4. **View Stock Report** - Display all products sorted by price
   - Shows products from lowest to highest price
   - Displays total inventory value

5. **Exit** - Close the application

## Key Components

### Product Class
- Stores: ID, Name, Price, Quantity
- Methods: getId(), getName(), getPrice(), getQuantity(), reduceStock()

### Inventory Class
- Stores up to 100 products in an array
- Methods: addProduct(), findById(), findByName(), getProductsSortedByPrice(), getTotalInventoryValue()

### Billing Class
- Calculates: Subtotal, Discount (5% for orders ≥ Tk. 1000), Tax (5%), Total
- Automatically applies discount threshold logic

### InventoryBillingSystem Class
- Main entry point with menu-driven interface
- Input validation for integers and decimals
- Handles all user interactions

## Input Validation

- All numeric inputs are validated
- Invalid entries prompt the user to re-enter
- Stock availability checked before processing sales

## Example Session

```
Enter your choice: 1
-- Add Product --
Enter product name: Laptop
Enter price: Tk. 50000
Enter quantity: 5
Product added successfully. Assigned ID: 1

Enter your choice: 3
-- Sell Product / Billing --
Enter product ID to sell: 1
Enter quantity to sell: 1
============ RECEIPT ============
Product   : Laptop
Quantity  : 1
Subtotal  : Tk. 50000.00
Discount  : Tk. 0.00
Tax (5%)  : Tk. 2500.00
TOTAL     : Tk. 52500.00
==================================
```

## Notes

- Maximum 100 products in inventory
- Prices in Bangladeshi Taka (Tk.)
- Automatic ID assignment starts from 1
- Case-insensitive product name search
- System prevents overselling
