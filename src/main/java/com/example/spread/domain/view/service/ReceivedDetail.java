package com.example.spread.domain.view.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
public class ReceivedDetail {

    private int amount;
    private Long userId;

    public ReceivedDetail(int amount, Long userId) {
        this.amount = amount;
        this.userId = userId;
    }
}
