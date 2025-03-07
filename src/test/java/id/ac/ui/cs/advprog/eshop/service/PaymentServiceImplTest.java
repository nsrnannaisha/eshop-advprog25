package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImplTest implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImplTest(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment processPayment(Payment payment) {
        if (isValidPayment(payment)) {
            payment.setStatus(PaymentStatus.SUCCESS.getValue());
            return paymentRepository.save(payment);
        } else {
            payment.setStatus(PaymentStatus.REJECTED.getValue());
            return paymentRepository.save(payment);
        }
    }

    private boolean isValidPayment(Payment payment) {
        return payment.getStatus().equals(PaymentStatus.SUCCESS.getValue());
    }

    @Override
    public Payment findPaymentById(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public void updatePaymentStatus(String paymentId, String status) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment != null) {
            payment.setStatus(status);
            paymentRepository.save(payment);
        } else {
            throw new IllegalArgumentException("Payment not found with id: " + paymentId);
        }
    }
}
