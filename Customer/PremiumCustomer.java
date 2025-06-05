package Customer;

import Product.Product;
import Transaction.Bill;

import java.util.List;

public class PremiumCustomer extends Customer{
    String customerId , name;
    private double loyaltyPoints = 0;

    public PremiumCustomer(String customerId, String name){
        this.customerId = customerId;
        this.name = name;
    }

    public void setBasket(List<Product> b2){
        basket = b2;
    }

    @Override
    public void checkOut(Bill bill, String name) {
        System.out.println("Here is your bill");
        bill.printBill(name);
    }
    public String getName(){
        return name;
    }

    public double getLoyaltyPoints(){
        return loyaltyPoints;
    }
    public void setLoyaltyPoints(double points){
        this.loyaltyPoints = points;
    }
}
