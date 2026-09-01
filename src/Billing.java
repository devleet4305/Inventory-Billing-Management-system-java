/**
 * Handles bill calculation for a sale: subtotal, discount, tax, and
 * final payable amount.
 */
public class Billing {
    private static final double TAX_RATE = 0.05;       // 5% tax
    private static final double DISCOUNT_THRESHOLD = 1000.0;
    private static final double DISCOUNT_RATE = 0.05;   // 5% discount on large orders

    private final double subtotal;
    private final double discount;
    private final double tax;
    private final double total;

    public Billing(double unitPrice, int quantity) {
        subtotal = unitPrice * quantity;
        discount = (subtotal >= DISCOUNT_THRESHOLD) ? subtotal * DISCOUNT_RATE : 0.0;
        double afterDiscount = subtotal - discount;
        tax = afterDiscount * TAX_RATE;
        total = afterDiscount + tax;
    }

    public double getSubtotal() { return subtotal; }
    public double getDiscount() { return discount; }
    public double getTax() { return tax; }
    public double getTotal() { return total; }
}