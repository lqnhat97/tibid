package org.tibid.service.tiki;

import com.google.gson.Gson;
import org.tibid.dto.BidOrderDto;
import org.tibid.entity.BidOrderEnity;
import org.tibid.entity.tiki.Order;
import org.tibid.mapper.BidOrderMapper;
import org.tibid.mapper.BidTicketMapper;
import org.tibid.repository.BidOrderRepo;
import org.tibid.repository.BidTicketRepo;

public class TikiIntegrateServiceImpl implements TikiIntegrateService {

    private final BidTicketRepo bidTicketRepo;

    private final BidOrderRepo bidOrderRepo;

    private final BidTicketMapper bidTicketMapper;

    private final BidOrderMapper bidOrderMapper;

    private final Gson gson=new Gson();

    public TikiIntegrateServiceImpl(BidTicketRepo bidTicketRepo, BidOrderRepo bidOrderRepo, BidTicketMapper bidTicketMapper, BidOrderMapper bidOrderMapper) {
        this.bidTicketRepo = bidTicketRepo;
        this.bidOrderRepo = bidOrderRepo;
        this.bidTicketMapper = bidTicketMapper;
        this.bidOrderMapper = bidOrderMapper;
    }

    @Override
    public Order createOrder(BidOrderDto bidOrderDto) {
        BidOrderEnity bidOrderEnity = bidOrderMapper.toEntity(bidOrderDto);
        return null;
    }

}
