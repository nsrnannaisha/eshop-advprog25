package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;

public interface PaymentService {
    Payment processPayment(Payment payment);
    Payment findPaymentById(String paymentId);
    void updatePaymentStatus(String paymentId, String status);
}
