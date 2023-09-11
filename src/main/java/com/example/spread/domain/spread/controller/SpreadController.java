package com.example.spread.domain.spread.controller;

import com.example.spread.domain.spread.dto.SpreadRequestDto;
import com.example.spread.domain.spread.service.SpreadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spread")
@RequiredArgsConstructor
public class SpreadController {

    private final SpreadService spreadService;

    @PostMapping
    public ResponseEntity<String> createSpread(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestHeader("X-ROOM-ID") String roomID,
            @RequestBody SpreadRequestDto reqeustDto)
    {
        String token = spreadService.createSpread(userId, roomID, reqeustDto.getAmount(), reqeustDto.getRecipients());
        return ResponseEntity.ok(token);
    }

}
