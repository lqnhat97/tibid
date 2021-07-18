package org.tibid.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "bid_ticket")
public class BidTicketEntity extends BaseEntity{

    @Column(name = "user_id")
    private int userId;

    @Column(name = "bid_order_id")
    private int bidOrderId;

    @Column(name = "price")
    private float price;

    @Column(name = "status")
    private int status;

}
