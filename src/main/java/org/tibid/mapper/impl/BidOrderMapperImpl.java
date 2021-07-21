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
        return entity;
    }
}
