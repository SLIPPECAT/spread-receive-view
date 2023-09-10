package com.example.spread.view.controller;

import com.example.spread.view.dto.ViewResponseDto;
import com.example.spread.view.service.ViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/view")
@RequiredArgsConstructor
public class ViewController {

    private final ViewService viewService;

    @GetMapping()
    public ResponseEntity<ViewResponseDto> viewSpreadInfo(
            @RequestHeader("X-USER-ID") Long userId,
            @RequestBody String token
    ){
        ViewResponseDto viewResponseDto = viewService.getSpreadInfo(userId, token);
        return ResponseEntity.ok(viewResponseDto);
    }

}
