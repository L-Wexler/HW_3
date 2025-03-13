package com.example.afinal;

public class Category {
    private String Category;
    private int Image;

    public Category(String category, int image){
        this.Category = category;
        this.Image = image;
    }

    public String getCategory() {
        return Category;
    }

    public int getImage(){
        return Image;
    }
}

