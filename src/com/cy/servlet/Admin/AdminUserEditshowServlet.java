package com.cy.servlet.Admin;

import com.cy.model.User;
import com.cy.service.UserService;
import com.cy.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/admin/user_editshow")
public class AdminUserEditshowServlet extends HttpServlet {
    private UserService uService = new UserServiceImpl();
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User user = uService.selectById(id);
        request.setAttribute("u", user);
        request.getRequestDispatcher("/admin/user_edit.jsp").forward(request, response);
    }

}
