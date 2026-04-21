package com.project.controller;

import java.io.IOException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import com.project.model.User;
import com.project.dao.UserDAO;


@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if(user == null){
            response.sendRedirect("login.jsp");
            return;
        }
        
        
        
        // 🔥 Get form data
        String phone = request.getParameter("phone");
        String gender = request.getParameter("gender");
        String birthdate = request.getParameter("birthdate");
        String location = request.getParameter("location");
        String skills = request.getParameter("skills");
        String work = request.getParameter("work");
        String education = request.getParameter("education");
        String university = request.getParameter("university");
        String about = request.getParameter("about");

        // 🔥 Set into user object
        user.setPhone(phone);
        user.setGender(gender);
        user.setBirthdate(birthdate);
        user.setLocation(location);
        user.setSkills(skills);
        user.setWork(work);
        user.setEducation(education);
        user.setUniversity(university);
        user.setAbout(about);
        
        System.out.println("Phone: " + phone);
        System.out.println("Location: " + location);
        System.out.println("User ID: " + user.getId());

        // 🔥 Update DB
        UserDAO dao = new UserDAO();
        boolean updated = dao.updateUser(user);

        if(updated){
            response.sendRedirect("profile.jsp?update=success");
        } else {
            response.sendRedirect("profile.jsp?update=failed");
        }
    }
}