package com.example.XLab;

public class Appointmentlab {

    private String pName,Email,date,time;
    private Integer contact,TestId;

    public Appointmentlab() {
    }

    public Appointmentlab(String patientName, String email, String date, String time, Integer contactNumber, Integer testId) {
        this.pName = patientName;
        this.Email = email;
        this.date = date;
        this.time = time;
        this.contact = contactNumber;
        this.TestId = testId;
    }

    public String getPatientName() {
        return pName;
    }

    public void setPatientName(String patientName) {
        this.pName = patientName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getContactNumber() {
        return contact;
    }

    public void setContactNumber(Integer contactNumber) {
        this.contact = contactNumber;
    }

    public Integer getTestId() {
        return TestId;
    }

    public void setTestId(Integer chemId) {
        this.TestId = TestId;
    }
}
