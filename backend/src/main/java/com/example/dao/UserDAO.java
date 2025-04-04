package com.example.dao;

import java.math.BigDecimal;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.backend.entity.User;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    public UserDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int createUser(String username, String password, String name, String email, BigDecimal balance) {
        String createUserQuery = "INSERT INTO users (username, password, name, email) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(createUserQuery, username, password, name, email);
    }

    public User findUserById(long id) {
        String selectUserByIdQuery = "SELECT * FROM users WHERE id = ?";

        return jdbcTemplate.queryForObject(selectUserByIdQuery, (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setCurrentBalance(rs.getBigDecimal("current_balance"));
            return user;
        }, id);
    }

    public BigDecimal getUserBalanceById(long id) {
        String getUserBalanceByIdQuery = "SELECT current_balance FROM users WHERE users_id = id";

        return jdbcTemplate.queryForObject(getUserBalanceByIdQuery, (rs, rowNum) -> {
            return rs.getBigDecimal("current_balance");
        }, id);
    }

    public void updateUserBalance(long id, BigDecimal newBalance) {
        String updateUserBalanceQuery = "UPDATE users SET current_balance = ? WHERE id = ?";
        jdbcTemplate.update(updateUserBalanceQuery, newBalance, id);
    }
}
