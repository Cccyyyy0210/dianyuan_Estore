package com.cy.servlet.Goods;

import com.cy.model.Page;
import com.cy.model.Type;
import com.cy.service.GoodsService;
import com.cy.service.TypeService;
import com.cy.service.impl.GoodsServiceImpl;
import com.cy.service.impl.TypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/goods_list")
public class GoodsListServlet extends HttpServlet {
    private GoodsService gService = new GoodsServiceImpl();
    private TypeService tService = new TypeServiceImpl();
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = 0;
        if(request.getParameter("id") != null) {
            id=Integer.parseInt(request.getParameter("id") ) ;
        }
        int pageNo = 1;
        if(request.getParameter("pageNo") != null) {
            pageNo=Integer.parseInt(request.getParameter("pageNo") ) ;
        }

//		List<Goods> list = gService.selectGoods(id, pageNo, 8);
//		request.setAttribute("list", list);

        Page p = gService.getGoodsPage(id, pageNo);


        request.setAttribute("p", p);
        request.setAttribute("id", id);
        Type t = null;
        if(id!=0) {
            try {
                t=tService.select(id);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        request.setAttribute("t", t);
        request.getRequestDispatcher("/goods_list.jsp").forward(request, response);
    }

}
