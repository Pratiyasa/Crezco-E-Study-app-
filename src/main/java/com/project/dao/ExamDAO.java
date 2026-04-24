package com.project.dao;

import java.sql.*;
import java.util.*;
import com.project.model.Questions;
import com.project.utils.DBConnection;

import java.sql.Connection;

public class ExamDAO {

    public List<Questions> getRandomQuestions(String subject) {
        List<Questions> list = new ArrayList<>();

        try {
        	System.out.println("=======question");
        	Connection con = DBConnection.getConnection();

        	String sql = "SELECT * FROM questions WHERE LOWER(subject)=LOWER(?) ORDER BY RAND() LIMIT 5";
        	System.out.println("=======question---");
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, subject);
            
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Questions q = new Questions();
  

                q.setId(rs.getInt("id"));
                q.setQuestion(rs.getString("question"));
                q.setOption1(rs.getString("option1"));
                q.setOption2(rs.getString("option2"));
                q.setOption3(rs.getString("option3"));
                q.setOption4(rs.getString("option4"));
                q.setCorrectAnswer(rs.getInt("correct_answer"));

                list.add(q);
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public void saveResult(int userId, String subject, int score, int correct, int wrong) {
        try {
            Connection con = DBConnection.getConnection();

            String sql = "INSERT INTO exam_results(user_id, subject, score, correct_count, wrong_count) VALUES(?,?,?,?,?)";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);
            ps.setString(2, subject);
            ps.setInt(3, score);
            ps.setInt(4, correct);
            ps.setInt(5, wrong);

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public Map<String, Object> getUserStats(int userId) {
        Map<String, Object> stats = new HashMap<>();

        try {
            Connection con = DBConnection.getConnection();

            // total tests
            String totalSql = "SELECT COUNT(*) AS totalTests FROM exam_results WHERE user_id=?";
            PreparedStatement ps1 = con.prepareStatement(totalSql);
            ps1.setInt(1, userId);
            ResultSet rs1 = ps1.executeQuery();

            if(rs1.next()){
                stats.put("totalTests", rs1.getInt("totalTests"));
            }

            // average score
            String avgSql = "SELECT AVG(score) AS avgScore FROM exam_results WHERE user_id=?";
            PreparedStatement ps2 = con.prepareStatement(avgSql);
            ps2.setInt(1, userId);
            ResultSet rs2 = ps2.executeQuery();

            if(rs2.next()){
                stats.put("avgScore", rs2.getDouble("avgScore"));
            }

            // recent tests
            String recentSql = "SELECT subject, score FROM exam_results WHERE user_id=? ORDER BY exam_date DESC LIMIT 3";
            PreparedStatement ps3 = con.prepareStatement(recentSql);
            ps3.setInt(1, userId);
            ResultSet rs3 = ps3.executeQuery();

            List<String> recentTests = new ArrayList<>();

            while(rs3.next()){
                recentTests.add(rs3.getString("subject") + " - " + rs3.getInt("score") + "/5");
            }

            stats.put("recentTests", recentTests);

        } catch(Exception e){
            e.printStackTrace();
        }

        return stats;
    }
    public Map<String, Double> getSubjectWiseAverage(int userId) {
        Map<String, Double> subjectAvg = new LinkedHashMap<>();

        try {
            Connection con = DBConnection.getConnection();

            String sql = "SELECT subject, AVG(score) AS avgScore " +
                         "FROM exam_results WHERE user_id=? " +
                         "GROUP BY subject LIMIT 3";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                subjectAvg.put(rs.getString("subject"), rs.getDouble("avgScore"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return subjectAvg;
    }
}