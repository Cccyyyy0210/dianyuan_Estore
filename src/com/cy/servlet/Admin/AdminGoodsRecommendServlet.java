package com.cy.servlet.Admin;

import com.cy.service.GoodsService;
import com.cy.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/goods_recommend")
public class AdminGoodsRecommendServlet extends HttpServlet {
    private GoodsService gService = new GoodsServiceImpl();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String method = request.getParameter("method");
        int typeTarget=Integer.parseInt(request.getParameter("typeTarget"));
        if(method.equals("add")) {
            try {
                gService.addRecommend(id, typeTarget);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else {
            try {
                gService.removeRecommend(id, typeTarget);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        request.getRequestDispatcher("/admin/goods_list").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
