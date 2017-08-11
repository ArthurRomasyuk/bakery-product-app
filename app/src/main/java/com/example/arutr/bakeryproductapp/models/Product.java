package com.example.arutr.bakeryproductapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Artur Romasiuk
 */

public class Product {

    private String name;
    private String description;
    private String imageUrl;
    private int weight;
    private int price;
    private String category;

    public Product() {
    }

    public Product(String name, String description, int weight, int price, String category, String imageUrl) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return name != null ? name.equals(product.name) : product.name == null;

    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }

}
