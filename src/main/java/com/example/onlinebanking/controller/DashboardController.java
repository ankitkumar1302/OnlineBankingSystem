package com.example.onlinebanking.controller;

import com.example.onlinebanking.model.Transaction;
import com.example.onlinebanking.model.User;
import com.example.onlinebanking.service.UserService;
import com.example.onlinebanking.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    private final UserService userService;
    private final TransactionService transactionService;

    @Autowired
    public DashboardController(UserService userService, TransactionService transactionService) {
        this.userService = userService;
        this.transactionService = transactionService;
    }

    @GetMapping
    public String showDashboard(Model model, Principal principal) {
        if (principal == null) {
            // If principal is null, user is not authenticated, show a message or redirect
            model.addAttribute("error", "User not authenticated.");
            return "error"; // You may have an error.html to handle this
        }

        // Get the logged-in user's username
        String username = principal.getName();

        // Fetch user details
        User user = userService.findByUsername(username);

        // Fetch recent transactions for the user
        List<Transaction> transactions = transactionService.findRecentTransactionsByUser(user.getId());

        // Add user and transactions to the model
        model.addAttribute("user", user);
        model.addAttribute("transactions", transactions);

        return "dashboard"; // This will render the dashboard.html page
    }
}
