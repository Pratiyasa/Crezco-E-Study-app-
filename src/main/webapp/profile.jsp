<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="com.project.model.User" %>
<%@ page import="java.util.*,com.project.dao.ExamDAO" %>
<%
User user = (User) session.getAttribute("user");

// 🔐 Protect page
if(user == null){
    response.sendRedirect("login.jsp");
    return;
}
%>
<%
ExamDAO dao = new ExamDAO();

Map<String, Object> stats = dao.getUserStats(user.getId());
int totalTests = (Integer)stats.get("totalTests");
double avgScore = (Double)stats.get("avgScore");

Map<String, Double> subjectAvg = dao.getSubjectWiseAverage(user.getId());
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile</title>

    <!-- Your CSS -->
    <link rel="stylesheet" href="CSS/profile.css?=v2">
</head>

<body>

<!-- NAVBAR -->
<div id="navbar">
        <a href="index.jsp" id="logo">Crezco</a>

        <div id="options">
            <h4><a href="profile.jsp">PROFILE</a></h4>
            <h4>FORUM</h4>
            <h4><a href="exam_page.jsp">E-EXAM</a></h4>
        </div>

        <div id="logout">
                <h3>Welcome, <%= user.getUsername() %></h3>
                <a href="LogoutServlet" class="btn">Logout</a>
        </div>
    </div>
<!-- MAIN -->
<div class="profile-container">

    <!-- LEFT PANEL -->
    <div class="profile-left">

     <!-- PROFILE IMAGE -->
        <form action="<%= request.getContextPath() %>/UploadProfilePicServlet"
      method="post"
      enctype="multipart/form-data">

    <div class="profile-img-container">

        <!-- PROFILE IMAGE DISPLAY -->
        <img src="<%= request.getContextPath() %>/image/<%= user.getProfilePic() %>" 
     class="profile-img" />
     
        <!-- hidden file input -->
        <input type="file"
               id="imageUpload"
               name="profilePic"
               accept="image/*"
               hidden
               onchange="this.form.submit()">

        <!-- edit button (opens file picker) -->
        <label for="imageUpload" class="edit-icon">✎</label>

    </div>

    </form>

        <!-- STATIC USER DATA -->
        <h2><%= user.getUsername() %></h2>
        <p class="email"><%= user.getEmail() %></p>
        <!-- CARDS -->
			<div class="about-card">
				    <h3>Stats</h3>
				
				    <p><strong>Total Tests:</strong> <%= totalTests %></p>
				    <p><strong>Overall Avg Score:</strong> <%= String.format("%.2f", avgScore) %></p>
				
				    
				   </div>
				<div class="about-card">
				    <h4>Subject-wise Avg</h4>
				
				    <%
				    for(Map.Entry<String, Double> entry : subjectAvg.entrySet()){
				    %>
				        <p>
				            <strong><%= entry.getKey().toUpperCase() %>:</strong>
				            <%= String.format("%.2f", entry.getValue()) %>
				        </p>
				    <%
				    }
				    %>
				</div>
        </div>

    

    <!-- RIGHT PANEL -->
    <div class="profile-right">

        <!-- EDIT PROFILE -->
        <div class="edit-profile">

            <form action="UpdateProfileServlet" method="post" onsubmit="return validateProfileForm()">

                <div class="form-grid">

					<div class="form-group">
					    <label>Phone Number</label>
					    <input type="text" id="phone" name="phone"
					           value="<%= user.getPhone() != null ? user.getPhone() : "" %>">
					    <small id="phoneError" class="error-msg"></small>
					</div>

                    <div class="form-group">
					    <label>Gender</label>
					    <select name="gender">
					        <option value="Male" <%= "Male".equals(user.getGender()) ? "selected" : "" %>>Male</option>
					        <option value="Female" <%= "Female".equals(user.getGender()) ? "selected" : "" %>>Female</option>
					        <option value="Other" <%= "Other".equals(user.getGender()) ? "selected" : "" %>>Other</option>
					    </select>
					</div>

                    <div class="form-group">
                        <label>Birth Date</label>
                        <input type="date" name="birthdate" value="<%= user.getBirthdate() != null ? user.getBirthdate() : "" %>">
                    </div>

                    <div class="form-group">
                        <label>Location</label>
                        <input type="text" name="location" value="<%= user.getLocation() != null ? user.getLocation() : "" %>">
                    </div>

                    <div class="form-group">
                        <label>Skills</label>
                        <input type="text" name="skills" value="<%= user.getSkills() != null ? user.getSkills() : "" %>">
                    </div>

                    <div class="form-group">
                        <label>Work</label>
                        <input type="text" name="work" value="<%= user.getWork() != null ? user.getWork() : "" %>">
                    </div>

                    <div class="form-group">
                        <label>Education</label>
                        <input type="text" name="education" value="<%= user.getEducation() != null ? user.getEducation() : "" %>">
                    </div>

                    <div class="form-group">
                        <label>University</label>
                        <input type="text" name="university" value="<%= user.getUniversity() != null ? user.getUniversity() : "" %>">
                    </div>

                    <div class="form-group">
                        <label>About Me</label>
                        <textarea rows="4" name="about"><%= user.getAbout() != null ? user.getAbout() : "" %></textarea>
                    </div>

                </div>

                <!-- BUTTON -->
                <button type="submit" class="save-btn">Save Changes</button>

            </form>

        </div>

    </div>

</div>

<!-- JS for Image Preview -->
<script>
document.getElementById("imageUpload").addEventListener("change", function(event) {
    const file = event.target.files[0];

    if (file) {
        // preview
        const imageURL = URL.createObjectURL(file);
        document.getElementById("profileImage").src = imageURL;

        // 🔥 auto submit form
        this.form.submit();
    }
});

function validateProfileForm() {
    let phone = document.getElementById("phone");
    let phoneError = document.getElementById("phoneError");

    phoneError.innerText = "";
    phone.style.border = "";

    let phonePattern = /^[0-9]{10}$/;

    if (!phonePattern.test(phone.value.trim())) {
        phoneError.innerText = "Phone number must be exactly 10 digits.";
        phone.style.border = "2px solid red";
        return false;
    }

    return true;
}
</script>

</body>
</html>