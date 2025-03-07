package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private final List<Payment> payments = new ArrayList<>();

    public Payment save(Payment payment) {
        if (payment.getStatus() == null) {
            payment.setStatus(PaymentStatus.PENDING.getValue());
        }
        payments.add(payment);
        return payment;
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

    public void updateStatus(String paymentId, String newStatus) {
        Payment payment = findById(paymentId);
        if (payment != null) {
            payment.setStatus(newStatus);
        }
    }
}
