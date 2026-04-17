package com.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {
    private OrderService orderService;
    private Product laptop;

    @BeforeEach
    void setUp() {
        orderService = new OrderService();
        laptop = new Product("P001", 10); // Start with 10 items
    }

    @Test
    void testOrderSuccess() {
        String result = orderService.processOrder(laptop, 2, true);
        assertEquals("ORDER_SUCCESS", result);
        assertEquals(8, laptop.getStock());
    }

    @Test
    void testPaymentFailure() {
        String result = orderService.processOrder(laptop, 1, false);
        assertEquals("PAYMENT_FAILED", result);
        assertEquals(10, laptop.getStock()); // Stock should not change
    }

    @Test
    void testOutOfStock() {
        String result = orderService.processOrder(laptop, 15, true);
        assertEquals("OUT_OF_STOCK", result);
        assertEquals(10, laptop.getStock());
    }
}
