package com.cy.servlet.Goods;

import com.cy.model.Page;
import com.cy.service.GoodsService;
import com.cy.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;


import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.SQLException;

@WebServlet(name = "goods_search",urlPatterns = "/goods_search")
public class GoodsSearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private GoodsService gService = new GoodsServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        System.out.println(keyword);
        int pageNo = 1;
        if(request.getParameter("pageNo") != null) {
            pageNo=Integer.parseInt(request.getParameter("pageNo") ) ;
        }
        Page p = null;
        try {
            p = gService.getSearchGoodsPage(keyword,pageNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("p", p);
        request.setAttribute("keyword", URLEncoder.encode(keyword,"utf-8"));
        request.getRequestDispatcher("goods_search.jsp").forward(request, response);
    }
}
