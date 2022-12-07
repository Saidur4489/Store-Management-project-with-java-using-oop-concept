import java.util.ArrayList;
import java.util.Scanner;

public class Storemanager {
    private static ArrayList<Product> products = new ArrayList();
    private static ArrayList<Order> orders = new ArrayList();
    private static Scanner input=new Scanner(System.in);

    public Storemanager() {
    }

    public static void main(String[] args) {
        while(true) {
            System.out.println("------------Store management system!-------------- \nPlease select an option:");
            System.out.println("1. View products");
            System.out.println("2. Add product");
            System.out.println("3. Place order");
            System.out.println("4. View orders");
            System.out.println("5. Exit");
            int option = input.nextInt();
            input.nextLine();
            if (option == 1) {
                viewProducts();
            } else if (option == 2) {
                addProduct();
            } else if (option == 3) {
                placeOrder();
            } else if (option == 4) {
                viewOrders();
            } else {
                if (option == 5) {
                    System.out.println("Thank you for using our store management system!");
                    return;
                }

                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void viewProducts() {
        System.out.println("PRODUCTS:");
        for (Product product : products) {
            System.out.println(product.getName() + " (BDT " + product.getPrice() + ")");
        }
    }

    public static void addProduct() {
        System.out.print("Enter product name: ");
        String name = input.nextLine();
        System.out.print("Enter product price: ");
        double price = input.nextDouble();

        Product product = new Product(name, price);
        products.add(product);

        System.out.println("Product added: " + product.getName() + " (BDT " + product.getPrice() + ")");
    }

    public static void placeOrder() {
        System.out.println("Enter customer name: ");
        String customerName = input.nextLine();
        Order order = new Order(customerName);

        while(true) {
            System.out.print("Enter product name (or 'checkout' to finish): ");
            String productName = input.nextLine();
            if (productName.equals("checkout")) {
                orders.add(order);
                System.out.println("Order placed successfully! Order details:");
                System.out.println("Customer name: " + order.getCustomerName());
                System.out.println("Total cost: BDT " + order.getTotalCost());
                return;
            }

            Product product = findProduct(productName);
            if (product == null) {
                System.out.println("Invalid product name. Please try again.");
            } else {
                order.addProduct(product);
            }
        }
    }

    public static void viewOrders() {
        System.out.println("ORDERS");
        for (Order order : orders) {
            System.out.println("Customer name: " + order.getCustomerName());
            System.out.println("Total cost: BDT " + order.getTotalCost());
            System.out.println("Product details: ");
            for (Product product : order.getProducts()) {
                System.out.println(product.getName() + " (BDT " + product.getPrice() + ")");
            }
        }
    }



    public static Product findProduct(String name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
}
