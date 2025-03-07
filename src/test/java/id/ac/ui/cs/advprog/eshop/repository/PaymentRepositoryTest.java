package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    private PaymentRepository repository;
    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    void setUp() {
        repository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("123");
        product.setProductName("Test Product");
        product.setProductQuantity(1);
        products.add(product);

        Order order1 = new Order("order1", products, 1708560000L, "Safira");
        Order order2 = new Order("order2", products, 1808680000L, "Anton");

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP1234ABC5678");

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("bankName", "BCA");
        paymentData2.put("referenceCode", "123456");

        payment1 = new Payment(order1, "VOUCHER", paymentData1);
        payment2 = new Payment(order2, "BANK", paymentData2);
    }

    @Test
    void testSaveAndFindById() {
        repository.save(payment1);
        repository.save(payment2);

        assertEquals(payment1, repository.findById(payment1.getPaymentId()));
        assertEquals(payment2, repository.findById(payment2.getPaymentId()));
    }

    @Test
    void testFindByIdNotFound() {
        assertNull(repository.findById("invalid-id"));
    }

    @Test
    void testFindAll() {
        repository.save(payment1);
        repository.save(payment2);

        List<Payment> allPayments = repository.findAll();
        assertEquals(2, allPayments.size());
        assertTrue(allPayments.contains(payment1));
        assertTrue(allPayments.contains(payment2));
    }
}
