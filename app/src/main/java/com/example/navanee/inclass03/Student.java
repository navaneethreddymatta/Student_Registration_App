package com.example.navanee.inclass03;

import java.io.Serializable;

/**
 * Created by navanee on 07-09-2016.
 */
public class Student implements Serializable {
    private String name;
    private String emailID;
    private int department;
    private int mood;
    private int accountState;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public Student(String name, String emailID, int department, int mood) {
        this.name = name;
        this.emailID = emailID;
        this.department = department;
        this.mood = mood;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", emailID='" + emailID + '\'' +
                ", department=" + department +
                ", mood=" + mood +
                '}';
    }
}
