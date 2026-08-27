package com.neueda.leap.merchantportal;

public class PaymentStatusEvent {
    private final Long payoutId;  // Make immutable
    private final String status;
    
    // Enum for valid statuses
    enum PaymentStatus { PENDING, APPROVED, COMPLETED, FAILED }
    
    public PaymentStatusEvent(Long payoutId, String status) {
        if (payoutId == null || payoutId <= 0) {
            throw new IllegalArgumentException("Invalid payout ID");
        }
        if (status == null || status.isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null");
        }
        this.payoutId = payoutId;
        this.status = status;
    }
    
    public Long getPayoutId() { return payoutId; }
    public String getStatus() { return status; }
}
