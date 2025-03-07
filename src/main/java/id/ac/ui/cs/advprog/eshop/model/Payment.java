package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;
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
    }

    public void setStatus(String status) {
    }
}
