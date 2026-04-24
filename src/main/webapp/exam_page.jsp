<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.project.model.User" %>
<%

User user = (User) session.getAttribute("user");

// 🔐 Protect page
if(user == null){
    response.sendRedirect("login.jsp");
    return;
}
%>

<!DOCTYPE html>
<html>
<head>
    <title>Online Exam Portal</title>
    <link rel="stylesheet" href="CSS/exam.css">
</head>
<body>
<a href="index.jsp" id="logo">Crezco</a>
<div class="container">
    <h1>Online Examination</h1>

    <label>Select Subject:</label>
    <select id="subject">
        <option value="" selected disabled>-- Choose Subject --</option>
        <option value="dbms">DBMS</option>
        <option value="os">Operating System</option>
        <option value="oops">OOPs</option>
    </select>

    <div class="instructions">
        <h3>Instructions:</h3>
        <ul>
            <li>Total duration of the exam is <b>5 minutes</b>.</li>
            <li>The timer will start immediately after clicking <b>Start Exam</b>.</li>
            <li>Each question carries <b>1 mark</b>. No negative marking.</li>
            <li>All questions are <b>multiple choice questions (MCQs)</b>.</li>
            <li>You can select only <b>one option</b> for each question.</li>
            <li>Do not refresh or close the browser during the exam.</li>
            <li>Switching tabs or minimizing the window may lead to warning alerts.</li>
            <li>The exam will be <b>auto-submitted</b> when time runs out.</li>
            <li>Click the <b>Submit</b> button once you have completed the exam.</li>
            <li>Ensure you have a <b>stable internet connection</b>.</li>
            <li>Use of unfair means is strictly prohibited.</li>
        </ul>

        <p style="margin-top:10px;">
            <b>Note:</b> Once you start the exam, you cannot go back to change the subject.
        </p>
    </div>

    <button onclick="startExam()">Start Exam</button>
</div>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

<script src="script2.js"></script>

</body>
</html>