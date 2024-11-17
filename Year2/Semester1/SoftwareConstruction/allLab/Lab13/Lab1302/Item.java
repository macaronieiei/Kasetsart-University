package Lab13.Lab1302;  

import java.util.ArrayList;
import java.util.Scanner;

public class Item {
    private String name;
    private double price;
    private int quantity;

    public Item(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public double calTotal() {
        return price * quantity;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return String.format("%-20s", name) + "\t\t" + String.format("$%-10.2f", price) + "\t\t" + quantity + "\t\t"
                + String.format("$%-8.2f", calTotal());
    }

}