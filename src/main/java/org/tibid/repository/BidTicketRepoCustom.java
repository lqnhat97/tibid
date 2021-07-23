package org.tibid.repository;

import org.tibid.entity.BidTicketEntity;

public interface BidTicketRepoCustom {
	BidTicketEntity findLastRecordBy(long userId, long orderId, int status);

	int updateTicketStatus(long orderId);
}
