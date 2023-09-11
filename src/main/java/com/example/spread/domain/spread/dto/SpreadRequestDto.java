package com.example.spread.domain.spread.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class SpreadRequestDto {

    private int amount;
    private int recipients;

    public SpreadRequestDto() {}

    public SpreadRequestDto(int amount, int recipients) {
        this.amount = amount;
        this.recipients = recipients;
    }
}
