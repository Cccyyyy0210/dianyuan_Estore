package com.cy.servlet.Goods;

import com.cy.model.Goods;
import com.cy.model.Order;
import com.cy.service.GoodsService;
import com.cy.service.impl.GoodsServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/goods_buy")
public class GoodsBuyServlet extends HttpServlet {
    private GoodsService gService = new GoodsServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Map<String, String[]> map =request.getParameterMap();
//		for(String key : map.keySet()) {
//			System.out.println(key+":"+map.get(key));
//		}
        Order o = null;
        if(request.getSession().getAttribute("order") != null) {
            o = (Order) request.getSession().getAttribute("order");
        }else {
            o = new Order();
            request.getSession().setAttribute("order", o);
        }
        int goodsid = Integer.parseInt(request.getParameter("goodsid"));
        Goods goods = null;
        try {
            goods = gService.getGoodsById(goodsid);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(goods.getStock()>0) {
            o.addGoods(goods);
            response.getWriter().print("ok");
        }else {
            response.getWriter().print("fail");
        }
    }

}
