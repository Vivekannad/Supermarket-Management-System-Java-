package Product;

public class Cosmetics extends Product{

    public Cosmetics(String id, String name, ProductCategories category, double previousPrice, double discount, int quantity) {
        super(id, name, category, previousPrice, discount, quantity);
    }


    @Override
    public Product clone(){
        return new Cosmetics(productId , name , category , previousPrice , discount, quantity);
    }
}
