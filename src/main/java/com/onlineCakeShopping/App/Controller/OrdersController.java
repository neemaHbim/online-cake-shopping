package com.onlineCakeShopping.App.Controller;

import com.onlineCakeShopping.App.Domain.Cake;
import com.onlineCakeShopping.App.Domain.Orders;
import com.onlineCakeShopping.App.Domain.User;
import com.onlineCakeShopping.App.Repositories.CakeRepository;
import com.onlineCakeShopping.App.Repositories.OrdersRepository;
import com.onlineCakeShopping.App.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/orders/")
public class OrdersController {
    private Cake selectedCake;
    private User loggedInUser;

    private final OrdersRepository repo;
    private final CakeRepository cake_repo;
    private final UserRepository user_repo;

    @Autowired
    public OrdersController(OrdersRepository repo, CakeRepository cake_repo, UserRepository user_repo) {
        this.repo = repo;
        this.cake_repo = cake_repo;
        this.user_repo = user_repo;
    }

    @GetMapping("dashboard")
    public String showDashboard() {
        return "dashboard/index";
    }


    @GetMapping("add")
    public String showRegistrationForm(Orders orders) {
        return "./dashboard/add-order";
    }

    @GetMapping("list")
    public String showUpdateForm(Model model) {
        model.addAttribute("ordersList", repo.findAll());
        return "./dashboard/ordersList";
    }


    @GetMapping("cake/{id}")
    public String showOrderForm(@PathVariable("id") Integer id, @Valid Orders order, Principal principal) {
        Cake cake = cake_repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Cake Id:" + id));
        User user = user_repo.findByEmail(principal.getName())
                .orElseThrow(() -> new IllegalArgumentException("Invalid Cake Id:" + id));
        selectedCake=cake;
        loggedInUser=user;
        return "./dashboard/make-order";
    }


    @PostMapping("register")
    public String orderCake( @Valid Orders order, BindingResult result,
                             Model model, RedirectAttributes redirectAttributes, Principal principal) {

        try {
            if (result.hasErrors()) {
                return "./dashboard/make-order";
            }
            order.setCake(selectedCake);
            order.setTotalPrice(order.getCake().getPrice()*order.getQuantity());
            order.setClient(loggedInUser);
            repo.save(order);
            System.out.println("This is a logged in user "+principal.getName());
            model.addAttribute("orders", repo.findAll());
            redirectAttributes.addFlashAttribute("message", "Order successfully created!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../dashboard";
    }


    @PostMapping("reg")
    public String addOrder(@Valid Orders orders, BindingResult result, Model model, RedirectAttributes redirectAttributes, Principal principal, @AuthenticationPrincipal User user) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try {
            if (result.hasErrors()) {
                return "add-order";
            }

//incident.setRegisteredBy(principal.getName());

            repo.save(orders);
            redirectAttributes.addFlashAttribute("message", "Order saved successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:list";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Orders orders = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Order Id:" + id));
        model.addAttribute("orders", orders);
        return "./dashboard/edit-order";
    }

    @PostMapping("update/{id}")
    public String updateOrder(@PathVariable("id") Integer id, @Valid Orders orders, BindingResult result,
                                 Model model, RedirectAttributes redirectAttributes, Principal principal) {

        try {
            if (result.hasErrors()) {
                orders.setId(id);
                return "./dashboard/edit-order";
            }
//incident.setRegisteredBy(principal.getName());
            repo.save(orders);
            model.addAttribute("ordersList", repo.findAll());
            redirectAttributes.addFlashAttribute("message", "Order updated successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../list";
    }

    @GetMapping("delete/{id}")
    public String deleteOrder(@PathVariable("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

        try {
            Orders orders = repo.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Order Id:" + id));
            repo.delete(orders);
            model.addAttribute("ordersList", repo.findAll());
            redirectAttributes.addFlashAttribute("message", "Order deleted successfully!");

        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:../list";
    }

}
