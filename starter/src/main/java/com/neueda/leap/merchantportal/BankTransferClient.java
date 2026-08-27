package com.neueda.leap.merchantportal;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import org.springframework.security.access.prepost.PreAuthorize;

public interface BankTransferClient {
    @PreAuthorize("hasRole('MERCHANT_ADMIN')")
    void transfer( 
        @NotNull(message = "Merchant ID cannot be null")
    Long merchantId, 

    @Positive(message = "Amount must be greater than 0")
    double amount
) throws BankTransferException;
}
