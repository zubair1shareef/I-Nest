package com.example.recylerview;

public class Signupdata {
    String name,email,city,image;

    public Signupdata(String name, String email, String city,String image) {
        this.name = name;
        this.email = email;
        this.city = city;
        this.image=image;
    }

    public Signupdata() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
