package org.tibid.repository;

import org.springframework.stereotype.Repository;
import org.tibid.entity.BidTicketEntity;

@Repository
public interface BidTicketRepo extends BaseRepo<BidTicketEntity> {
    BidTicketEntity findByBidOrderIdAndStatus(int bidOrderId, int status);
}
