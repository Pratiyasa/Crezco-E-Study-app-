<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- CSS -->
    <link rel="stylesheet" href="CSS/Register_login.css?=v2">

    <title>Register - Crezco</title>
</head>

<body>

    <!-- BACKGROUND VIDEO -->
    <video autoplay muted loop id="bg-video">
        <source src="bg-video.mp4" type="video/mp4">
    </video>
     <a href="index.jsp" id="logo">Crezco</a>
    <!-- REGISTER FORM -->
    <form action="RegisterServlet" method="post">

        <div class="modal-content">
            <h2>Sign Up</h2>

            <label>Username</label>
            <input type="text" name="username" placeholder="Enter your username" required>

            <label>Email</label>
            <input type="text" name="email" placeholder="Enter your email" required>

            <label>Password</label>
            <input type="password" name="password" placeholder="Enter your password" required>

            <button type="submit" class="btn">Register</button>

            <!-- OPTIONAL -->
            <p style="font-size: 12px; text-align:center;">
                Already have an account?
                <a href="login.jsp" style="color:#f3d88d;">Login</a>
            </p>
        </div>

    </form>

</body>
</html>