package org.tibid.repository;

import java.util.List;

import org.tibid.entity.BidTicketEntity;

public interface BidTicketRepoCustom {
	BidTicketEntity findLastRecordBy(long userId, long orderId, int status);

	int updateTicketStatusToFail(long orderId);

	List<BidTicketEntity> findLimitTicketsByOrderId(long orderId, int limit);
}
