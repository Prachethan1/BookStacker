package com.librarymanagementsystem.librarymanagementsystem.service;

import com.librarymanagementsystem.librarymanagementsystem.entity.Admin;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.AdminException;
import com.librarymanagementsystem.librarymanagementsystem.exception.UserException;

import java.util.List;

public interface AdminService {
    Admin addAdmin(Admin admin) throws AdminException;
    Admin getAdmin(int id) throws AdminException;
    List<Admin> getAdmins() throws AdminException;
    Admin updateAdmin(Admin admin) throws AdminException;
    void deleteAdmin(int id) throws AdminException;
    Admin adminLogin(String name, String password) throws AdminException;
}
