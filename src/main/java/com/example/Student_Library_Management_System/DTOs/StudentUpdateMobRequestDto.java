package com.example.Student_Library_Management_System.DTOs;

public class StudentUpdateMobRequestDto {

    private String mobNo;

    private int id;

    public StudentUpdateMobRequestDto() {
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
