package Employee;

import Customer.PremiumCustomer;
import Customer.PremiumCustomerManager;
import Product.Inventory;

abstract public class Employee {
    String empId , name , password;
    Role role;



    public Employee(String empId, String name, String password, Role role) {
        this.empId = empId;
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public boolean logIn(String empId , String password){
        return this.empId.equals(empId) && this.password.equals(password);
    }
    abstract public void greetMessage();

    /*
    The reason I am making these functions again is
    if I let Employee subclass have access to the class directly ,
    they can do anything , it is risky.
    That's why I have made premiumCustomerManager property private
    and made these functions protected so that they can only be accessed inside this package
    and by subclasses.
     */

    protected PremiumCustomer getPremiumCustomerById(String id , PremiumCustomerManager pcm){
        return pcm.searchPremiumCustomer(id);
    }
    protected void addPremiumCustomer(PremiumCustomer pc, PremiumCustomerManager pcm){
        pcm.addPremiumCustomer(pc);
        System.out.println("Registered");
        pcm.showAllPremiumCustomers();
    }

    protected void showAllPremiumCustomers(PremiumCustomerManager pcm){
        pcm.showAllPremiumCustomers();
    }

}
