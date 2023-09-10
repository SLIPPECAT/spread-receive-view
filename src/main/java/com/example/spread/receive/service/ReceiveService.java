package com.example.spread.receive.service;

import com.example.spread.receive.entity.Spread;
import com.example.spread.receive.entity.SpreadDetail;
import com.example.spread.spread.repository.SpreadDetailRepository;
import com.example.spread.spread.repository.SpreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReceiveService {

    private final SpreadRepository spreadRepository;
    private final SpreadDetailRepository spreadDetailRepository;

    public int receiveMoney(Long userId, String roomId, String token) {

        Spread spread = spreadRepository.findByToken(token).orElseThrow(() ->
                new IllegalArgumentException("토큰 정보가 올바르지 않습니다."));
//        throw new IllegalArgumentException("뿌리기 정보가 없습니다.");
        // 뿌리기 정보가 없는 경우
//        if (spread == null){
//            throw new IllegalArgumentException("뿌리기 정보가 없습니다.");
//        }
        // 뿌리기를 한 대화방과 동일하지 않은 경우
        if (!roomId.equals(spread.getRoomId())){
            throw new IllegalArgumentException("뿌린 대화방과 다른 대화방입니다.");
        }
        // 뿌리기 건이 유효하지 않은 경우
        if (!isValidatedSpread(spread)){
            throw new IllegalArgumentException("뿌린 건에 대한 받기는 10분간만 유효합니다.");
        }
        // 자신이 뿌리기 한 건에 대해 자신이 받는 경우
        if (userId.equals(spread.getUserId())){
            throw new IllegalArgumentException("자신이 뿌리기 한 건은 받을 수 없습니다.");
        }
        if (spreadDetailRepository.findByUserId(userId) != null){
            throw new IllegalArgumentException("이미 뿌리기를 받았습니다.");
        }
        // 위의 예외 경우에 해당하지 않을 경우,
        // 아직 누구에게도 할당되지 않은 분배건 중 하나를 할당하여 금액을 받음
        List<SpreadDetail> unallocatedDetails = spreadDetailRepository.findUnallocatedDetailBySpreadId(spread.getId());

        SpreadDetail spreadDetail = unallocatedDetails.stream()
                .filter(sd -> !sd.isAllocated() && sd.getUserId() == null)
                .findAny()
                .orElseThrow( () -> new IllegalArgumentException("이미 뿌리기를 받았습니다."));

        spreadDetail.setUserId(userId);
        spreadDetail.setAllocated(true);
        spreadDetailRepository.save(spreadDetail);
        spread.allocateAmount(spreadDetail.getAmount());
        spreadRepository.save(spread);

        return spreadDetail.getAmount();
    }

    // 뿌린 건에 대한 받기는 10분 간 유효함.
    private boolean isValidatedSpread(Spread spread){
        long time = System.currentTimeMillis() - spread.getCreatedDate().getTime();
        return time <= 10 * 60 * 1000;
    }

}
