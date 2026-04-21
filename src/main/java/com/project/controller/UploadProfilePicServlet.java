package com.project.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.InputStream;
import com.project.model.User;
import com.project.dao.UserDAO;

@MultipartConfig
@WebServlet("/UploadProfilePicServlet")
public class UploadProfilePicServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	System.out.println("UPLOAD SERVLET HIT");
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Part filePart = request.getPart("profilePic");

        if (filePart == null || filePart.getSubmittedFileName() == null) {
            System.out.println("No file selected");
            return;
        }

        String fileName = System.currentTimeMillis() + "_" + filePart.getSubmittedFileName();

        String uploadPath = "C:/Users/prati/iwt-uploads/";

        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }

        File file = new File(uploadDir, fileName);

        try (InputStream input = filePart.getInputStream()) {
            Files.copy(input, file.toPath(),
                    java.nio.file.StandardCopyOption.REPLACE_EXISTING);
        }

        System.out.println("Saved at: " + file.getAbsolutePath());
        System.out.println("FILE NAME: " + fileName);
        System.out.println("UPLOAD PATH: " + uploadPath);
        System.out.println("FILE EXISTS DIR: " + uploadDir.exists());

            String dbPath = fileName;

            user.setProfilePic(dbPath);

            // update DB
            System.out.println("User id "+user.getId());
            UserDAO dao = new UserDAO();
            dao.updateProfilePic(user.getId(), dbPath);
            session.setAttribute("user", user);
        

        response.sendRedirect("profile.jsp");
    }
}