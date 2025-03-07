package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import java.util.Map;

@Getter
public class Payment {
    private String paymentId;
    private Order order;
    private String method;
    private Map<String, String> paymentData;
    private String status;

    public Payment(String paymentId, Order order, String method, Map<String, String> paymentData) {
        this.paymentId = paymentId;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        validatePayment();
    }

    private void validatePayment() {
        switch (method) {
            case "VOUCHER":
                validateVoucher();
                break;
            case "BANK":
                validateBankTransfer();
                break;
            default:
                this.status = PaymentStatus.REJECTED.getValue();
        }
    }

    private void validateVoucher() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode != null && voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                voucherCode.replaceAll("[^0-9]", "").length() == 8) {
            setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    private void validateBankTransfer() {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        if (bankName != null && !bankName.isEmpty() &&
                referenceCode != null && !referenceCode.isEmpty()) {
            setStatus(PaymentStatus.SUCCESS.getValue());
        } else {
            setStatus(PaymentStatus.REJECTED.getValue());
        }
    }

    public void setStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.status = status;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
