package com.example.toptativa2.Models;


public class User {
    private int id;
    private String fullname;
    private String email;
    private String password;
    private String user_type;
    private int sus_method;
    private int active;



    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public int getSus_method() {
        return sus_method;
    }

    public void setSus_method(int sus_method) {
        this.sus_method = sus_method;
    }

}
