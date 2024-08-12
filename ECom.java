import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Product {
    String name;
    double price;

    Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " - $" + price;
    }
}

class Cart {
    private List<Product> products;

    Cart() {
        products = new ArrayList<>();
    }

    void addProduct(Product product) {
        products.add(product);
        System.out.println(product.name + " added to cart.");
    }

    void viewCart() {
        if (products.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            double total = 0;
            System.out.println("Cart contents:");
            for (Product product : products) {
                System.out.println(product);
                total += product.price;
            }
            System.out.println("Total: $" + total);
        }
    }

    double getTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.price;
        }
        return total;
    }
}

public class ECom {
    private static List<Product> productList = new ArrayList<>();
    private static Cart cart = new Cart();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Sample products
        productList.add(new Product("Laptop", 999.99));
        productList.add(new Product("Smartphone", 499.99));
        productList.add(new Product("Headphones", 89.99));

        int choice;

        do {
            System.out.println("\nE-Commerce Platform");
            System.out.println("1. List Products");
            System.out.println("2. Add Product to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout (Simulate Payment)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    listProducts();
                    break;

                case 2:
                    addToCart(scanner);
                    break;

                case 3:
                    cart.viewCart();
                    break;

                case 4:
                    checkout();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void listProducts() {
        System.out.println("Available Products:");
        for (int i = 0; i < productList.size(); i++) {
            System.out.println((i + 1) + ". " + productList.get(i));
        }
    }

    private static void addToCart(Scanner scanner) {
        listProducts();
        System.out.print("Enter product number to add to cart: ");
        int productNumber = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        if (productNumber > 0 && productNumber <= productList.size()) {
            Product selectedProduct = productList.get(productNumber - 1);
            cart.addProduct(selectedProduct);
        } else {
            System.out.println("Invalid product number!");
        }
    }

    private static void checkout() {
        cart.viewCart();
        System.out.print("Proceed to checkout? (yes/no): ");
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        if ("yes".equalsIgnoreCase(response)) {
            double totalAmount = cart.getTotal();
            System.out.println("Processing payment of $" + totalAmount + "...");
            System.out.println("Payment successful! Thank you for your purchase.");
        } else {
            System.out.println("Checkout canceled.");
        }
    }
}
