package com.ecommerce;

public class Product {
    private String id;
    private int stock;

    public Product(String id, int stock) {
        this.id = id;
        this.stock = stock;
    }

    public synchronized boolean reduceStock(int quantity) {
        if (this.stock >= quantity) {
            this.stock -= quantity;
            return true;
        }
        return false;
    }

    public int getStock() { return stock; }
}
