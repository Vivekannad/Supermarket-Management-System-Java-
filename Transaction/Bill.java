package Transaction;

import Customer.Customer;
import Product.Product;
import Customer.PremiumCustomer;

import java.util.List;

public class Bill {
    Customer customer;
    List<Product> basket;
    double total ;
    double discount;
    double finalTotal;

    public Bill(Customer customer, List<Product> basket, double total, double discount) {
        this.customer = customer;
        this.basket = basket;
        this.total = total;
        this.discount = discount;
        this.finalTotal = total - discount;
    }

    public void printBill(String cashierName) {
        System.out.printf("%-15s %-10s %-10s %-12s %-10s %-10s%n",
                "Product", "Price", "Discount", "Final Price", "Quantity", "Total Price");
        for (Product b : basket) {
            System.out.printf("%-15s %-10.2f %-10.2f %-12.2f %-10d %-10.2f%n",
                    b.getName(),
                    b.getPreviousPrice(),
                    b.getDiscount(),
                    b.getFinalPrice(),
                    b.getQuantity(),
                    b.getFinalPrice() * b.getQuantity());
        }
        System.out.printf("%n%-58s %-10.2f%n", "Total:", total);
        System.out.printf("%-58s %-10.2f%n", "A.discount:", discount);
        System.out.printf("%-58s %-10.2f%n", "Final Price:", finalTotal);
        if(customer instanceof PremiumCustomer){
        System.out.printf("%-58s %-10.2f%n%n", "Loyalty Points:" , ((PremiumCustomer) customer).getLoyaltyPoints());
        }
        System.out.println("Cashier: " + cashierName);
    }


    public List<Product> getProducts() {
        return this.basket;
    }
}
