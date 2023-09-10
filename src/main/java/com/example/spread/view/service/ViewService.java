package com.example.spread.view.service;

import com.example.spread.receive.entity.Spread;
import com.example.spread.receive.entity.SpreadDetail;
import com.example.spread.spread.repository.SpreadDetailRepository;
import com.example.spread.spread.repository.SpreadRepository;
import com.example.spread.view.dto.ViewResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViewService {

    private final SpreadDetailRepository spreadDetailRepository;
    private final SpreadRepository spreadRepository;
    public int validatedDate = (7 * 24 * 60 * 60 * 1000);
//    @Cacheable(value = "viewCache", key="#token")
    public ViewResponseDto getSpreadInfo(Long userId, String token) {
        Spread spread = spreadRepository.findByToken(token).orElseThrow(() ->
                new IllegalArgumentException("토큰 정보가 올바르지 않습니다.")
        );
//        if (spread == null){
//            throw new IllegalArgumentException("토큰 정보가 올바르지 않습니다.");
//        }
//        if (!userId.equals(spreadInfo.getUserId())){
//            throw new IllegalArgumentException("뿌리기 정보에 접근할 권한이 없습니다.");
//        }
        Date createdDate = spread.getCreatedDate();
        if (!isValidatedDate(createdDate)){
            throw new IllegalArgumentException("7일 이내 건에 대해서만 조회 가능합니다.");
        }
        Map<String, Object> userInfo = new HashMap<>();
        List<SpreadDetail> receivedDetails = spreadDetailRepository.findBySpreadIdAndUserIdIsNotNull(spread.getId());

        List<ReceivedDetail> result = receivedDetails.stream()
                // 일치하는 부분이 따로 빠져야 한다.
//                .filter(detail -> detail.getUserId().equals(userId)) // userId와 일치하는 분배건만 필터링
                .map(detail -> new ReceivedDetail(detail.getAmount(), detail.getUserId()))
                .collect(Collectors.toList());

        if(receivedDetails.isEmpty()){
            throw new IllegalArgumentException("뿌린 건에 대해 조회할 수 있는 권한이 없습니다.");
        }

        int totalAmount = spread.getTotalAmount();
//        int allocatedAmount = spreadInfo.getAllocatedAmount();
        int allocatedAmount = receivedDetails.stream().mapToInt(SpreadDetail::getAmount).sum();

        SpreadDetail spreadDetail = spreadDetailRepository.findByUserId(userId);
        if (spreadDetail == null){
            throw new IllegalArgumentException("뿌리기를 받은 건이 없습니다.");
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
