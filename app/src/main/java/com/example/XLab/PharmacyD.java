package com.example.XLab;

public class PharmacyD {

    private String ID;
    private String Pno;
    private String Pname;
    private String Address;
    private int Phno;
    private String Date;
    private String Url;

    public PharmacyD() {
    }

    public PharmacyD(String ID, String pno, String pname, String address, int phno, String date,String Url) {
        this.ID = ID;
        Pno = pno;
        Pname = pname;
        Address = address;
        Phno = phno;
        Date = date;
        this.Url=Url;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPno() {
        return Pno;
    }

    public void setPno(String pno) {
        Pno = pno;
    }

    public String getPname() {
        return Pname;
    }

    public void setPname(String pname) {
        Pname = pname;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getPhno() {
        return Phno;
    }

    public void setPhno(int phno) {
        Phno = phno;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }


}
