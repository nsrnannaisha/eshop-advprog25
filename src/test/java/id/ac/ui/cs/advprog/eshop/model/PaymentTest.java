package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private List<Order> orders;
    private List<Product> products;

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

        this.orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b", this.products, 1708560000L, "Safira Sudarajat");
        Order order2 = new Order("12938476-011a-4c06-b534-44eb1396d69b", this.products, 1808680000L, "Anton Sutanto");

        orders.add(order1);
        orders.add(order2);
    }

    @Test
    void testCreatePaymentVoucherInvalidLength() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC");

        Payment payment = new Payment("a1b2c3d4-e5f6-7890-abcd-ef1234567890", orders.get(0), "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherMissingESHOPPrefix() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "SHOP12345678ABCD");

        Payment payment = new Payment("b2c3d4e5-f6g7-890a-bcde-f12345678901", orders.get(0), "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherMissingNumerical() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOPABCDEFGHABCD");

        Payment payment = new Payment("c3d4e5f6-7890-abcd-ef12-345678901234", orders.get(0), "VOUCHER", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentVoucherValid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        Payment payment = new Payment("d4e5f6g7-890a-bcde-f123-456789012345", orders.get(0), "VOUCHER", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankMissingBankName() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("referenceCode", "987654");

        Payment payment = new Payment("e5f6g7h8-90ab-cdef-1234-567890123456", orders.get(1), "BANK", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankMissingReferenceCode() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "");

        Payment payment = new Payment("f6g7h8i9-0abc-def1-2345-678901234567", orders.get(1), "BANK", paymentData);

        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testCreatePaymentBankValid() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "BCA");
        paymentData.put("referenceCode", "123456");

        Payment payment = new Payment("g7h8i9j0-1abc-def2-3456-789012345678", orders.get(1), "BANK", paymentData);

        assertEquals("SUCCESS", payment.getStatus());
    }
}
