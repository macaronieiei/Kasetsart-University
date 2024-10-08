package lap4;

import java.util.Scanner;

public class Product {
    private int code;
    private String name;
    private double price;

    public Product() {
        this.code = 0;
        this.name = "";
        this.price = 0;
    }

    public Product(int code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public static void main(String[] args) {
        Product[] product = new Product[5]; 
        product[0] = new Product(0, "Mama", 5.5);
        product[1] = new Product(10, "Lays", 10.5);
        int count = 20;
        
        Scanner input = new Scanner(System.in);
        
        // Enter Product detail code 20, 30, 40, 50
        System.out.println("======== Enter Product Details ========");
        for (int i = 2; i < 5; i++) {
            product[i] = new Product();
            product[i].setCode(count);
            System.out.println("Product code " + count);
            System.out.print("Name: ");
            product[i].setName(input.nextLine());
            System.out.print("Price: ");
            product[i].setPrice(input.nextDouble());
            input.nextLine();
            System.out.println();
            count += 10;
        }

        // List of Products
        System.out.println("======== List of Products ========");
        for (int j = 0; j < product.length; j++) {
            System.out.println("Product code " + product[j].getCode());
            System.out.println("Name: " + product[j].getName() + " , Price: " + product[j].getPrice());
            System.out.println();
        }
        
        // What do you want to buy
        System.out.println("======== What do you want to buy? ========");
        System.out.println("Enter product code (press -1 to exit) ");
        
        double total = 0;
        while (true) {
            System.out.print("Enter product code : ");
            int enterCode = input.nextInt();
            if (enterCode == -1) {
                break;
            }
            boolean found = false;
            for (int i = 0; i < product.length; i++) {
                if (product[i].getCode() == enterCode) {
                    found = true;
                    System.out.print("Amount of " + product[i].getName() + ": ");
                    int amount = input.nextInt();
                    total += product[i].getPrice() * amount;
                    break;
                }
            }
        }
        System.out.println("Total amount: " + total);
        input.close();
    }
}