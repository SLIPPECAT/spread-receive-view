package com.example.spread.domain.receive.controller;

import com.example.spread.domain.receive.dto.ReceiveRequestDto;
import com.example.spread.domain.receive.service.ReceiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/receive")
@RequiredArgsConstructor
public class ReceiveController {

    private final ReceiveService receiveService;

    @PostMapping
    public ResponseEntity<Integer> receiveMoney(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestHeader("X-ROOM-ID") String roomId,
            @RequestBody ReceiveRequestDto reqeustDto)
    {
        int amountReceived = receiveService.receiveMoney(userId, roomId, reqeustDto.getToken());
        return ResponseEntity.ok(amountReceived);
    }
}