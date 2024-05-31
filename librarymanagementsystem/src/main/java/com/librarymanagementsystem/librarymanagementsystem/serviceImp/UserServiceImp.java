package com.librarymanagementsystem.librarymanagementsystem.serviceImp;

import com.librarymanagementsystem.librarymanagementsystem.dto.UserLoginDetails;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.UserException;
import com.librarymanagementsystem.librarymanagementsystem.repository.BookRepository;
import com.librarymanagementsystem.librarymanagementsystem.repository.UserRepository;
import com.librarymanagementsystem.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User save(UserLoginDetails userLoginDetails) {
        User user = new User(userLoginDetails.getUsername(), userLoginDetails.getRole(), passwordEncoder.encode(userLoginDetails.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User save(User user) {
        User user1 = new User(user.getUsername(), user.getPhone(),user.getAddress(), user.getBranch(), passwordEncoder.encode(user.getPassword()), user.getRole(),user.getNoOfBooksIssued(), user.getTotalFine());
        return userRepository.save(user1);
    }

    @Override
    public User addUser(User user) throws UserException {
        if(userRepository.findByUsername(user.getUsername())!= null && userRepository.findByPassword(user.getPassword())!= null) {
            throw new UserException("user details already exists");
        }
        return userRepository.save(user);
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user){
        User existingUser = userRepository.findById(user.getUserId()).orElse(null);

        assert existingUser != null;
        existingUser.setUsername(user.getUsername());
        existingUser.setPhone(user.getPhone());
        existingUser.setBranch(user.getBranch());
        existingUser.setAddress(user.getAddress());
        existingUser.setNoOfBooksIssued(user.getNoOfBooksIssued());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(int id) {
         userRepository.deleteById(id);
    }

    @Override
    public boolean userLogin(String name, String password){
        User user = userRepository.findByUsername(name);
        if(user!=null && user.getPassword().equals(password)){
            return true;
        }
        else{
            return false;
        }
//    	User user = userRepository.findByNameandPassword(name, password);
//        return user != null;
    }

    @Override
    public List<User> getUsers(){
//        List<User> userList = userRepository.findAll();
        return userRepository.findAll();
    }

    @Override
    public void updateTotalFine(User user, double fine){
        user.setTotalFine(user.getTotalFine() + fine);
        userRepository.save(user);
    }

    @Override
    public void payFine(User user, double amount){
        double currentFine = user.getTotalFine();
        if (currentFine == amount) {
            user.setTotalFine(0.0);
        } else if (currentFine > amount) {
            user.setTotalFine(currentFine - amount);
        } else{
            user.setTotalFine(currentFine-amount);
        }
        userRepository.save(user);
    }

//    public User getAuthenticatedUser() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        // Assuming your User class implements UserDetails, which is typical with Spring Security
//        if (authentication != null && authentication.getPrincipal() instanceof User) {
//            return (User) authentication.getPrincipal();
//        }
//
//        // If user is not authenticated or the principal is not an instance of User
//        return null;
//    }
}
