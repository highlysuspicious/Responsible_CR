package com.example.responsible_cr;

public class routine {

    String no;
    String date;
    String day;
    String p1;
    String p2;
    String p3;
    String p4;
    String p5;

    public routine(String no,String date, String day, String p1, String p2, String p3, String p4, String p5) {
        this.no = no;
        this.day = day;
        this.date = date;
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
        this.p5 = p5;
    }

    public routine()
    {
        this.no = "";
        this.day = "";
        this.date = "";
        this.p1 = "";
        this.p2 = "";
        this.p3 = "";
        this.p4 = "";
        this.p5 = "";
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }


    public String getDay() {
        return day;
    }

    public String getDate() {
        return date;
    }

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public String getP3() {
        return p3;
    }

    public String getP4() {
        return p4;
    }

    public String getP5() {
        return p5;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setP1(String p1) {
        this.p1 = p1;
    }

    public void setP2(String p2) {
        this.p2 = p2;
    }

    public void setP3(String p3) {
        this.p3 = p3;
    }

    public void setP4(String p4) {
        this.p4 = p4;
    }

    public void setP5(String p5) {
        this.p5 = p5;
    }


}
