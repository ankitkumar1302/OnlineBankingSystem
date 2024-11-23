package com.example.onlinebanking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginController {

    // Dummy data for testing login
    private static final Map<String, String> dummyUsers = new HashMap<>();

    static {
        // Adding some dummy users
        dummyUsers.put("testuser@gmail.com", "password123");
        dummyUsers.put("admin", "adminpass");
    }

    // Display login page
    @GetMapping
    public String showLoginForm(Model model) {
        return "login"; // Renders login.html
    }

    // Handle login form submission
    @PostMapping
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               Model model) {
        // Validate the username and password against dummy data
        if (dummyUsers.containsKey(username) && dummyUsers.get(username).equals(password)) {
            model.addAttribute("message", "Login successful!");
            model.addAttribute("username", username);
            return "welcome"; // Render a success page, e.g., welcome.html
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login"; // Re-render the login page with an error
        }
    }
}
