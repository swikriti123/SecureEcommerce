package com.example.ecosmetics.Model;

public class Category {

    private String image;
    private String category;

    public Category(String image, String category) {
        this.image = image;
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
