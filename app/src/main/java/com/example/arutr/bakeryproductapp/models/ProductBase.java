package com.example.arutr.bakeryproductapp.models;


import android.util.Log;
import java.util.ArrayList;
import java.util.Random;

public class ProductBase {

    private ArrayList<Product> products;

    public ProductBase() {
        products = new ArrayList<>();

        products.add (new Product("BANANA", "Almost a pound of bananas in each cake, with chopped walnuts and our smooth cream-cheese " +
                "frosting. No other banana cake compares.", 900, 5, "Cakes" ,"http://www.frostbakeshop.com/assets/1950/1_banana.jpg"));
        products.add(new Product("CARAMEL", "A moist yellow cake frosted with buttery caramel frosting - simple, yet simply delicious." +
                " You've never had caramel cake until you've tried this one.", 850, 4, "Cakes" ,"http://www.frostbakeshop.com/assets/1950/1_banana.jpg"));
        products.add(new Product("CARROT", "This cake is loaded with carrots for incredible moistness and has chopped raisins and" +
                " walnuts. Finished with cream cheese frosting. This is a very heavy, rich and spicy cake you are sure to enjoy.", 1000, 7, "Cakes" ,
                "http://www.frostbakeshop.com/assets/1950/2_dsc_2051.jpg"));
        products.add(new Product("KEY LIME PIE", "A smooth, sweet yet tart filling in a Graham cracker crust make this a pie" +
                " for Key Lime lovers. Topped with lightly sweetened heavy cream.", 600, 3, "Pies" ,
                "http://www.frostbakeshop.com/assets/1950/2_10_pie_slice_key_lime.jpg"));
        products.add(new Product("CHOCOLATE SILK", "No pudding here! Butter, eggs and the best cocoa available make a rich smooth filling." +
                " In a pastry crust and topped with lightly sweetened heavy cream and grated chocolate.", 800, 7, "Pies" ,
                "http://www.frostbakeshop.com/assets/1950/2_10_pie_slice_key_lime.jpg"));
        products.add(new Product("CHOCOLATE CHEESECAKE", "Cream cheese, eggs and chocolate in an Oreo crust with a sour cream top and chocolate " +
                "ganache drizzle. Wow!", 500, 14, "Cheesecakes" ,
                "http://www.frostbakeshop.com/assets/1950/2_13_chz_choc_slice.jpg"));
        products.add(new Product("ORIGINAL CHEESECAKE", "For the purist. Vanilla cheesecake with a Graham cracker crumb crust and sour cream top.",
                600, 18, "Cheesecakes" ,
                "http://www.frostbakeshop.com/assets/1950/2_dsc_2010.jpg"));


    }
    public int getProductCount() {
        return products.size();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
