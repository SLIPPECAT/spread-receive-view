package com.example.spread.spread.service;

import com.example.spread.receive.entity.Spread;
import com.example.spread.receive.entity.SpreadDetail;
import com.example.spread.spread.repository.SpreadDetailRepository;
import com.example.spread.spread.repository.SpreadRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class SpreadService {

    private final SpreadRepository spreadRepository;
    private final SpreadDetailRepository spreadDetailRepository;

    public String createSpread(Long userId, String roomId, int totalAmount, int recipients){
        String token = generateToken();

        List<Integer> amounts = distributeAmount(totalAmount, recipients);

        Spread spread = Spread.builder()
                .userId(userId)
                .roomId(roomId)
                .token(token)
                .totalAmount(totalAmount)
                .createdDate(new Date())
                .build();
        spreadRepository.save(spread);

        for (int i = 0; i < recipients; i++) {
            SpreadDetail detail = SpreadDetail.builder()
                    .spread(spread)
                    .amount(amounts.get(i))
                    .build();
            spreadDetailRepository.save(detail);
        }
        return token;
    }

    private String generateToken(){
        Random random = new Random();
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            char ch = (char) (random.nextInt(26) + 'A');
            token.append(ch);
        }
        return token.toString();
    }

    private List<Integer> distributeAmount(int totalAmount, int recipients){
        List<Integer> amounts = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < recipients - 1; i++) {
            int maxAmount = totalAmount - sum(amounts);
            int amount = random.nextInt(maxAmount) + 1;
            amounts.add(amount);
        }
        amounts.add(totalAmount - sum(amounts));
        return amounts;
    }

    private int sum(List<Integer> amounts) {
        return amounts.stream().mapToInt(Integer::intValue).sum();
    }


}
