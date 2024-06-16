package com.librarymanagementsystem.librarymanagementsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "`user`")
public class User {

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", branch='" + branch + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    @Id
    @GeneratedValue
    private int userId;
    private String username;
    private String phone;
    private String address;
    private String branch;
    private String password;
    private String role="USER";
    private int noOfBooksIssued;
    private Double totalFine;


    public User(String username, String phone, String address, String branch, String password, String role, int noOfBooksIssued, Double totalFine) {
        this.username = username;
        this.phone = phone;
        this.address = address;
        this.branch = branch;
        this.password = password;
        this.role = role;
        this.noOfBooksIssued = noOfBooksIssued;
        this.totalFine = totalFine;
    }


    public User(String username, String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }

    public User(){
        super();
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Double getTotalFine() {
        return totalFine;
    }

    public void setTotalFine(Double totalFine) {
        this.totalFine = totalFine;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNoOfBooksIssued() {
        return noOfBooksIssued;
    }

    public void setNoOfBooksIssued(int noOfBooksIssued) {
        this.noOfBooksIssued = noOfBooksIssued;
    }
}
