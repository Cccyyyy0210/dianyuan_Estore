package com.cy.servlet.Goods;

import com.cy.model.Goods;
import com.cy.service.GoodsService;
import com.cy.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "goods_detail",urlPatterns = "/goods_detail")
public class GoodsDetailServlet extends HttpServlet {
    private GoodsService gService = new GoodsServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Goods g = null;
        try {
            g = gService.getGoodsById(id);
            request.setAttribute("g", g);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/goods_detail.jsp").forward(request, response);
    }

}
