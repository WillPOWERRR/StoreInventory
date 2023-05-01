package com.learntocode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Store {

    private static List<Product> inventory;
    private static List<Product> cart;

    public static void main(String[] args) {
        loadInventory();
        cart = new ArrayList<>();
        showHomeScreen();
    }

    public static void loadInventory() {
        inventory = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader("Inventory.csv"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Product product = new Product(parts[0], parts[1], Double.parseDouble(parts[2]));
                inventory.add(product);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading inventory.");
            System.exit(1);
        }
    }

    public static void showHomeScreen() {
        System.out.println("Welcome to our Online Store!");
        System.out.println("Please select an option:");
        System.out.println("1. Show Products");
        System.out.println("2. Show Cart");
        System.out.println("3. Exit");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter option number: ");
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                showProducts();
                break;
            case 2:
                showCart();
                break;
            case 3:
                System.out.println("Thank you for shopping with us!");
                System.exit(0);
            default:
                System.out.println("Invalid option. Please try again.");
                System.out.println();
                showHomeScreen();
                break;
        }
    }public static void showCart() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
        } else {
            System.out.println("Items in your cart:");
            System.out.println("ID\tName\t\t\t\tPrice");
            for (Product product : cart) {
                System.out.println(product.getId() + "\t" + product.getName() + "\t\t$" + product.getPrice());
            }
        }
        System.out.println();
        showHomeScreen();
    }
    public static Product findProductById(String productId) {
        for (Product product : inventory) {
            if (product.getId().equals(productId)) {
                return product;
            }
        }
        return null;
    }
    public static void showProducts() {
        System.out.println("List of Products:");
        System.out.println("ID\tName\t\t\t\tPrice");
        for (Product product : inventory) {
            System.out.println(product.getId() + "\t" + product.getName() + "\t\t$" + product.getPrice());
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter product ID to add to cart or X to go back to the home screen: ");
        String productId = scanner.nextLine();
        if (productId.equalsIgnoreCase("X")) {
            showHomeScreen();
        } else {
            Product product = findProductById(productId);
            if (product != null) {
                cart.add(product);
                System.out.println(product.getName() + " added to cart.");
                System.out.println();
                showHomeScreen();
            } else {
                System.out.println("Invalid product ID. Please try again.");
                System.out.println();
                showProducts();
            }
        }

    }
}