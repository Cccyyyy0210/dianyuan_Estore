package com.cy.servlet.Admin;

import com.cy.service.OrderService;
import com.cy.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/order_delete")
public class AdminOrderDeleteServlet extends HttpServlet {
    private OrderService oService = new OrderServiceImpl();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        oService.delete(id);
        request.getRequestDispatcher("/admin/order_list").forward(request, response);
    }

}
