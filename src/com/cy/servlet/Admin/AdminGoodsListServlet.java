package com.cy.servlet.Admin;

import com.cy.model.Page;
import com.cy.service.GoodsService;
import com.cy.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/goods_list")
public class AdminGoodsListServlet extends HttpServlet {
    private GoodsService gService = new GoodsServiceImpl();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int type = 0;//推荐类型
        if(request.getParameter("type") != null) {
            type=Integer.parseInt(request.getParameter("type") ) ;
        }
        int pageNo = 1;
        if(request.getParameter("pageNo") != null) {
            pageNo=Integer.parseInt(request.getParameter("pageNo") ) ;
        }
        Page p = null;
        try {
            p = gService.getGoodsRecommendPage(type, pageNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("p", p);
        request.setAttribute("type", type);
        request.getRequestDispatcher("/admin/goods_list.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
