<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.project.model.User" %>

<%
User user = (User) session.getAttribute("user");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- CSS -->
    <link rel="stylesheet" href="CSS/style.css?=v3">

    <!-- Fonts & Icons -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.0/css/all.min.css">

    <title>Crezco</title>
</head>

<body>

    <!-- NAVBAR -->
    <div id="navbar">
        <a href="index.jsp" id="logo">Crezco</a>

        <div id="options">
            <h4><a href="profile.jsp">PROFILE</a></h4>
            <h4>FORUM</h4>
            <h4>E-EXAM</h4>
        </div>

        <div id="logout">
          <% if (user == null) { %>
			    <a href="login.jsp" class="btn">Login</a>
			    <a href="Register.jsp" class="btn">Register</a>
			<% } else { %>
			    <h3>Welcome, <%= user.getUsername() %></h3>
			    <a href="LogoutServlet" class="btn">Logout</a>
			<% } %>
        </div>
    </div>

    <!-- TAGLINE -->
    <div id="tagline">
        <h1>
            <div>
                <img src="hero.jpeg">
            </div>
            Practical Skills
        </h1>
        <h1>for Real-World Success.</h1>

        <h5>
            What you learn online today could redefine your future tomorrow
        </h5>
    </div>

    <!-- INFO SECTION -->
    <div id="info">

        <div id="text">
            <div class="no" data-target="67">0%</div>
            <h6>Lorem ipsum dolor sit amet consectetur adipisicing elit.</h6>
        </div>

        <div id="text">
            <div class="no" data-target="89">0%</div>
            <h6>Lorem ipsum dolor sit amet consectetur adipisicing elit.</h6>
        </div>

        <div id="text">
            <div class="no" data-target="78">0%</div>
            <h6>Lorem ipsum dolor sit amet consectetur adipisicing elit.</h6>
        </div>

        <div id="text">
            <div class="no" data-target="50">0%</div>
            <h6>Lorem ipsum dolor sit amet consectetur adipisicing elit.</h6>
        </div>

    </div>

    <!-- FOOTER -->
    <footer id="footer">

        <div class="footer-container">

            <!-- LEFT -->
            <div class="footer-section">
                <h2>Crezco</h2>
                <p>Empowering students with real-world skills through interactive learning.</p>
            </div>

            <!-- LINKS -->
            <div class="footer-section">
                <h3>Quick Links</h3>
                <a id="home" href="index.jsp">Home</a>
                <a href="profile.jsp">Profile</a>
                <a href="#">Forum</a>
                <a href="#">E-Exam</a>
            </div>

            <!-- CONTACT -->
            <div class="footer-section">
                <h3>Contact</h3>
                <p>Email: support@crezco.com</p>
                <p>Phone: +91 9876543210</p>
            </div>

            <!-- SOCIAL -->
            <div class="footer-section">
                <h3>Follow Us</h3>
                <div class="socials">
                    <a href="#"><i class="fa-brands fa-instagram"></i> Instagram</a>
                    <a href="#"><i class="fa-brands fa-twitter"></i> Twitter</a>
                    <a href="#"><i class="fa-brands fa-linkedin"></i> LinkedIn</a>
                </div>
            </div>

        </div>

        <div class="footer-bottom">
            © 2026 Crezco | All Rights Reserved
        </div>

    </footer>

    <!-- JS -->
    <script src="script.js"></script>

</body>
</html>