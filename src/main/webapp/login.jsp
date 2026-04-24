<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- CSS -->
    <link rel="stylesheet" href="CSS/Register_login.css?=v3">

    <title>Login - Crezco</title>
</head>

<body>

    <!-- BACKGROUND VIDEO -->
    <video autoplay muted loop id="bg-video">
        <source src="bg-video.mp4" type="video/mp4">
    </video>
     <a href="index.jsp" id="logo">Crezco</a>
    <!-- LOGIN FORM -->
    <form action="LoginServlet" method="post" onsubmit="return validateLoginForm()">

    <div class="modal-content">
        <h2>Sign In</h2>

        <label>Email</label>
			<input type="text" id="email" name="email" placeholder="Enter your email" required
			style="<%= request.getAttribute("error") != null ? "border:2px solid red;" : "" %>">
			
			<small id="emailError" class="error-msg">
			    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
			</small>

        <label>Password</label>
			<input type="password" id="password" name="password" placeholder="Enter your password" required
			style="<%= request.getAttribute("error") != null ? "border:2px solid red;" : "" %>">
             <small id="passwordError" class="error-msg"></small>
			
			
        <button type="submit" class="btn">Login</button>

        <p style="font-size: 12px; text-align:center;">
            Don't have an account?
            <a href="Register.jsp" style="color:#f3d88d;">Register</a>
        </p>
    </div>

</form>

<script>
function validateLoginForm() {
    let email = document.getElementById("email");
    let password = document.getElementById("password");

    let emailError = document.getElementById("emailError");
    let passwordError = document.getElementById("passwordError");

    let isValid = true;

    emailError.innerText = "";
    passwordError.innerText = "";
    email.style.border = "";
    password.style.border = "";

    let emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;

    if (!emailPattern.test(email.value.trim())) {
        emailError.innerText = "Please enter a valid email address.";
        email.style.border = "2px solid red";
        isValid = false;
    }

    if (password.value.trim().length < 8) {
        passwordError.innerText = "Password must be at least 8 characters.";
        password.style.border = "2px solid red";
        isValid = false;
    }

    return isValid;
}
</script>

</body>
</html>