package Product;


public class Grocery extends Product{

    public Grocery(String id, String name, ProductCategories category, double previousPrice, double discount,  int quantity) {
        super(id, name, category, previousPrice, discount, quantity);
    }


    @Override
    public Product clone(){
        return new Grocery(productId , name , category , previousPrice , discount, quantity);
    }
}
