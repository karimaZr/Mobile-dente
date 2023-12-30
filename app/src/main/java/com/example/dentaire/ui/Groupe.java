package com.example.dentaire.ui;

import java.io.Serializable;
import java.util.List;

public class Groupe implements Serializable {
    private Integer id;
    private String code;
    private String year;
    private Professor professor;
    private List<Student> students;
    private List<PW> pws;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<PW> getPws() {
        return pws;
    }

    public void setPws(List<PW> pws) {
        this.pws = pws;
    }
}