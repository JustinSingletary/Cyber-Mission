package com.neueda.leap.merchantportal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
public class MerchantController {
    private final PayoutRepository payoutRepository;

    public MerchantController(PayoutRepository payoutRepository) {
        this.payoutRepository = payoutRepository;
    }

    @GetMapping("/api/payouts/{payoutId}")
    @PreAuthorize("hasRole('MERCHANT')")
    public PayoutRequest getPayout(@Valid @PathVariable Long payoutId) {
        return payoutRepository.findById(payoutId)
                .orElseThrow(() -> new RuntimeException("HTTP 404"));
    }
}
