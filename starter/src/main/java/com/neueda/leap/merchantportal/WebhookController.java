package com.neueda.leap.merchantportal;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import jakarta.validation.Valid;

@RestController
public class WebhookController {
    private final PayoutStatusUpdater payoutStatusUpdater;

    public WebhookController(PayoutStatusUpdater payoutStatusUpdater) {
        this.payoutStatusUpdater = payoutStatusUpdater;
    }

    @PostMapping("/api/webhooks/payment-status")
    @PreAuthorize("hasRole('WEBHOOK_SERVICE')")
    public void handlePaymentStatusWebhook(@Valid @RequestBody PaymentStatusEvent event) {
        payoutStatusUpdater.markSettled(event.getPayoutId(), event.getStatus());
    }
}
