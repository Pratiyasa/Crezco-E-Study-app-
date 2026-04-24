package com.project.controller;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.http.HttpSession;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.project.dao.ExamDAO;
import com.project.model.Questions;

@WebServlet("/ExamServlet")
public class ExamServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check if user logged in
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String subject = request.getParameter("subject");

        ExamDAO dao = new ExamDAO();
        List<Questions> questions = dao.getRandomQuestions(subject);

        System.out.println("Selected Subject: " + subject);
        System.out.println("Questions fetched: " + questions.size());

        // Store in session for SubmitExamServlet
        session.setAttribute("questions", questions);
        session.setAttribute("subject", subject);

        // Send to JSP
        request.setAttribute("questions", questions);
        request.setAttribute("subject", subject);

        RequestDispatcher rd = request.getRequestDispatcher("/questions.jsp");
        rd.forward(request, response);
    }
}