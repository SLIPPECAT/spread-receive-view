package com.example.spread.domain.view.service;

import com.example.spread.domain.receive.entity.Spread;
import com.example.spread.domain.receive.entity.SpreadDetail;
import com.example.spread.domain.repository.SpreadDetailRepository;
import com.example.spread.domain.repository.SpreadRepository;
import com.example.spread.domain.view.dto.ViewResponseDto;
import com.example.spread.exception.ViewException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.spread.exception.ViewException.*;

@Service
@RequiredArgsConstructor
public class ViewService {

    private final SpreadRepository spreadRepository;
    private final SpreadDetailRepository spreadDetailRepository;
    public int validatedDate = (7 * 24 * 60 * 60 * 1000);

    @Transactional
    public ViewResponseDto getSpreadInfo(Long userId, String token) {
        System.out.println("token = " + token);
        Spread spread = spreadRepository.findByToken(token).orElseThrow(() ->
                new ViewException(INVALID_TOKEN));

        Date createdDate = spread.getCreatedDate();
        if (!isValidatedDate(createdDate)){
            throw new ViewException(INVALID_DATE);
        }
        List<SpreadDetail> receivedDetails = spreadDetailRepository.findBySpreadIdAndUserIdIsNotNull(spread.getId());

        List<ReceivedDetail> result = receivedDetails.stream()
                .map(detail -> new ReceivedDetail(detail.getAmount(), detail.getUserId()))
                .collect(Collectors.toList());

        int totalAmount = spread.getTotalAmount();
        int allocatedAmount = receivedDetails.stream().mapToInt(SpreadDetail::getAmount).sum();

        SpreadDetail spreadDetail = spreadDetailRepository.findByUserId(userId);
        if (spreadDetail == null){
            throw new ViewException(NOT_ACCESSED);
        }

        return ViewResponseDto.builder()
                .spreadDate(createdDate)
                .totalAmount(totalAmount)
                .allocatedAmount(allocatedAmount)
                .spreadDetail(result)
                .build();
    }

    private boolean isValidatedDate(Date createdDate){
        Date currentDate = new Date();
        return currentDate.getTime() - createdDate.getTime() <= validatedDate;
    }
}