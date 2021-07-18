package org.tibid.mapper;

import org.springframework.stereotype.Service;
import org.tibid.dto.BidOrderDto;
import org.tibid.entity.BidOrderEnity;

@Service
public interface BidOrderMapper extends BaseMapper<BidOrderDto, BidOrderEnity> {

}
