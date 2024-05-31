package com.librarymanagementsystem.librarymanagementsystem.controller;

import com.librarymanagementsystem.librarymanagementsystem.dto.UserLoginDetails;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.UserException;
import com.librarymanagementsystem.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User addUser(@ModelAttribute User user) throws UserException {
        //User addUser = userService.addUser(user);

    	//System.out.println(user.toString());
        return userService.addUser(user);

       // User addedUser = userService.addUser(user);
       // return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
    }

    @GetMapping("/userId/{id}")
    public User getUser(@PathVariable int id) throws UserException{
        return userService.getUser(id);

    }

    @GetMapping("/users")
    public List<User> getUsers() throws UserException {
        return userService.getUsers();
    }

    @PutMapping("/update")
    public User updateUser(@RequestBody User updatedUser) throws UserException {
        return userService.updateUser(updatedUser);
        }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) throws UserException{
         userService.deleteUser(id);
    }

//    @PostMapping("/UserLogin")
//    public ResponseEntity<String> loginUser(@RequestParam("name") String name,@RequestParam("password") String password) throws UserException{
//        boolean loginUser = userService.userLogin(name, password);
//        if(loginUser) {
//            return ResponseEntity.ok("Login successful");
//            //return HttpStatus.OK;
//            //return ResponseEntity.ok().build();
//        }
//        else {
//            //return ResponseEntity.notFound().build();
//            //return HttpStatus.NOT_FOUND;
//           return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//        }
//    }
//
}
