package com.neueda.leap.merchantportal;

import java.util.Optional;

public interface PayoutRepository {
    /**
     * @param userId required for access control verification
     */
    Optional<PayoutRequest> findById(Long payoutId, Long userId);
    
    /**
     * @param userId required for authorization and audit logging
     */
    PayoutRequest save(PayoutRequest payout, Long userId);
}
