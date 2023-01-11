package com.cy.service;

import com.cy.model.Page;
import com.cy.model.User;

public interface UserService {
    public boolean register(User user);
    public User login(String ue,String password);
    public User selectById(int id);
    public void updateUserAddress(User user);
    public void updatePwd(User user);
    public Page getUserPage(int pageNumber);
    public boolean delete(int id );
}
