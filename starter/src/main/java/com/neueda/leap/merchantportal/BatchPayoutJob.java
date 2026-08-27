package com.neueda.leap.merchantportal;

import java.util.List;
import jakarta.validation.Valid;

public class BatchPayoutJob {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BatchPayoutJob.class);

    private final BankTransferClient bankTransferClient;
    private final PayoutRepository payoutRepository;

    public BatchPayoutJob(BankTransferClient bankTransferClient, PayoutRepository payoutRepository) {
        this.bankTransferClient = bankTransferClient;
        this.payoutRepository = payoutRepository;
    }

    public void runNightlyBatch(@Valid List<PayoutRequest> approvedPayouts) {
        for (PayoutRequest payout : approvedPayouts) {
            try {
                bankTransferClient.transfer(payout.getMerchantId(), payout.getAmount());
                payout.setApprovalStatus("PAID");
            } catch (BankTransferException e) {
                log.warn("Transfer failed for payout {}, not marking paid: {}",
                        payout.getId(), e.getMessage());
                payout.setApprovalStatus("FAILED");
            }
            payoutRepository.save(payout);
        }
    }
}
