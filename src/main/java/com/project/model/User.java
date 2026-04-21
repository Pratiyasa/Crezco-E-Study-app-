package com.project.model;

public class User {

    private String Username;
    private String email;
    private String password;
    private String image;
    private int totalTests;
    private int streak;
    private int id;
    private String phone;
    private String gender;
    private String birthdate;
    private String location;
    private String skills;
    private String work;
    private String education;
    private String university;
    private String about;
    private String profilePic;
    

    // Getters
    public String getUsername() { return Username; }
    public String getEmail() { return email; }
    public String getImage() { return image; }
    public String getPassword() { return password; }
    public int getTotalTests() { return totalTests; }
    public int getStreak() { return streak; }
    public int getId() { return id; }

    // Setters
    public void setUsername(String name) { this.Username = name; }
    public void setEmail(String email) { this.email = email; }
    public void setImage(String image) { this.image = image; }
    public void setPassword(String password) { this.password = password; }
    public void setTotalTests(int totalTests) { this.totalTests = totalTests; }
    public void setStreak(int streak) { this.streak = streak; }
    public void setId(int id) { this.id = id; }
    
    
    
    
    
    
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }


    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }


    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }


    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }


    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }
}