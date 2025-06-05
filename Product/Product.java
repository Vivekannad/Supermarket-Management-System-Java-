package Product;

public abstract class Product {
    String productId , name ;
    ProductCategories category;
    double previousPrice , discount , finalPrice;
    int quantity;

    Product(String id , String name , ProductCategories category , double previousPrice , double discount ,  int quantity){
        this.productId = id;
        this.name = name ;
        this.category = category;
        this.previousPrice = previousPrice;
        this.discount = discount;
        this.finalPrice = previousPrice - discount;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public ProductCategories getCategory() {
        return category;
    }

    public double getPreviousPrice() {
        return previousPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    /* This is a built-in method which we will over-ride in sub-classes
    and other classes as well where Product type is used.
    We are doing this because we do not want customer basket and the product to have same reference
    because if customer modifies it quantity of a thing buying , product class is also modified
     */
    public abstract Product clone();
}
