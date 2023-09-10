package com.example.spread.receive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReceiveDetailResponse {

    private Long detailId;
    private int receivedAmount;

}

