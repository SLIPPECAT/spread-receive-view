package com.example.spread.domain.view.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
public class ViewResponseDto {

    private Date spreadDate;
    private int totalAmount;
    private int allocatedAmount;
    private Object spreadDetail;

    public ViewResponseDto() {}

    @Builder
    public ViewResponseDto(Date spreadDate, int totalAmount, int allocatedAmount, Object spreadDetail) {
        this.spreadDate = spreadDate;
        this.totalAmount = totalAmount;
        this.allocatedAmount = allocatedAmount;
        this.spreadDetail = spreadDetail;
    }
}
