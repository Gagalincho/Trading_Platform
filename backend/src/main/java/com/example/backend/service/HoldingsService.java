package com.example.backend.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.backend.model.Holdings;
import com.example.dao.HoldingsDAO;

@Service
public class HoldingsService {
    
    private final HoldingsDAO holdingsDAO;

    public HoldingsService(HoldingsDAO holdingsDAO) {
        this.holdingsDAO = holdingsDAO;
    }

    public int createHolding(long userId, String symbol, BigDecimal quantity) {
        return holdingsDAO.createHolding(userId, symbol, quantity);
    }

    public int updateHolding(long userId, String symbol, BigDecimal quantity) {
        return holdingsDAO.updateHolding(userId, symbol, quantity);
    }

    public int deleteHoldingByUserId(long userId, String symbol) {
        return holdingsDAO.deleteHoldingByUserId(userId, symbol);
    }

    public List<Holdings> getHoldingsForUserByUserId(long userId) {
        return holdingsDAO.getHoldingsForUserByUserId(userId);
    }

    public Holdings getHoldingByUserIdAndSymbol(long userId, String symbol) {
        return holdingsDAO.getHoldingByUserIdAndSymbol(userId, symbol);
    }
    
}
