package com.cy.dao;

import com.cy.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    public void addUser(User user) throws SQLException;
    public boolean isUsernameExist(String username) throws SQLException;
    public boolean isEmailExist(String email) throws SQLException;
    public User selectByUsernamePassword(String username,String password) throws SQLException;
    public User selectByEmailPassword(String email,String password) throws SQLException;
    public User selectById(int id) throws SQLException;

    public void updateUserAddress(User user) throws SQLException;
    public void updatePwd(User user) throws SQLException;
    public int selectUserCount() throws SQLException;
    public List selectUserList(int pageNo, int pageSize) throws SQLException;
    public void delete(int id) throws SQLException;
}
