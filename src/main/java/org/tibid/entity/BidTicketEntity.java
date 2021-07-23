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
    private long userId;

    @Column(name = "bid_order_id")
    private long bidOrderId;

    @Column(name = "price")
    private float price;

    @Column(name = "status")
    private int status;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_avatar")
    private String userAvatar;
}
