package com.cy.servlet.Admin;

import com.cy.model.Type;
import com.cy.service.TypeService;
import com.cy.service.impl.TypeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/type_list")
public class AdminTypeListServlet extends HttpServlet {
    private TypeService tService = new TypeServiceImpl();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Type> list= null;
        try {
            list = tService.GetAllType();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.setAttribute("list", list);
        request.getRequestDispatcher("/admin/type_list.jsp").forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
