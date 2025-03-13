package com.example.afinal;
import java.io.Serializable;

public class Item implements Serializable {

    private String Name, Brand, Color, Price, Category;
    private int Image;

    public Item(String name, String brand, String color, String category, int image, String price) {
        this.Name = name;
        this.Brand = brand;
        this.Color = color;
        this.Image = image;
        this.Price = price;
        this.Category = category;
    }

    public String getName() {
        return Name;
    }

    public String getBrand() {
        return Brand;
    }

    public String getColor() {
        return Color;
    }

    public String getPrice() {
        return Price;
    }

    public String getCategory() {
        return Category;
    }

    public int getImage() {
        return Image;
    }
}
