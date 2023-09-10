package com.example.spread.view.dto;

import com.example.spread.receive.entity.SpreadDetail;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ViewResponseDto {

    private Date spreadDate;
    private int totalAmount;
    private int allocatedAmount;
    private Object spreadDetail;
//    private List<SpreadDetail> spreadDetails;
//, List<SpreadDetail> spreadDetails
    @Builder
    public ViewResponseDto(Date spreadDate, int totalAmount, int allocatedAmount, Object spreadDetail) {
        this.spreadDate = spreadDate;
        this.totalAmount = totalAmount;
        this.allocatedAmount = allocatedAmount;
        this.spreadDetail = spreadDetail;
    }
}
