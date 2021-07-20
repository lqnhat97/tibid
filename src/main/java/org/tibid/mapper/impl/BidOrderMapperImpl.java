package org.tibid.mapper.impl;

import org.springframework.stereotype.Component;
import org.tibid.dto.BidOrderDto;
import org.tibid.entity.BidOrderEnity;
import org.tibid.mapper.BidOrderMapper;

@Component
public class BidOrderMapperImpl implements BidOrderMapper {
	@Override
	public BidOrderDto toDto(BidOrderEnity entity) {
		BidOrderDto dto = new BidOrderDto();
		dto.setId(entity.getId());
		dto.setUserId(entity.getUserId());
		dto.setProductId(entity.getProductId());
		dto.setStartPrice(entity.getStartPrice());
		dto.setPriceStep(entity.getPriceStep());
		dto.setStatus(entity.getStatus());
		dto.setBidStartTime(entity.getBidStartTime());
		dto.setBidEndTime(entity.getBidEndTime());
		dto.setBidQuantity(entity.getBidQuantity());
		dto.setCreatedAt(entity.getCreatedDate());
		dto.setModifiedAt(entity.getUpdatedDate());
		dto.setCeilingPrice(entity.getCeilingPrice());
		dto.setProductName(entity.getProductName());
		return dto;
	}

	@Override
	public BidOrderEnity toEntity(BidOrderDto dto) {
		BidOrderEnity entity = new BidOrderEnity();
		if (0 != dto.getUserId()) {
			entity.setUserId(dto.getUserId());
		}
		entity.setProductId(dto.getProductId());
		entity.setStartPrice(dto.getStartPrice());
		entity.setPriceStep(dto.getPriceStep());
		entity.setStatus(dto.getStatus());
		entity.setBidStartTime(dto.getBidStartTime());
		entity.setBidEndTime(dto.getBidEndTime());
		entity.setBidQuantity(dto.getBidQuantity());
		entity.setCeilingPrice(dto.getCeilingPrice());
		entity.setProductName(dto.getProductName());
		return entity;
	}
}
