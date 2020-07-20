package com.example.recylerview;

public class Infom {
    String name,rollno,descp;

    public Infom(String name, String rollno ,String descp) {
        this.name = name;
        this.rollno = rollno;
        this.descp=descp;
    }

    public Infom() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollno() {
        return rollno;
    }

    public void setRollno(String rollno) {
        this.rollno = rollno;
    }

    public String getDescp() {
        return descp;
    }

    public void setDescp(String descp) {
        this.descp = descp;
    }
}
