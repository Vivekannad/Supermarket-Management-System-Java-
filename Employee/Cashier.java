package Employee;

import Customer.Customer;
import Product.Inventory;
import Product.Product;
import Transaction.Bill;
import Customer.PremiumCustomer;

import java.util.List;
import java.util.Scanner;

public class Cashier extends Employee{

    private final Scanner input = new Scanner(System.in);
    public Cashier(String empId, String name, String password, Role role) {
        super(empId, name, password, role);
    }

    @Override
    public void greetMessage() {
        System.out.println("Welcome back , cashier " + name);
    }

    public Product searchProductById(Inventory inventory,String id){
        return inventory.searchById(id);
    }

    public Product searchProductByName( Inventory inventory , String name){
        return inventory.searchByName(name);
    }

    public Bill generateBill(Customer customer){
        List<Product> basket = customer.getBasket();
        double total = 0, discount = 0;
        for(Product b : basket){
            total += (b.getFinalPrice() * b.getQuantity());
        }
        if(customer instanceof PremiumCustomer){
        System.out.println("Your total is " + total);
            PremiumCustomer pc = ((PremiumCustomer) customer);
            calculateLoyaltyPoints( pc, total);
            System.out.printf("You have got %.2f loyalty points, do you wish to avail those? (true-false) ", pc.getLoyaltyPoints());
             if(input.nextBoolean()){
                discount = calculateLoyaltyPointsDiscount(pc , total );

             }
        }
        return new Bill(customer , basket , total, discount);
    }

    public void calculateLoyaltyPoints(PremiumCustomer pc , double total){
        double points =  pc.getLoyaltyPoints() + (total / 100);
        pc.setLoyaltyPoints(points);
    }

    public double calculateLoyaltyPointsDiscount(PremiumCustomer pc, double total ){
       double dis = pc.getLoyaltyPoints() * 10;
       if(dis > total){
         double timesLess =  ((dis - total) / 10);
         dis = (pc.getLoyaltyPoints() - timesLess) * 10;
         pc.setLoyaltyPoints(pc.getLoyaltyPoints() - timesLess);
       }
       return dis;
    }

    public void checkOut(Customer customer){
       Bill bill = generateBill(customer);

        customer.checkOut(bill, this.name);

        customer.getBasket().clear();
    }

}
