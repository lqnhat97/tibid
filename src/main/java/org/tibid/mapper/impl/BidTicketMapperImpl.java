package org.tibid.mapper.impl;

import org.springframework.stereotype.Component;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.BidTicketEntity;
import org.tibid.mapper.BidTicketMapper;

@Component
public class BidTicketMapperImpl implements BidTicketMapper {
	@Override
	public BidTicketDto toDto(BidTicketEntity entity) {
		BidTicketDto dto = new BidTicketDto();
		dto.setUserId(entity.getUserId());
		dto.setBidOrderId(entity.getBidOrderId());
		dto.setPrice(entity.getPrice());
		dto.setStatus(entity.getStatus());
		return dto;
	}

	@Override
	public BidTicketEntity toEntity(BidTicketDto dto) {
		BidTicketEntity entity = new BidTicketEntity();
		if(0 != dto.getUserId()){
			entity.setUserId(dto.getUserId());
		}
		entity.setBidOrderId(dto.getBidOrderId());
		entity.setPrice(dto.getPrice());
		entity.setStatus(dto.getStatus());
		return entity;
	}
}
