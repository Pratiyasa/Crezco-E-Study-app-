package com.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;

import com.project.model.User;
import com.project.utils.DBConnection;

public class UserDAO {

    public boolean registerUser(User user) {
        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());

            int row = ps.executeUpdate();

            if (row > 0) {
                status = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return status;
    }
    
    
    
    public User login(String email, String password) {
        User user = null;

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE email=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, email);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                
                user.setPhone(rs.getString("phone"));
                user.setGender(rs.getString("gender"));
                user.setBirthdate(rs.getString("birthdate"));
                user.setLocation(rs.getString("location"));
                user.setSkills(rs.getString("skills"));
                user.setWork(rs.getString("work"));
                user.setEducation(rs.getString("education"));
                user.setUniversity(rs.getString("university"));
                user.setAbout(rs.getString("about"));
                user.setProfilePic(rs.getString("profile_pic"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    
    
    
    public boolean updateUser(User user) {
        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE users SET phone=?, gender=?, birthdate=?, location=?, skills=?, work=?, education=?, university=?, about=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user.getPhone());
            ps.setString(2, user.getGender());

            // 🔥 FIX DATE
            if(user.getBirthdate() != null && !user.getBirthdate().isEmpty()) {
                ps.setDate(3, java.sql.Date.valueOf(user.getBirthdate()));
            } else {
                ps.setNull(3, java.sql.Types.DATE);
            }

            ps.setString(4, user.getLocation());
            ps.setString(5, user.getSkills());
            ps.setString(6, user.getWork());
            ps.setString(7, user.getEducation());
            ps.setString(8, user.getUniversity());
            ps.setString(9, user.getAbout());
            ps.setInt(10, user.getId());

            System.out.println("Before executeUpdate");

            int rows = ps.executeUpdate();

            System.out.println("Rows updated: " + rows);

            if(rows > 0){
                status = true;
            }

        } catch(Exception e){
            e.printStackTrace();   // 🔥 MUST HAVE
        }

        return status;
    }
    
    
    
    public boolean updateProfilePic(int userId, String path) {
        boolean status = false;

        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE users SET profile_pic=? WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, path);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            if(rows > 0){
                status = true;
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }
    
    public boolean emailExists(String email) {
        boolean exists = false;

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                exists = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return exists;
    }
}