package com.example.spread.domain.receive.dto;

import lombok.Getter;

@Getter
public class ReceiveRequestDto {

    private String token;
    public ReceiveRequestDto() {}

    public ReceiveRequestDto(String token) {
        this.token = token;
    }
}
