<%@ page import="java.util.*,com.project.model.Questions" %>
<!DOCTYPE html>
<html>
<head>
    <title>Result</title>
    <link rel="stylesheet" href="CSS/exam.css">
</head>
<body>

<div class="container">
    <h1>Exam Result</h1>

    <h2>Total Score: <%= request.getAttribute("score") %> out of 5</h2>
    <h3>Correct Answers: <%= request.getAttribute("correct") %></h3>
    <h3>Wrong Answers: <%= request.getAttribute("wrong") %></h3>

    <hr>

    <h3>Wrong Attempted Questions:</h3>

    <%
    List<Questions> wrongQuestions = (List<Questions>) request.getAttribute("wrongQuestions");

    if(wrongQuestions != null && !wrongQuestions.isEmpty()){
        int i = 1;
        for(Questions q : wrongQuestions){
    %>

        <p><b><%= i %>. <%= q.getQuestion() %></b></p>
        <p>
    Correct Answer:
    <%
        if(q.getCorrectAnswer() == 0){
            out.print(q.getOption1());
        } else if(q.getCorrectAnswer() == 1){
            out.print(q.getOption2());
        } else if(q.getCorrectAnswer() == 2){
            out.print(q.getOption3());
        } else if(q.getCorrectAnswer() == 3){
            out.print(q.getOption4());
        }
    %>
</p>
        <hr>

    <%
        i++;
        }
    } else {
    %>
        <p>Excellent! No wrong answers.</p>
    <%
    }
    %>

    <button type="button" onclick="goHome()">Go Home</button>
</div>

<script>
function goHome(){
    window.location.href="index.jsp";
}
</script>

</body>
</html>