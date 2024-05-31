package com.librarymanagementsystem.librarymanagementsystem.controller;

import com.librarymanagementsystem.librarymanagementsystem.dto.UserLoginDetails;
import com.librarymanagementsystem.librarymanagementsystem.entity.Book;
import com.librarymanagementsystem.librarymanagementsystem.entity.User;
import com.librarymanagementsystem.librarymanagementsystem.exception.BookException;
import com.librarymanagementsystem.librarymanagementsystem.exception.UserException;
import com.librarymanagementsystem.librarymanagementsystem.service.BookService;
import com.librarymanagementsystem.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;


@Controller
public class IndexController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserDetailsService userDetailsService;

//    @RequestMapping("/")
//    public ModelAndView index(Model model){
////        model.addAttribute("title", "Home - Smart Contact Manager");
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("index.html");
//        return modelAndView;
//    }

    @GetMapping("/")
    public String list(){
//        model.addAttribute("title", "Library");
        return "index";
    }


    @GetMapping("/about")
    public String list1(){
        return "about";
    }


//    @GetMapping("/login")
//    public String loginPage(Model model){
//        model.addAttribute("userLogin",new User());
//        return "login";
//    }
@GetMapping("/login")
public String login(Model model, UserLoginDetails userLoginDetails) {
    model.addAttribute("userLogin", userLoginDetails);
    return "login";
}


//    @GetMapping("/signup")
//    public String signup(Model model, UserLoginDetails userLoginDetails){
//    	model.addAttribute("userForm", userLoginDetails);
//        return "signup";
//    }

//   2 @GetMapping("/signup")
//    public String signup(Model model){
//        model.addAttribute("user", new User()); // Provide an empty User object for the form
//        return "signup";
//    }



    @GetMapping("/signup")
    public String signup(@ModelAttribute("user") User user) {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerSave(@ModelAttribute("user") User user, Model model) throws UserException {
//       User existingUser = userService.findByUsername(user.getUsername());
//        if (user != null) {
//            model.addAttribute("userexist", existingUser);
//            return "signup";
//        }
//        System.out.println(user);
//        userService.save(user);
//        return "redirect:/signup?success";
//
        userService.save(user);
        model.addAttribute("message", "Registered Successfully!");
        return "signup";
    }


    @GetMapping("/user-dashboard")
    public String userDash(){
        //model.addAttribute("userForm",new User());
        return "userdashboard";
    }
    
    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }
    
//    @GetMapping("/bookRegister")
//    public String addBooks(Model model){
//        model.addAttribute("bookForm",new Book());
//        return "/bookRegister";
//        //return "redirect:/viewBooks?success";
//    }



    @GetMapping("/bookRegister")
    public String bookRegister(@ModelAttribute("book") Book book) {
        return "bookRegister";
    }

    @PostMapping("/bookRegister")
    public String addBooks(@ModelAttribute("book") Book book, Model model) throws BookException {
        bookService.addBook(book);
        model.addAttribute("message", "Registered book Successfully!");
        return "bookRegister";
    }




//    @GetMapping("/viewCart")
//    public String viewCart(){
//        return "viewCart";
//    }
    
//    @GetMapping("/viewBooks")
//    public String viewBooks(){
//        return "viewBooks";
//    }
//
//


//    @GetMapping("/home")
//    public String home(Model model, Principal principal) {
////        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
////        model.addAttribute("userDetails" , userDetails);
////        return "home";
//
//        if (principal != null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
//            model.addAttribute("userDetails", userDetails);
//            return "home";
//        } else {
//            // Handle the case where principal is null (e.g., not authenticated)
//            return "redirect:/login";
//        }
//    }



    @GetMapping("user-page")
    public String userPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "user";
    }

    @GetMapping("admin-page")
    public String adminPage (Model model, Principal principal) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
        model.addAttribute("user", userDetails);
        return "admin";
    }

}
