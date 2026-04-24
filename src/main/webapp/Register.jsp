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
    <form action="RegisterServlet" method="post" onsubmit="return validateForm()">

    <div class="modal-content">
        <h2>Sign Up</h2>

        <label>Username</label>
        <input type="text" name="username" placeholder="Enter your username" required>

        <label>Email</label>
		        <input type="text" id="email" name="email" placeholder="Enter your email" style="<%= request.getAttribute("error") != null ? "border:2px solid red;" : "" %>">
        <small id="emailError" class="error-msg">
		    <%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %>
		</small>

        <label>Password</label>
		<input type="password" id="password" name="password" placeholder="Enter your password" required>
		<small id="passwordError" class="error-msg"></small>
		
        <button type="submit" class="btn">Register</button>

        <p style="font-size: 12px; text-align:center;">
            Already have an account?
            <a href="login.jsp" style="color:#f3d88d;">Login</a>
        </p>
    </div>

</form>

<script>
function validateForm() {
    let email = document.getElementById("email");
    let password = document.getElementById("password");

    let emailError = document.getElementById("emailError");
    let passwordError = document.getElementById("passwordError");

    let isValid = true;

    // reset
    emailError.innerText = "";
    passwordError.innerText = "";
    email.style.border = "";
    password.style.border = "";

    let emailPattern = /^[^ ]+@[^ ]+\.[a-z]{2,3}$/;
    let passwordPattern =
        /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@#$!%*?&])[A-Za-z\d@#$!%*?&]{8,}$/;

    if (!emailPattern.test(email.value.trim())) {
        emailError.innerText = "Please enter a valid email address.";
        email.style.border = "2px solid red";
        isValid = false;
    }

    if (!passwordPattern.test(password.value.trim())) {
        passwordError.innerText =
            "Password must contain uppercase, lowercase, number, and special character.";
        password.style.border = "2px solid red";
        isValid = false;
    }

    return isValid;
}
</script>
</body>
</html>