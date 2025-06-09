package com.example.afinal;

import java.io.Serializable;

public class Item implements Serializable {

    private String ID;
    private String Name, Brand, Color, Price, Category;
    private String Image;

    public Item(String name, String brand, String color, String category, String image, String price) {
        this.Name = name;
        this.Brand = brand;
        this.Color = color;
        this.Image = image;
        this.Price = price;
        this.Category = category;
        this.ID = String.valueOf(System.currentTimeMillis());
    }

    public Item(String name, String brand, String color, String category, String image, String price, String id){
        this.Name = name;
        this.Brand = brand;
        this.Color = color;
        this.Image = image;
        this.Price = price;
        this.Category = category;
        this.ID = id;
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

    public String getImage() {
        return Image;
    }

    public String getID() {
        return ID;
    }
}
