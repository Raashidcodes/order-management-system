package com.ecommerce;

public class OrderService {
    public String processOrder(Product product, int quantity, boolean paymentStatus) {
        if (!paymentStatus) return "PAYMENT_FAILED";
        
        if (product.reduceStock(quantity)) {
            return "ORDER_SUCCESS";
        } else {
            return "OUT_OF_STOCK";
        }
    }
}
