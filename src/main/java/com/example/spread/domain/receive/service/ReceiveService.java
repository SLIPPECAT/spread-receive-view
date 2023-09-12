package com.example.spread.domain.receive.service;

import com.example.spread.domain.receive.entity.Spread;
import com.example.spread.domain.receive.entity.SpreadDetail;
import com.example.spread.domain.repository.SpreadDetailRepository;
import com.example.spread.domain.repository.SpreadRepository;
import com.example.spread.exception.ReceiveException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.example.spread.exception.ReceiveException.*;

@Service
@RequiredArgsConstructor
public class ReceiveService {

    private final SpreadRepository spreadRepository;
    private final SpreadDetailRepository spreadDetailRepository;

    @Async("asyncExecutor")
    @Transactional
    public int receiveMoney(Long userId, String roomId, String token) {

        Spread spread = spreadRepository.findByToken(token).orElseThrow(() ->
                new ReceiveException(INVALID_TOKEN));

        if (!roomId.equals(spread.getRoomId())){
            throw new ReceiveException(MISMATCHED_ROOM);
        }
        if (!isValidatedSpread(spread)){
            throw new ReceiveException(INVALID_TIME);
        }
        if (userId.equals(spread.getUserId())){
            throw new ReceiveException(NOT_RECIPIENT);
        }
        if (spreadDetailRepository.findByUserId(userId) != null){
            throw new ReceiveException(ALREADY_RECEIVE);
        }

        List<SpreadDetail> unallocatedDetails = spreadDetailRepository.findUnallocatedDetailBySpreadId(spread.getId());

        SpreadDetail spreadDetail = unallocatedDetails.stream()
                .filter(sd -> !sd.isAllocated() && sd.getUserId() == null)
                .findAny()
                .orElseThrow( () -> new ReceiveException(ALREADY_RECEIVE));

        spreadDetail.setUserId(userId);
        spreadDetail.setAllocated(true);
        spreadDetailRepository.save(spreadDetail);
        spread.allocateAmount(spreadDetail.getAmount());
        spreadRepository.flush();
        spreadRepository.save(spread);

        return spreadDetail.getAmount();
    }

    private boolean isValidatedSpread(Spread spread){
        long time = System.currentTimeMillis() - spread.getCreatedDate().getTime();
        return time <= 10 * 60 * 1000;
    }

}
