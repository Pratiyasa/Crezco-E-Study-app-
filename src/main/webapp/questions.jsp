<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,com.project.model.Questions" %>


<!DOCTYPE html>
<html>
<head>
    <title>Exam Page</title>
    <link rel="stylesheet" href="CSS/exam.css">
</head>
<body>

<div class="exam-container">
    <div class="top-bar">
        <div id="subjectTitle">
            Subject: <%= request.getAttribute("subject") %>
        </div>
        <div id="timer"></div>
    </div>

    <form id="quizForm" action="SubmitExamServlet" method="post">

<input type="hidden" name="subject" value="<%= request.getAttribute("subject") %>">

<%
List<Questions> questions = (List<Questions>) request.getAttribute("questions");
int i = 1;
for(Questions q : questions){
%>

<div class="question-box">
    <p><b><%= i %>. <%= q.getQuestion() %></b></p>

    <label><input type="radio" name="q<%=q.getId()%>" value="0"> <%= q.getOption1() %></label><br>
    <label><input type="radio" name="q<%=q.getId()%>" value="1"> <%= q.getOption2() %></label><br>
    <label><input type="radio" name="q<%=q.getId()%>" value="2"> <%= q.getOption3() %></label><br>
    <label><input type="radio" name="q<%=q.getId()%>" value="3"> <%= q.getOption4() %></label><br>
</div>

<hr>

<%
i++;
}
%>

<button type="submit">Submit</button>
</form>
</div>
<script src="script2.js"></script>
<script>
    window.onload = function() {
        startTimer(15);
    };
</script>
</body>

</html>