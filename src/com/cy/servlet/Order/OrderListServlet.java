package com.cy.servlet.Order;

import com.cy.model.Order;
import com.cy.model.User;
import com.cy.service.OrderService;
import com.cy.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/order_list")
public class OrderListServlet extends HttpServlet {
    private OrderService oService = new OrderServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User u = (User) request.getSession().getAttribute("user");
        List<Order> list = oService.selectAll(u.getId());
        request.setAttribute("orderList", list);
        request.getRequestDispatcher("/order_list.jsp").forward(request, response);
    }
}
