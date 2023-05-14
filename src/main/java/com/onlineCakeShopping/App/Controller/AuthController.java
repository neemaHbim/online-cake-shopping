package com.onlineCakeShopping.App.Controller;

import com.onlineCakeShopping.App.Repositories.CakeRepository;
import com.onlineCakeShopping.App.Repositories.OrdersRepository;
import com.onlineCakeShopping.App.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AuthController {
    @Autowired
    CakeRepository repo;
    @Autowired
    UserRepository user_repo;
    @Autowired
    OrdersRepository orders_repo;

    @RequestMapping("/dashboard")
    public String showDashboard(Model model){
        model.addAttribute("cakes", repo.findAll());
        model.addAttribute("clients", user_repo.findAll());
        model.addAttribute("ordersList2", orders_repo.findAll());
        return"./dashboard/index";

    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }
}
