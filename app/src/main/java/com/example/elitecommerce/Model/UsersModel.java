package com.example.elitecommerce.Model;

public class UsersModel {
    String userEmail;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public UsersModel(String userEmail) {
        this.userEmail = userEmail;
    }
}
