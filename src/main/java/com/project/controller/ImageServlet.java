package com.project.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {

	private static final String IMAGE_DIR = "C:/Users/prati/iwt-uploads/";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();

        // 🔴 Safety check (VERY IMPORTANT)
        if (pathInfo == null || pathInfo.equals("/")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Image name missing");
            return;
        }

        String fileName = pathInfo.substring(1); // remove "/"

        File file = new File(IMAGE_DIR, fileName);

        // 🔴 Check file existence
        if (!file.exists() || file.isDirectory()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Image not found");
            return;
        }

        // 🔥 Detect content type dynamically (better than fixed jpeg)
        String mimeType = getServletContext().getMimeType(file.getName());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setContentLengthLong(file.length());

        // 🔥 Safe stream handling
        try (FileInputStream fis = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {

            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        }
    }
}