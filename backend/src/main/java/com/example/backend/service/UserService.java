package com.example.backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.backend.model.User;
import com.example.dao.UserDAO;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int createUser(String username, String password, String name, String email) {
        return userDAO.createUser(username, password, name, email);
    }

    public User findUserById(long id) {
        return userDAO.findUserById(id);
    }

    public BigDecimal getUserBalanceById(long id) {
        return userDAO.getUserBalanceById(id);
    }

    public void updateUserBalance(long id, BigDecimal newBalance) {
        userDAO.updateUserBalance(id, newBalance);
    }
}
