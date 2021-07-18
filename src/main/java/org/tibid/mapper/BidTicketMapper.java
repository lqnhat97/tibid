package org.tibid.mapper;

import org.springframework.stereotype.Service;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.BidTicketEntity;

@Service
public interface BidTicketMapper extends BaseMapper<BidTicketDto, BidTicketEntity> {
}
