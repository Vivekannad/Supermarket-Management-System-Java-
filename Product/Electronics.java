package Product;

public class Electronics extends Product{

   public Electronics(String id, String name, ProductCategories category, double previousPrice, double discount,  int quantity) {
        super(id, name, category, previousPrice, discount,  quantity);
    }

    @Override
    public Product clone(){
        return new Electronics(productId , name , category , previousPrice , discount, quantity);
    }
}
