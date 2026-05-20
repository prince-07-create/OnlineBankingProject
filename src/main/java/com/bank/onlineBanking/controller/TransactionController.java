package com.bank.onlineBanking.controller;

import com.bank.onlineBanking.model.User;
import com.bank.onlineBanking.service.TransactionService;
import com.bank.onlineBanking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @GetMapping("/transfer")
    public String transferForm(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName()).orElse(null);
        model.addAttribute("user", user);
        return "transfer";
    }

    @PostMapping("/transfer")
    public String transferSubmit(@RequestParam("receiverUsername") String receiverUsername,
                                 @RequestParam("amount") BigDecimal amount,
                                 Principal principal,
                                 RedirectAttributes redirectAttributes) {
        try {
            transactionService.transferMoney(principal.getName(), receiverUsername, amount);
            redirectAttributes.addFlashAttribute("successMessage", "Transfer successful!");
            return "redirect:/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/transfer";
        }
    }

    @GetMapping("/history")
    public String history(Principal principal, Model model) {
        User user = userService.findByUsername(principal.getName()).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            model.addAttribute("transactions", transactionService.getTransactionHistory(user.getId()));
        }
        return "history";
    }
}
