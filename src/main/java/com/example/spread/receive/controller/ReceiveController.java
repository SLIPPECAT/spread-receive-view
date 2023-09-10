package com.example.spread.receive.controller;

import com.example.spread.receive.dto.ReceiveRequestDto;
import com.example.spread.receive.service.ReceiveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receive")
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
//        System.out.println("reqeustDto.getToken() = " + reqeustDto.getToken());
//        System.out.println("amountReceived = " + amountReceived);
        return ResponseEntity.ok(amountReceived);
    }
}