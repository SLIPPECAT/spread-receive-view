package com.example.spread.domain.spread.service;

import com.example.spread.domain.receive.entity.Spread;
import com.example.spread.domain.receive.entity.SpreadDetail;
import com.example.spread.domain.repository.SpreadDetailRepository;
import com.example.spread.domain.repository.SpreadRepository;
import com.example.spread.exception.SpreadException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static com.example.spread.exception.SpreadException.INVALID_RECIPIENT;

@Service
@RequiredArgsConstructor
public class SpreadService {

    private final SpreadRepository spreadRepository;
    private final SpreadDetailRepository spreadDetailRepository;

    @Transactional
    public String createSpread(Long userId, String roomId, int totalAmount, int recipients){
        String token;
        do {
            token = generateToken();
        } while (spreadRepository.findByToken(token).isPresent());
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
        char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        for (int i = 0; i < 3; i++) {
            int randomNum = random.nextInt(alphabet.length);
            char randomChar = alphabet[randomNum];
            token.append(randomChar);
        }
        return token.toString();
    }

    private List<Integer> distributeAmount(int totalAmount, int recipients){
        if (recipients == 0){
            throw new SpreadException(INVALID_RECIPIENT);
        }
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
