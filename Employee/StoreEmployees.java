package Employee;

import java.util.ArrayList;
import java.util.List;

public class StoreEmployees {
    private List<Cashier> allCashiers;
    private List<Admin> allAdmins;

    public StoreEmployees() {
        this.allCashiers = new ArrayList<>();
        this.allAdmins = new ArrayList<>();
    }

    public void addCashier(Cashier c){
        allCashiers.add(c);
    }
    public void removeCashier(String id){
       this.allCashiers = allCashiers.stream().filter(c -> !c.empId.equals(id)).toList();
    }
    public void addAdmin(Admin ad){
        allAdmins.add(ad);
    }
    public void removeAdmin(String id){
        this.allAdmins = allAdmins.stream().filter(a -> !a.empId.equals(id)).toList();
    }

    public Cashier logInCashier(String empId , String password){
        // if cashier is in the records and not some any other.
        for(Cashier c : allCashiers){
            if(c.logIn(empId , password)){
                return c;
            }
        }
        return null;
    }
    public Admin logInAdmin(String empId , String password){
        // if admin is in the records and not some any other.

        for(Admin a : allAdmins){
            if(a.logIn(empId , password)){
                return a;
            }
        }
        return null;
    }

}
