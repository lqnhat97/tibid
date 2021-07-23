package org.tibid.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.tibid.entity.BidOrderEntity;

@Repository
public interface BidOrderRepo extends BaseRepo<BidOrderEntity>, BidOrderRepoCustom {
	BidOrderEntity findByTikiOrderId(String tikiOrderId);

	@Query("select b from BidOrderEntity b where b.id in (select distinct t.bidOrderId from BidTicketEntity t where t.userId = :user_id and t.status = :status)")
	List<BidOrderEntity> findByUserBidingIdAndStatus(@Param("user_id") long userId, @Param("status") int status);
}
