package Product;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Product> products;
    // dynamic array
    // arrayList


    public Inventory(){
        this.products  = new ArrayList<>();
    }

    public void addProduct(Product product){
     products.add(product);
    }
    public void removeProduct(String productId){    //223 = tomato
      this.products = products.stream().filter(u -> !(u.productId.equals(productId))).toList();

    }

    public void viewAllProducts(){
        products.forEach(p -> System.out.println(p.name));
    }

    public Product searchById(String productId){
      return   products.stream().filter(u -> u.productId.equals(productId)).findFirst().orElse(null);
    }
    public Product searchByName(String name){
        return   products.stream().filter(u -> u.name.equalsIgnoreCase(name)).findFirst().orElse(null);
    }
    public List<Product> searchByCategory(String category) {
        ProductCategories categoryInput;

        try {
            categoryInput = ProductCategories.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Illegal category: " + e.getMessage());
            return new ArrayList<>(); // return empty list if invalid
        }

        return products.stream()
                .filter(p -> p.category == categoryInput) // or use equals if category is String
                .toList();
    }


}
