package org.tibid.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
}
