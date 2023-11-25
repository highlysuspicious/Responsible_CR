package com.example.responsible_cr;

public class exam {

    String no;
    String date;
    String time;
    String subject;
    String syllabus;

    public exam(String no,String date,String time,String subject,String syllabus)
    {
        this.no = no;
        this.date = date;
        this.time = time;
        this.subject = subject;
        this.syllabus = syllabus;
    }

    public exam()
    {
        this.no = "";
        this.date = "";
        this.time = "";
        this.subject = "";
        this.syllabus = "";
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
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

    public void setTime(String time) { this.time = time; }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) { this.subject = subject; }

    public String getSyllabus() {
        return syllabus;
    }

    public void setSyllabus(String syllabus) { this.syllabus = syllabus; }

}
