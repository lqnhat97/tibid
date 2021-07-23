package org.tibid.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.tibid.entity.BidTicketEntity;
import org.tibid.entity.QBidTicketEntity;
import org.tibid.repository.BidTicketRepoCustom;

public class BidTicketRepoCustomImpl implements BidTicketRepoCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public BidTicketEntity findLastRecordBy(long userId, long orderId, int status) {
		QBidTicketEntity qBidTicketEntity = QBidTicketEntity.bidTicketEntity;
		JPAQuery<BidTicketEntity> query = new JPAQuery<>(em);
		query.from(qBidTicketEntity)
				.where(qBidTicketEntity.userId.eq(userId)
						.and(qBidTicketEntity.bidOrderId.eq(orderId))
						.and(qBidTicketEntity.status.eq(status)))
				.orderBy(qBidTicketEntity.id.desc())
				.limit(1);
		return query.fetchOne();
	}

	@Override
	public int updateTicketStatusToFail(long orderId) {
		Query query = em.createNativeQuery("update bid_ticket set status = 3 where bid_order_id = :orderId");
		query.setParameter("orderId", orderId);
		return query.executeUpdate();
	}

	@Override
	public List<BidTicketEntity> findLimitTicketsByOrderId(long orderId, int limit) {
		Query query = em.createNativeQuery("select * from bid_ticket t where t.bid_order_id = :orderId order by t.id desc", BidTicketEntity.class);
		query.setParameter("orderId", orderId);
		query.setMaxResults(limit);
		return query.getResultList();
	}
}
