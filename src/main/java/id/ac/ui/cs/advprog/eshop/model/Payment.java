package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Map;

@Getter
@Setter
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
                this.status = "REJECTED";
        }
    }

    private void validateVoucher() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode != null && voucherCode.length() == 16 &&
                voucherCode.startsWith("ESHOP") &&
                voucherCode.replaceAll("[^0-9]", "").length() == 8) {
            this.status = "SUCCESS";
        } else {
            this.status = "REJECTED";
        }
    }

    private void validateBankTransfer() {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");
        this.status = (bankName != null && !bankName.isEmpty() &&
                referenceCode != null && !referenceCode.isEmpty()) ? "SUCCESS" : "REJECTED";
    }

    public void setStatus(String status) {
        String[] statusList = {"REJECTED", "SUCCESS", "PENDING"};
        if (Arrays.stream(statusList).noneMatch(item -> item.equals(status))) {
            throw new IllegalArgumentException();
        }
        this.status = status;
    }
}
