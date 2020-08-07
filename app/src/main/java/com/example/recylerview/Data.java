package com.example.recylerview;

public class Data {
    String city,email,image,name;

    public Data() {
    }

    public Data(String city, String email, String image, String name) {
        this.city = city;
        this.email = email;
        this.image = image;
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
