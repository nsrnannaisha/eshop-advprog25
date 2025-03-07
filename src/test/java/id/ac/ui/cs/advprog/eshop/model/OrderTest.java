package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    List<Product> products;

    @BeforeEach
    void setUp() {
        this.products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb5589ff-1c39-460e-8860-71afaf63db6d");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        Product product2 = new Product();
        product2.setProductId("a2c62328-4a37-4664-83c7-f32db8e20155");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1);
        this.products.add(product1);
        this.products.add(product2);
    }

    @Test
    void testCreateOrderEmptyProduct() {
        this.products.clear();

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(
                    "13652556-012a-4c07-b546-54eb139d679b",
                    this.products,
                    1708568000L,
                    "Safina Sudrajat"
            );
        });
    }

    @Test
    void testCreateOrderDefaultStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb139d679b",
                this.products,
                1708568000L,
                "Safina Sudrajat"
        );

        assertSame(this.products, order.getProducts());
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getProductName());
        assertEquals("Sabun Cap Usep", order.getProducts().get(1).getProductName());
        assertEquals("13652556-012a-4c07-b546-54eb139d679b", order.getId());
        assertEquals(1708568000L, order.getOrderTime());
        assertEquals("Safina Sudrajat", order.getAuthor());
        assertEquals("WAITING_PAYMENT", order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb139d679b",
                this.products,
                1708568000L,
                "Safina Sudrajat",
                "SUCCESS"
        );

        assertEquals("SUCCESS", order.getStatus());
    }

    @Test
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order(
                    "13652556-012a-4c07-b546-54eb139d679b",
                    this.products,
                    1708568000L,
                    "Safina Sudrajat",
                    "MEOW"
            );
        });
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb139d679b",
                this.products,
                1708568000L,
                "Safina Sudrajat"
        );
        order.setStatus("CANCELLED");
        assertEquals("CANCELLED", order.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Order order = new Order(
                "13652556-012a-4c07-b546-54eb139d679b",
                this.products,
                1708568000L,
                "Safina Sudrajat"
        );

        assertThrows(IllegalArgumentException.class, () -> order.setStatus("MEOW"));
    }
}
