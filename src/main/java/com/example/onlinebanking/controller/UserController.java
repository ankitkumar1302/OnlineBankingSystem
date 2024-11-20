package com.example.onlinebanking.controller;

import com.example.onlinebanking.model.User;
import com.example.onlinebanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;

        // Use for password
    }

    // Display registration page
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle registration form submission
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        user.setRole("ROLE_USER"); // Assign default role
        userService.saveUser(user); // Save user to the database
        return "redirect:/user/login?registered"; // Redirect to login page after registration
    }

    // Display login page
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        return "login"; // Renders login.html
    }

    // Display user profile
    @GetMapping("/profile")
    public String showUserProfile(Model model, Principal principal) {
        // Get the logged-in user's username
        String username = principal.getName();
        // Fetch user details
        User user = userService.findByUsername(username);
        // Add user details to the model
        model.addAttribute("user", user);
        return "profile"; // Renders profile.html
    }
}
