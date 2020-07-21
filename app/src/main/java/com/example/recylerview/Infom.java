package com.example.recylerview;

public class Infom {
    String name,rollno,descp,image;

    public Infom(String name, String rollno ,String descp,String image) {
        this.name = name;
        this.rollno = rollno;
        this.descp=descp;
        this.image=image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
