package com.librarymanagementsystem.librarymanagementsystem.repository;

import com.librarymanagementsystem.librarymanagementsystem.entity.Admin;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Admin findByPhone(String phone);
    //Admin findByName(String name);

    Admin findByName(String name);



}
