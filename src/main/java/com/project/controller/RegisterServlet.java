package com.project.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.project.dao.UserDAO;
import com.project.model.User;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	 private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);

        UserDAO dao = new UserDAO();
        boolean status = dao.registerUser(user);

        if (status) {
            response.sendRedirect("login.jsp");
        } else {
            response.getWriter().println("Registration Failed!");
        }
    }
}