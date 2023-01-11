package com.cy.servlet;

import com.cy.service.GoodsService;
import com.cy.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "IndexServlet",urlPatterns = "/index")
public class IndexServlet extends HttpServlet {
    private GoodsService gService=new GoodsServiceImpl();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取条幅商品,type_id=1,年货礼盒
        Map<String,Object> ScrollGood= null;
        try {
            ScrollGood = gService.getScrollGood();
            req.setAttribute("scroll",ScrollGood);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //获取热销商品
        List<Map<String,Object>> hotList= null;
        try {
            hotList = gService.getGoodsList(2);
            req.setAttribute("hotList",hotList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //获取新品商品
        List<Map<String,Object>>newList= null;
        try {
            newList = gService.getGoodsList(1);
            req.setAttribute("newList",newList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //index.jsp
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
