package com.librarymanagementsystem.librarymanagementsystem.dto;

public class UserLoginDetails {
    private String username;
    private String role;
    private String password;

    public UserLoginDetails(String username,String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String name) {
        this.username = name;
    }
    
    
}
