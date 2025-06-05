import Customer.NormalCustomer;
import Customer.PremiumCustomer;
import Customer.PremiumCustomerManager;
import Employee.*;
import Product.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Scanner input = new Scanner(System.in);
    static StoreEmployees employees = new StoreEmployees();
    static Inventory inventory = new Inventory();
    static PremiumCustomerManager pcm = new PremiumCustomerManager();
    static Admin rehman = new Admin("A-1", "rehman", "223", Role.ADMIN);
    static Cashier abdullah = new Cashier("C-1", "abdullah", "333", Role.CASHIER);

    public static void main(String[] args) {
        initializeData();
        mainMenu();
    }

    static void initializeData() {
        employees.addAdmin(rehman);
        employees.addCashier(abdullah);

        inventory.addProduct(new Grocery("P-223", "tomato", ProductCategories.GROCERY, 20, 2, 50));
        inventory.addProduct(new Grocery("P-224", "Potato", ProductCategories.GROCERY, 40, 10, 200));
        inventory.addProduct(new Electronics("P-100", "Iron", ProductCategories.ELECTRONICS, 2000, 100, 10));
        inventory.addProduct(new Electronics("P-101", "Kettle", ProductCategories.ELECTRONICS , 5000 , 1000 , 20));
        inventory.addProduct(new Cosmetics("P-301", "Lipstick", ProductCategories.COSMETICS , 300 , 50 , 50));

        pcm.addPremiumCustomer(new PremiumCustomer("c1", "Alice"));
    }

    static void mainMenu() {
        while (true) {
            try {
                System.out.println("Enter your role (Customer / Employee / Exit)");
                String role = input.next();
                switch (role.toUpperCase()) {
                    case "CUSTOMER" -> handleCustomer();
                    case "EMPLOYEE" -> handleEmployee();
                    case "EXIT" -> {
                        System.out.println("Exiting the program");
                        return;
                    }
                    default -> System.out.println("Invalid option. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
                input.nextLine();
            }
        }
    }

    static void handleCustomer() {
        boolean exitFlag = false;
        System.out.println("Welcome to Supermarket");
        NormalCustomer normalCustomer = new NormalCustomer();

        while (!exitFlag) {
            System.out.println("Select the category you want to walk in");
            printCategories();
            String category = input.next().toUpperCase();
            List<Product> items;
            try {
                items = inventory.searchByCategory(category);
                if (items == null || items.isEmpty()) throw new Exception("No item with the given category");
                items.forEach(i -> System.out.printf("Name: %s | Prev Price: %.1f | Discount: %.1f | Final: %.1f | Stock: %d\n",
                        i.getName(), i.getPreviousPrice(), i.getDiscount(), i.getFinalPrice(), i.getQuantity()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            Product cartedItem = null;
                    input.nextLine();   // buffer clean
            while (cartedItem == null) {
                try {
                    System.out.println("Enter the item name you want to purchase: ");
                    String item = input.nextLine();
                    cartedItem = inventory.searchByName(item);
                    if (cartedItem == null) System.out.println("Item not found. Try again.");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                    input.nextLine();
                }
            }

            int qty = 0;
            while (qty <= 0 || qty > cartedItem.getQuantity() ) {
                try {
                    System.out.print("Quantity: ");
                    qty = input.nextInt();
                    if (qty <= 0) System.out.println("Quantity must be greater than zero.");
                    else if (qty > cartedItem.getQuantity()) {
                        System.out.println("Insufficient stock.");
                        qty = 0;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Enter a number.");
                    input.nextLine();
                }
            }

            normalCustomer.addToBasket(cartedItem, qty);

            exitFlag = getYesOrNo("Do you want to go to counter? (true/false)");
        }

        boolean isPremium = getYesOrNo("Are you a premium customer? (true/false)");
        if (isPremium) handlePremiumCustomer(normalCustomer);
        else abdullah.checkOut(normalCustomer);

    }


    static void printCategories() {
        for (ProductCategories p : ProductCategories.values()) {
            System.out.print(p + " ");
        }
        System.out.println();
    }

    static void handlePremiumCustomer(NormalCustomer nc) {
        input.nextLine();
        System.out.println("Enter your customer Id");
        String pcId = input.nextLine();
        System.out.println("Enter your name");
        String pcName = input.nextLine();

        PremiumCustomer isPC = pcm.searchPremiumCustomer(pcId);
        if (isPC != null && isPC.getName().equalsIgnoreCase(pcName)) {
            isPC.setBasket(nc.getBasket());
            abdullah.checkOut(isPC);
        } else {
            System.out.println("Incorrect credentials.");
            abdullah.checkOut( nc);
        }
    }

    static boolean getYesOrNo(String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return input.nextBoolean();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter true or false.");
                input.nextLine();
            }
        }
    }


    static void handleEmployee() {
        System.out.println("Select your role (admin/cashier)");
        String role = input.next();
        switch (role.toUpperCase()) {
            case "ADMIN" -> handleAdmin();
            case "CASHIER" -> handleCashier();
            default -> System.out.println("Invalid role");
        }
    }

    static void handleAdmin() {
        Admin admin = handleAdminLogin();
        if (admin == null) {
            System.out.println("Incorrect credentials");
            return;
        }
        while (true) {
            adminMenu();
            try {
                int choice = input.nextInt();
                input.nextLine();   // CLEANS THE BUFFER
                switch (choice) {
                    case 1 -> addNewItem( inventory , admin);
                    case 2 -> removeItem( inventory , admin);
                    case 3 -> registerNewPremiumCustomer(admin);
                    case 4 -> viewInventory(inventory, admin);
                    case 5 -> {
                        return;
                    }
                    default -> System.out.println("Invalid option");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number.");
                input.nextLine();
            }
        }
    }

    static void handleCashier() {
        Cashier cashier = handleCashierLogin();
        if (cashier == null) {
            System.out.println("Incorrect credentials");
            return;
        }
        while (true) {
            cashierMenu();
            try {
                int choice = input.nextInt();
                input.nextLine();
                switch (choice) {
                    case 1 -> searchItemById(cashier);
                    case 2 -> searchItemByName(cashier);
                    case 3 -> {
                        return;
                    }
                    default -> System.out.println("Not an option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a number");
                input.nextLine();
            }
        }
    }


    static Admin handleAdminLogin() {
        try {
            System.out.println("Enter your id:");
            input.nextLine();
            String id = input.nextLine();

            System.out.println("Enter your password:");
            String password = input.nextLine();

            Admin admin = employees.logInAdmin(id, password);
            if (admin == null) throw new Exception("Invalid admin credentials.");
            return admin;
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }

    static void adminMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1) Add new item");
        System.out.println("2) Remove item");
        System.out.println("3) Register new premium customer");
        System.out.println("4) View inventory");
        System.out.println("5) Exit");
    }

    static void addNewItem( Inventory inventory ,  Admin admin) {
        try {
            System.out.println("Enter product Id:");
            String id = input.next();
            input.nextLine(); // clear buffer

            System.out.println("Enter product name:");
            String name = input.nextLine();

            System.out.println("Enter product category (GROCERY, ELECTRONICS, COSMETICS):");
            String category = input.next().toUpperCase();

            ProductCategories categoryInput;
            try {
                categoryInput = ProductCategories.valueOf(category);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category entered.");
                return;
            }

            System.out.println("Enter product price:");
            double price = input.nextDouble();

            System.out.println("Enter discount:");
            double discount = input.nextDouble();

            System.out.println("Enter stock quantity:");
            int qty = input.nextInt();
            input.nextLine(); // clear buffer

            Product product;
            switch (categoryInput) {
                case GROCERY -> product = new Grocery(id, name, categoryInput, price, discount, qty);
                case ELECTRONICS -> product = new Electronics(id, name, categoryInput, price, discount, qty);
                case COSMETICS -> product = new Cosmetics(id, name, categoryInput, price, discount, qty);
                default -> {
                    System.out.println("Unexpected category.");
                    return;
                }
            }

            admin.addNewProduct( inventory , product);
            System.out.println(" Product added successfully.");
        } catch (InputMismatchException e) {
            System.out.println(" Invalid input. Please enter numeric values for price, discount, and quantity.");
            input.nextLine(); // flush input buffer
        } catch (Exception e) {
            System.out.println(" Error adding product: " + e.getMessage());
        }
    }

    static void removeItem( Inventory inventory ,  Admin admin) {
        try {
            System.out.println("Enter product Id:");
            String id = input.next();
            input.nextLine();

            admin.removeProduct( inventory , id);
            System.out.println(" Item removed.");
        } catch (Exception e) {
            System.out.println(" Error removing item: " + e.getMessage());
        }
    }

    static void registerNewPremiumCustomer(Admin admin) {
        try {
            System.out.println("Enter customer Id:");
            String id = input.next();
            input.nextLine();

            System.out.println("Enter customer name:");
            String name = input.nextLine();

            admin.registerNewPremiumCustomer(new PremiumCustomer(id, name), pcm);
            System.out.println(" Premium customer registered.");
        } catch (Exception e) {
            System.out.println(" Failed to register premium customer: " + e.getMessage());
        }
    }

    static void viewInventory( Inventory inventory , Admin admin) {
        try {
            admin.viewInventory(inventory);
        } catch (Exception e) {
            System.out.println(" Error viewing inventory: " + e.getMessage());
        }
    }

    static Cashier handleCashierLogin() {
        try {
            System.out.println("Enter your cashier-id:");
            String id = input.next();
            input.nextLine();

            System.out.println("Enter your password:");
            String password = input.nextLine();

            Cashier cashier = employees.logInCashier(id, password);
            if (cashier == null) throw new Exception("Invalid cashier credentials.");
            return cashier;
        } catch (Exception e) {
            System.out.println("Login failed: " + e.getMessage());
            return null;
        }
    }

    static void cashierMenu() {
        System.out.println("\n--- Cashier Menu ---");
        System.out.println("1) Search product by ID");
        System.out.println("2) Search product by Name");
        System.out.println("3) to exit");
    }

    static void searchItemById(Cashier cashier) {
        try {
            System.out.println("Enter product Id:");
            String id = input.next();
            Product item = cashier.searchProductById( inventory, id);

            if (item == null) throw new Exception("Product not found.");
            System.out.printf("Name: %s | Prev Price: %.1f | Discount: %.1f | Final: %.1f | Stock: %d\n",
                    item.getName(), item.getPreviousPrice(), item.getDiscount(), item.getFinalPrice(), item.getQuantity());
        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }

    static void searchItemByName(Cashier cashier) {
        try {
            System.out.println("Enter product name:");
            String name = input.next();
            Product item = cashier.searchProductByName( inventory , name);

            if (item == null) throw new Exception("Product not found.");
            System.out.printf("Name: %s | Prev Price: %.1f | Discount: %.1f | Final: %.1f | Stock: %d\n",
                    item.getName(), item.getPreviousPrice(), item.getDiscount(), item.getFinalPrice(), item.getQuantity());

        } catch (Exception e) {
            System.out.println( e.getMessage());
        }
    }
}
