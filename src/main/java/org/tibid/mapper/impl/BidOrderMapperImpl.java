package org.tibid.mapper.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.tibid.dto.BidOrderDto;
import org.tibid.entity.BidOrderEntity;
import org.tibid.mapper.BidOrderMapper;

@Component
public class BidOrderMapperImpl implements BidOrderMapper {
	@Override
	public BidOrderDto toDto(BidOrderEntity entity) {
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

		if (entity.getTikiInfo() != null) {
			dto.setTikiInfo(entity.getTikiInfo());
		} else {
			dto.setTikiInfo("");
		}

		if (entity.getTikiOrderInfo() != null) {
			dto.setTikiOrderInfo(entity.getTikiOrderInfo());
		} else {
			dto.setTikiOrderInfo("");
		}

		if (entity.getTikiOrderId() != null) {
			dto.setTikiOrderId(entity.getTikiOrderId());
		} else {
			dto.setTikiOrderId("");
		}
		dto.setLastTicketId(entity.getLastTicketId());
		return dto;
	}

	@Override
	public BidOrderEntity toEntity(BidOrderDto dto) {
		BidOrderEntity entity = new BidOrderEntity();
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


		if (dto.getTikiInfo() != null) {
			entity.setTikiInfo(dto.getTikiInfo());
		} else {
			entity.setTikiInfo("");
		}

		if (dto.getTikiOrderInfo() != null) {
			entity.setTikiOrderInfo(dto.getTikiOrderInfo());
		} else {
			entity.setTikiOrderInfo("");
		}

		if (dto.getTikiOrderId() != null) {
			entity.setTikiOrderId(dto.getTikiOrderId());
		} else {
			entity.setTikiOrderId("");
		}
		entity.setLastTicketId(dto.getLastTicketId());
		return entity;
	}

	@Override
	public List<BidOrderDto> toDtoList(List<BidOrderEntity> entityList) {
		List<BidOrderDto> result = new ArrayList<>();
		entityList.forEach(entity -> {
			result.add(toDto(entity));
		});
		return result;
	}
}
