package Customer;

import java.util.ArrayList;
import java.util.List;

public class PremiumCustomerManager implements PremiumCustomerFunctionality {
    private List<PremiumCustomer> premiumCustomers;
    public PremiumCustomerManager(){
        this.premiumCustomers = new ArrayList<>();
    }

    @Override
    public void addPremiumCustomer(PremiumCustomer pc) {
        premiumCustomers.add(pc);
    }

    @Override
    public PremiumCustomer searchPremiumCustomer(String id) {
     return   premiumCustomers.stream().filter(p -> p.customerId.equals(id)).findFirst().orElse(null);

    }

    @Override
    public void removePremiumCustomer(String id) {
      this.premiumCustomers =  premiumCustomers.stream().filter(p -> !p.customerId.equals(id)).toList();
    }

    @Override
    public void showAllPremiumCustomers() {
    premiumCustomers.forEach(p -> System.out.println(p.name));
    }
}
