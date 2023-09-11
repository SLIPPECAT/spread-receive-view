package com.example.spread.domain.receive.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Entity
@Setter
@Table(name = "spread")
public class Spread {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomId;
    private Long userId;
    private String token;
    private int totalAmount;
    private int allocatedAmount;
    private Date createdDate;

    public Spread() {}
    @Builder
    public Spread(Long userId, String roomId, String token, int totalAmount, int allocatedAmount, Date createdDate) {
        this.userId = userId;
        this.roomId = roomId;
        this.token = token;
        this.totalAmount = totalAmount;
        this.createdDate = createdDate;
        this.allocatedAmount = allocatedAmount;
    }

    public void allocateAmount(int price){
        this.allocatedAmount += price;
    }

}
