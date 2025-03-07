package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();

    public void save(Payment payment) {
        payments.add(payment);
    }

    public Payment findById(String paymentId) {
        for (Payment payment : payments) {
            if (payment.getPaymentId().equals(paymentId)) {
                return payment;
            }
        }
        return null;
    }

    public List<Payment> findAll() {
        return new ArrayList<>(payments);
    }
}
