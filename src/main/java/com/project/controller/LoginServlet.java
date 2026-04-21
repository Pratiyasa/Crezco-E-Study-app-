package com.project.controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.project.dao.UserDAO;   // ✅ THIS LINE
import com.project.model.User;

import jakarta.servlet.annotation.WebServlet;

@WebServlet("/LoginServlet")

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO dao = new UserDAO();
        User user = dao.login(email, password);

        if(user != null) {
            // ✅ Login success
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            response.sendRedirect("profile.jsp");

        } else {
            // ❌ Login failed
            response.sendRedirect("Login.jsp?error=invalid");
        }
    }
}