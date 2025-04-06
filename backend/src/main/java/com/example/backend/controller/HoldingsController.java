package com.example.backend.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.dto.HoldingsDTO;
import com.example.backend.mapper.HoldingsMapper;
import com.example.backend.model.Holdings;
import com.example.backend.service.HoldingsService;
import com.example.backend.service.UserService;

@RestController
@RequestMapping("/holdings")
public class HoldingsController {

    private final HoldingsService holdingsService;
    private final UserService userService;

    public HoldingsController(HoldingsService holdingsService, UserService userService) {
        this.holdingsService = holdingsService;
        this.userService = userService;
    }
    
    @GetMapping("/user/{username}")
    public ResponseEntity<List<HoldingsDTO>> getUserHoldings(@PathVariable String username) {

        long userId = userService.findUserByUsername(username).getId();

        List<Holdings> holdings = holdingsService.getHoldingsForUserByUserId(userId);
        
        return ResponseEntity.ok(HoldingsMapper.toDTOList(holdings));
    }
}
