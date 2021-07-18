package org.tibid.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tibid.dto.BidOrderDto;
import org.tibid.dto.BidTicketDto;
import org.tibid.entity.BidOrderEnity;
import org.tibid.entity.BidTicketEntity;
import org.tibid.mapper.BidOrderMapper;
import org.tibid.mapper.BidTicketMapper;
import org.tibid.repository.BidOrderRepo;
import org.tibid.repository.BidTicketRepo;

@Service
@Transactional
public class TibidServiceImpl implements TibidService {

	private final BidTicketRepo bidTicketRepo;
	private final BidOrderRepo bidOrderRepo;

	private final BidTicketMapper bidTicketMapper;

	private final BidOrderMapper bidOrderMapper;

	public TibidServiceImpl(BidTicketRepo bidTicketRepo, BidOrderRepo bidOrderRepo, BidTicketMapper bidTicketMapper, BidOrderMapper bidOrderMapper) {
		this.bidTicketRepo = bidTicketRepo;
		this.bidOrderRepo = bidOrderRepo;
		this.bidTicketMapper = bidTicketMapper;
		this.bidOrderMapper = bidOrderMapper;
	}


	@Override
	public BidTicketDto createBidTicket(BidTicketDto bidTicketDto){
		BidTicketEntity result = bidTicketRepo.save(bidTicketMapper.toEntity(bidTicketDto));
		return bidTicketMapper.toDto(result);
	}

	@Override
	public BidOrderDto createBidOrder(BidOrderDto bidOrderDto){
		BidOrderEnity result = bidOrderRepo.save(bidOrderMapper.toEntity(bidOrderDto));
		return bidOrderMapper.toDto(result);
	}

	// Temporary find all
	@Override
	public List<BidOrderDto> searchBidOrder(){
		List<BidOrderDto> resultList = new ArrayList<>();
		bidOrderRepo.findAll().forEach(entity -> {
			resultList.add(bidOrderMapper.toDto(entity));
		});
		return resultList;
	}
}
