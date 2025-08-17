package com.example.afinal;

import java.io.Serializable;

public class Category implements Serializable {
    private String ID;
    private String Category;
    private String Image;

    public Category(String category, String id, String image){
        this.ID = String.valueOf(System.currentTimeMillis());
        this.Category = category;
        this.Image = image;
    }

    public String getID() {
        return ID;
    }
    public String getCategory() {
        return Category;
    }

    public String getImage(){
        return Image;
    }
}

