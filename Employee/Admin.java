package Employee;

import Customer.PremiumCustomer;
import Customer.PremiumCustomerManager;
import Product.Inventory;
import Product.Product;

public class Admin extends Employee{
    public Admin(String empId, String name, String password, Role role)
    {
        super(empId, name, password, role);
    }

    @Override
    public void greetMessage() {
        System.out.println("Welcome back , admin" + name);
    }

    //only admin can add premium customers and do these functionalities.
    public void registerNewPremiumCustomer(PremiumCustomer pc, PremiumCustomerManager  pcm){
        this.addPremiumCustomer(pc, pcm);
    }
    public void addNewProduct( Inventory inventory ,  Product p){
        inventory.addProduct(p);
    }
    public void removeProduct( Inventory inventory , String id){
        inventory.removeProduct(id);
    }
    public void viewInventory(Inventory inventory){
        inventory.viewAllProducts();
    }

}
