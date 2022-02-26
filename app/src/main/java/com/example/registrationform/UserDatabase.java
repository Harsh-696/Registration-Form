package com.example.registrationform;

import java.io.Serializable;

public class UserDatabase implements Serializable {
    int id;
    String username;
    String fullName;
    String phoneNumber;
    String email;
    String password;
    String confirmPassword;
//    int image;

    public UserDatabase(int id, String username, String fullName, String phoneNumber, String email, String password, String confirmPassword) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;

    }
    public UserDatabase(String username, String fullName, String phoneNumber, String email, String password, String confirmPassword) {
        this.username = username;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;

    }

    public UserDatabase() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
