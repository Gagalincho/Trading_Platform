package com.example.backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.dao.HoldingsDAO;
import com.example.backend.model.Holdings;

@Service
public class HoldingsService {

    private final HoldingsDAO holdingsDAO;
    private final UserService userService;

    public HoldingsService(HoldingsDAO holdingsDAO, UserService userService) {
        this.holdingsDAO = holdingsDAO;
        this.userService = userService;
    }

    public int createHolding(long userId, String symbol, BigDecimal quantity) {

        userService.ensureUserExists(userId);

        return holdingsDAO.createHolding(userId, symbol, quantity);
    }

    public int updateHolding(long userId, String symbol, BigDecimal quantity) {

        userService.ensureUserExists(userId);

        return holdingsDAO.updateHolding(userId, symbol, quantity);
    }

    public int deleteHoldingByUserId(long userId, String symbol) {

        userService.ensureUserExists(userId);

        return holdingsDAO.deleteHoldingByUserId(userId, symbol);
    }

    public List<Holdings> getHoldingsForUserByUserId(long userId) {

        userService.ensureUserExists(userId);

        return holdingsDAO.getHoldingsForUserByUserId(userId);
    }

    public Holdings getHoldingByUserIdAndSymbol(long userId, String symbol) {

        userService.ensureUserExists(userId);

        return holdingsDAO.getHoldingByUserIdAndSymbol(userId, symbol);
    }

}
