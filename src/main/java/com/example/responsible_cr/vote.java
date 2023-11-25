package com.example.responsible_cr;

public class vote {

    String no;
    String option;
    int count;

    public vote(String no,String option,int count)
    {
        this.no = no;
        this.option = option;
        this.count = count;
    }

    public vote()
    {
        this.no = "";
        this.option = "";
        this.count = 0;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }


    public String getOption() {
        return option;
    }


    public void setOpition(String option) {
        this.option = option;
    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
