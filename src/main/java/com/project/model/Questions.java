package com.project.model;

public class Questions {
    private int id;
    private String question;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private int correctAnswer;

    // Getter for id
    public int getId() {
        return id;
    }

    // Setter for id
    public void setId(int id) {
        this.id = id;
    }

    // Getter for question
    public String getQuestion() {
        return question;
    }

    // Setter for question
    public void setQuestion(String question) {
        this.question = question;
    }

    // Getter for option1
    public String getOption1() {
        return option1;
    }

    // Setter for option1
    public void setOption1(String option1) {
        this.option1 = option1;
    }

    // Getter for option2
    public String getOption2() {
        return option2;
    }

    // Setter for option2
    public void setOption2(String option2) {
        this.option2 = option2;
    }

    // Getter for option3
    public String getOption3() {
        return option3;
    }

    // Setter for option3
    public void setOption3(String option3) {
        this.option3 = option3;
    }

    // Getter for option4
    public String getOption4() {
        return option4;
    }

    // Setter for option4
    public void setOption4(String option4) {
        this.option4 = option4;
    }

    // Getter for correctAnswer
    public int getCorrectAnswer() {
        return correctAnswer;
    }

    // Setter for correctAnswer
    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}