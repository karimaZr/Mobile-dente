package com.example.dentaire.ui;

import java.io.Serializable;

public class Professor implements Serializable {
    private String grade;

    // Add other fields as needed

    public Professor() {
    }

    // Add getters and setters for all fields

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
