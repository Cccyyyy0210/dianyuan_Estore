package com.cy.dao;

import com.cy.model.Order;
import com.cy.model.OrderItem;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    public void insertOrder(Connection con, Order order) throws SQLException;
    public int getLastInsertId(Connection con) throws SQLException;
    public void insertOrderItem(Connection con, OrderItem item) throws SQLException;
    public List<Order> selectAll(int userid) throws SQLException;
    public List<OrderItem> selectAllItem(int orderid) throws SQLException;
    public int getOrderCount(int status) throws SQLException;
    public List<Order> selectOrderList(int status, int pageNumber, int pageSize) throws SQLException;
    public void updateStatus(int id,int status) throws SQLException;
    public void deleteOrder(Connection con ,int id) throws SQLException;
    public void deleteOrderItem(Connection con ,int id) throws SQLException;
}
