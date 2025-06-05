# 🛒 Retail Management System

A Java-based console application that simulates a retail store's backend operations for **Admins**, **Cashiers**, and **Customers**. The system allows inventory management, secure login, customer registration, and product search capabilities.

---

## 📋 Features

### 👨‍💼 Admin Module
- Secure admin login
- Add new products (GROCERY, ELECTRONICS, COSMETICS)
- Remove existing products by ID
- Register new premium customers
- View complete inventory

### 👨‍💻 Cashier Module
- Secure cashier login
- Search products by ID or name
- View product details (name, price, discount, quantity)
- Generate a printable customer bill with total and final prices

### 👤 Customer Module
- Regular and premium customer distinction
- Premium customers receive additional discounts
- Basket system to hold selected items
- Bill printing functionality

---

## ⚙️ Tech Stack

- **Language**: Java
- **Input**: Scanner (console-based UI)
- **Architecture**: Object-Oriented Design
- **No external libraries used**

---

## 📁 Project Structure

- `Retail-Management-System/`
  - `src/`
    - `Main.java` - `Main class`
    - `products/`
      - `Product.java`  -  `Abstract class`
      - `Grocery.java`  -  `Class`
      - `Electronics.java`  -  `Class`
      - `Cosmetics.java`  -  `Class`
      - `Inventory.java`  -  `Class`
      - `ProductCategories.java`  -  `Enum`
    - `Employee/`
      - `Employee.java` -  `Abstract class`
      - `Admin.java` -  `Class`
      - `Cashier.java` -  `Class`
      - `StoreEmployees.java` -  `Class`
      - `Role.java` -  `Enum`
   - `Customer/`
      - `Customer.java` -  `Abstract class`
      - `NormalCustomer.java` -  `Class`
      - `PremiumCustomerManager.java` -  `Class`
      - `PremiumCustomer.java` -  `Class`
      - `PremiumCustomerFunctionality.java` -  `Class`
   - `Transaction/`
      - `Bill.java` -  `Class`
  - `README.md`
  - `.gitignore`

---

## ✅ How to Run

1. **Clone or download** this repo.
2. Compile all \`.java\` files:

   \`\`\`bash
   javac *.java
   \`\`\`

3. Run the main program:

   \`\`\`bash
   java Main
   \`\`\`

---

## 🧪 Sample Credentials

> You can edit the \`Employees\` class to pre-register Admins and Cashiers.

\`\`\`java
// Sample admin
Admin admin = new Admin("admin123", "password123");

// Sample cashier
Cashier cashier = new Cashier("cashier01", "1234");
\`\`\`

---

## 🧠 Concepts Practiced

- Object-Oriented Programming (Abstraction, Inheritance, Polymorphism)
- Exception Handling (\`try-catch\`)
- Enum usage
- Dynamic object creation based on category
- Clean input validation and user prompts

---

## 📌 Future Enhancements

- GUI using JavaFX or Swing
- File-based or database storage for persistence
- Unit testing with JUnit
- Logging system
- Role-based access control

---

## 🙌 Contributors

Made with ❤️ by **[Vivek Anand]**