package com.neueda.leap.merchantportal;

import org.springframework.web.bind.annotation.*;

@RestController
public class MerchantController {

    private final PayoutRepository payoutRepository;

    public MerchantController(PayoutRepository payoutRepository) {
        this.payoutRepository = payoutRepository;
    }

    @GetMapping("/api/payouts/{payoutId}")
    public PayoutRequest getPayout(@PathVariable Long payoutId, @RequestHeader("X-User-Id") Long userId) {
        // userId required so the repository can enforce the caller owns/may view this payout
        return payoutRepository.findById(payoutId, userId)
                .orElseThrow(() -> new RuntimeException("Payout not found"));
    }
}
