package Customer;

import Transaction.Bill;

public class NormalCustomer extends Customer{
    @Override
    public void  checkOut(Bill bill , String name) {
        System.out.println("Here is your Bill");
        bill.printBill(name);
    }
}
