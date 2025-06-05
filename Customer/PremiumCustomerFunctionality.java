package Customer;

public interface PremiumCustomerFunctionality {
    void addPremiumCustomer(PremiumCustomer p);
    PremiumCustomer searchPremiumCustomer(String id);
    void removePremiumCustomer(String id);
    void showAllPremiumCustomers();
}
