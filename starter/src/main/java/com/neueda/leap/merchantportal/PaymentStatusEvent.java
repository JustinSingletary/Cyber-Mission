package com.neueda.leap.merchantportal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public class PaymentStatusEvent {
    @NotNull
    private Long payoutId;
    @NotBlank
    private String status;

    public Long getPayoutId() { return payoutId; }
    public String getStatus() { return status; }
}
