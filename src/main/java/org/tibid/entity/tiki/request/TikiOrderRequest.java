package org.tibid.entity.tiki.request;

import org.tibid.dto.BidTicketDto;

import java.util.List;

public class TikiOrderRequest {

    private List<BidTicketDto> data;

    public List<BidTicketDto> getData() {
        return data;
    }

    public void setData(List<BidTicketDto> data) {
        this.data = data;
    }
}
