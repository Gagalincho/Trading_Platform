package com.example.backend.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.backend.dao.UserDAO;
import com.example.backend.error.user.InvalidPasswordException;
import com.example.backend.error.user.NoUserWithSuchEmailExistsException;
import com.example.backend.error.user.NoUserWithSuchIdExistsException;
import com.example.backend.error.user.NoUserWithSuchUsernameExistsException;
import com.example.backend.error.user.UsernameAlreadyExistsException;
import com.example.backend.model.User;
import com.example.backend.util.PasswordUtil;

@Service
public class UserService {

    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public int createUser(String username, String password, String name, String email) {

        if (userDAO.findUserByUsername(username) == null) {
            throw new UsernameAlreadyExistsException("Username already exists");
        }

        String hashedPassword = PasswordUtil.hashPassword(password);

        return userDAO.createUser(username, hashedPassword, name, email);
    }

    public boolean login(String username, String rawPassword) {
        User user = userDAO.findUserByUsername(username);
    
        if (user == null) {
            throw new NoUserWithSuchUsernameExistsException("User not found");
        }
    
        if (!PasswordUtil.matches(rawPassword, user.getPassword())) {
            throw new InvalidPasswordException("Incorrect password");
        }
    
        return true;
    }

    public User findUserById(long id) {
        ensureUserExists(id);

        return userDAO.findUserById(id);
    }

    public User findUserByUsername(String username) {
        User user = userDAO.findUserByUsername(username);

        if (user == null) {
            throw new NoUserWithSuchUsernameExistsException("User not found");
        }

        return userDAO.findUserByUsername(username);
    }

    public User findUserByEmail(String email) {
        User user = userDAO.findUserByEmail(email);

        if (user == null) {
            throw new NoUserWithSuchEmailExistsException("User not found");
        }

        return userDAO.findUserByEmail(email);
    }

    public BigDecimal getUserBalanceById(long id) {
        ensureUserExists(id);

        return userDAO.getUserBalanceById(id);
    }

    public void updateUserBalance(long id, BigDecimal newBalance) {
        ensureUserExists(id);

        userDAO.updateUserBalance(id, newBalance);
    }

    public void ensureUserExists(long userId) {
        if (userDAO.findUserById(userId) == null) {
            throw new NoUserWithSuchIdExistsException("User not found");
        }
    }
}
