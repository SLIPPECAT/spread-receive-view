package com.example.spread.domain.view.controller;

import com.example.spread.domain.receive.dto.ReceiveRequestDto;
import com.example.spread.domain.receive.entity.Spread;
import com.example.spread.domain.repository.SpreadRepository;
import com.example.spread.domain.view.dto.ViewResponseDto;
import com.example.spread.domain.view.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/view")
@RequiredArgsConstructor
public class ViewController {

    private final ViewService viewService;

    @PostMapping
    public ResponseEntity<ViewResponseDto> viewSpreadInfo(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestBody ReceiveRequestDto requestDto
    ){
        ViewResponseDto viewResponseDto = viewService.getSpreadInfo(userId, requestDto.getToken());
        return ResponseEntity.ok(viewResponseDto);
    }

}
