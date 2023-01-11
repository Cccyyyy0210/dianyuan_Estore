package com.cy.service;

import com.cy.model.Order;
import com.cy.model.Page;

import java.util.List;

public interface OrderService {
    public void addOrder(Order order);
    public List<Order> selectAll(int userid);
    public Page getOrderPage(int status, int pageNumber);
    public void updateStatus(int id,int status);
    public void delete(int id);
}
