package com.onlineCakeShopping.App.Controller;

import com.onlineCakeShopping.App.Domain.Orders;
import com.onlineCakeShopping.App.Repositories.CakeRepository;
import com.onlineCakeShopping.App.Domain.Cake;
import com.onlineCakeShopping.App.Domain.User;
import com.onlineCakeShopping.App.Repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/cake/")
public class CakeController {

    String someValue="Hello";


    private final CakeRepository repo;


    @Autowired
    public CakeController(CakeRepository repo) {
        this.repo = repo;
    }

    @GetMapping("add")
    public String showRegistrationForm(Cake cake) {
        return "./dashboard/add-cake";
    }

    @GetMapping("dashboard")
    public String showDashboard(Model model) {
        model.addAttribute("cakes", repo.findAll());
        return "../dashboard/index";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("cakes", repo.findAll());
        return "./dashboard/cakes";
    }

    @PostMapping("registration")
    public String addCake(@Valid Cake cake, BindingResult result, Model model, RedirectAttributes redirectAttributes, Principal principal, @AuthenticationPrincipal User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try {
            if (result.hasErrors()) {
                return "add-cake";
            }


            repo.save(cake);
            redirectAttributes.addFlashAttribute("message", "Cake saved successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../dashboard";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Cake cake = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Cake Id:" + id));
        model.addAttribute("cake", cake);
        return "./dashboard/edit-cake";
    }

    @PostMapping("update/{id}")
    public String updateCake(@PathVariable("id") Integer id, @Valid Cake cake, BindingResult result,
                                 Model model, RedirectAttributes redirectAttributes, Principal principal) {

        try {
            if (result.hasErrors()) {
                cake.setId(id);
                return "./dashboard/edit-cake";
            }
            repo.save(cake);
            model.addAttribute("cakes", repo.findAll());
            redirectAttributes.addFlashAttribute("message", "Cake updated successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../../dashboard";
    }

    @GetMapping("delete/{id}")
    public String deleteCake(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

        try {
            Cake cake = repo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Cake Id:" + id));
            repo.delete(cake);
            model.addAttribute("cakes", repo.findAll());
            redirectAttributes.addFlashAttribute("message", "Cake deleted successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../../dashboard";
    }

}
