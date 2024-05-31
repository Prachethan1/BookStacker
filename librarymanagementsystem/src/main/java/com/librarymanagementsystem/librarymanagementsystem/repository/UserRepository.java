package com.librarymanagementsystem.librarymanagementsystem.repository;

import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByPassword(String password);
    User findByUsername(String username);
}
