package com.androiddevnkds.myapplication.model;

import android.net.Uri;

public class UserModel {

    private String email;
    private String name;
    private String uriImage;

    public UserModel() {
    }

    public UserModel(String email, String name, String uriImage) {
        this.email = email;
        this.name = name;
        this.uriImage = uriImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUriImage() {
        return uriImage;
    }

    public void setUriImage(String uriImage) {
        this.uriImage = uriImage;
    }
}
