package com.example.dentaire.ui;
import java.io.Serializable;
import java.util.Objects;

public class StudentPWPk implements Serializable {

    private int studentId;
    private int pwId;

    public StudentPWPk(int studentId, int pwId) {
        this.studentId = studentId;
        this.pwId = pwId;
    }

    public StudentPWPk() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getPwId() {
        return pwId;
    }

    public void setPwId(int pwId) {
        this.pwId = pwId;
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentPWPk that = (StudentPWPk) o;
        return Objects.equals(studentId, that.studentId) &&
                Objects.equals(pwId, that.pwId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, pwId);
    }
}
