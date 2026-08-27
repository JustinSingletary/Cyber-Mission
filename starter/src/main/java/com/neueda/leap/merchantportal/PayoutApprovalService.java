package com.neueda.leap.merchantportal;

public class PayoutApprovalService {

    private PayoutRepository payoutRepository;

    public PayoutApprovalService(PayoutRepository payoutRepository) {
        this.payoutRepository = payoutRepository;
    }

    public void approve(Long payoutId, Long approvingUserId) {
        if (approvingUserId == null) {
            throw new IllegalArgumentException("Approving user ID cannot be null");
        }

        PayoutRequest payout = payoutRepository.findById(payoutId, approvingUserId)
                .orElseThrow(() -> new IllegalArgumentException("Payout not found"));

        // Prevent re-approval
        if ("APPROVED".equals(payout.getApprovalStatus())) {
            throw new IllegalStateException("Payout already approved");
        }

        // Enforce segregation of duties: requester cannot approve their own payout
        if (approvingUserId.equals(payout.getRequestedByUserId())) {
            throw new SecurityException("Users cannot approve their own payout request");
        }

        payout.setApprovalStatus("APPROVED");
        payout.setApprovedByUserId(approvingUserId);
        payoutRepository.save(payout, approvingUserId);
    }
}
