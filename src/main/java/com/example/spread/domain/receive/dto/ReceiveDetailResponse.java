package com.example.spread.domain.receive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ReceiveDetailResponse {

    private Long detailId;
    private int receivedAmount;

    public ReceiveDetailResponse() {}

    public ReceiveDetailResponse(Long detailId, int receivedAmount) {
        this.detailId = detailId;
        this.receivedAmount = receivedAmount;
    }
}

