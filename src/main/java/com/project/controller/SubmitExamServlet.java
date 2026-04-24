package com.project.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.project.dao.ExamDAO;
import com.project.model.Questions;
import com.project.model.User;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/SubmitExamServlet")
public class SubmitExamServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        String subject = request.getParameter("subject");

        List<Questions> questions = (List<Questions>) session.getAttribute("questions");

        int correct = 0;
        int wrong = 0;

        List<Questions> wrongQuestions = new ArrayList<>();

        for (Questions q : questions) {
            String selected = request.getParameter("q" + q.getId());

            if (selected != null) {
                int ans = Integer.parseInt(selected);

                if (ans == q.getCorrectAnswer()) {
                    correct++;
                } else {
                    wrong++;
                    wrongQuestions.add(q);
                }
            }
        }

        int score = correct;

        ExamDAO dao = new ExamDAO();
        dao.saveResult(user.getId(), subject, score, correct, wrong);

        request.setAttribute("score", score);
        request.setAttribute("correct", correct);
        request.setAttribute("wrong", wrong);
        request.setAttribute("wrongQuestions", wrongQuestions);

        RequestDispatcher rd = request.getRequestDispatcher("/result.jsp");
        rd.forward(request, response);
    }
}