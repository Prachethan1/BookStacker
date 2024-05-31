package com.librarymanagementsystem.librarymanagementsystem.service;

import com.librarymanagementsystem.librarymanagementsystem.dto.UserLoginDetails;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.UserException;

import java.util.List;

public interface UserService {
    User addUser(User user) throws UserException;
    User getUser(int id) throws UserException;
    User updateUser(User user) throws UserException;
    void deleteUser(int id) throws UserException;
    boolean userLogin(String name, String password) throws UserException;
    List<User> getUsers() throws UserException;
    void updateTotalFine(User user, double fine) throws UserException;
    void payFine(User user, double amount) throws UserException;
    User save(User user);
    User findByUsername(String username);
    User save(UserLoginDetails userLoginDetails);
}
