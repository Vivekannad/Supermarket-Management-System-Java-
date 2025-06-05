package Customer;

import Product.Product;
import Transaction.Bill;

import java.util.ArrayList;
import java.util.List;

abstract public class Customer {
    List<Product> basket;


    Customer(){
        this.basket = new ArrayList<>();
    }

    public void addToBasket(Product originalProduct, int quantity){
        // tomato = 10
        Product copiedProduct = originalProduct.clone();
        for(Product b: basket){
            if(b.getName().equalsIgnoreCase(originalProduct.getName())){
                b.setQuantity(b.getQuantity() + quantity);
                originalProduct.setQuantity(originalProduct.getQuantity() - quantity);
                return;
            }
        }
        copiedProduct.setQuantity(quantity);
        basket.add(copiedProduct);
        originalProduct.setQuantity(originalProduct.getQuantity() - quantity);
    }

    public void viewBasket(){
        basket.forEach(p -> System.out.println(p.getName()));
    }
    public List<Product> getBasket(){
        return basket;
    }
    abstract public void checkOut(Bill bill, String name);
}
