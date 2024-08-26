package com.example.eventx20.Database.DataModel;

import androidx.annotation.NonNull;

public class User {
    private String Name;
    private String Email;
    private String Password;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String name,String email , String password){
        this.Name = name;
        this.Email = email;
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "Name='" + Name + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
