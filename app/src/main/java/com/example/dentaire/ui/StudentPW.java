package com.example.dentaire.ui;

import java.io.Serializable;
import java.util.Date;

public class StudentPW implements Serializable {
private  StudentPWPk id;
    private String imageFront;
    private String imageSide;
    private double alpha1;
    private double alpha2;
    private double alpha3;
    private double beta1;
    private double beta2;
    private double beta3;
    private double note;
    private String time;
    private Date date;

    // Add other fields as needed

    public StudentPW() {
    }

    public StudentPW(StudentPWPk id, String imageFront, String imageSide, double alpha1, double alpha2, double alpha3, double beta1, double beta2, double beta3, double note, String time, Date date) {
        this.id = id;
        this.imageFront = imageFront;
        this.imageSide = imageSide;
        this.alpha1 = alpha1;
        this.alpha2 = alpha2;
        this.alpha3 = alpha3;
        this.beta1 = beta1;
        this.beta2 = beta2;
        this.beta3 = beta3;
        this.note = note;
        this.time = time;
        this.date = date;
    }



    public StudentPWPk getId() {
        return id;
    }

    public void setId(StudentPWPk id) {
        this.id = id;
    }

    public String getImageFront() {
        return imageFront;
    }

    public void setImageFront(String imageFront) {
        this.imageFront = imageFront;
    }

    public String getImageSide() {
        return imageSide;
    }

    public void setImageSide(String imageSide) {
        this.imageSide = imageSide;
    }

    public double getAlpha1() {
        return alpha1;
    }

    public void setAlpha1(double alpha1) {
        this.alpha1 = alpha1;
    }

    public double getAlpha2() {
        return alpha2;
    }

    public void setAlpha2(double alpha2) {
        this.alpha2 = alpha2;
    }

    public double getAlpha3() {
        return alpha3;
    }

    public void setAlpha3(double alpha3) {
        this.alpha3 = alpha3;
    }

    public double getBeta1() {
        return beta1;
    }

    public void setBeta1(double beta1) {
        this.beta1 = beta1;
    }

    public double getBeta2() {
        return beta2;
    }

    public void setBeta2(double beta2) {
        this.beta2 = beta2;
    }

    public double getBeta3() {
        return beta3;
    }

    public void setBeta3(double beta3) {
        this.beta3 = beta3;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}