package com.example.shop.controller;

import com.example.shop.domain.Product;
import com.example.shop.domain.User;
import com.example.shop.repos.MessageRepo;
import com.example.shop.repos.ShopRepo;
import com.example.shop.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import sun.security.util.SecurityConstants;

import java.util.List;
import java.util.Map;

@Controller
public class ShopController {
    @Autowired
    ShopRepo shopRepo;

    @GetMapping("/shop")
    public String shopTest(@AuthenticationPrincipal User user, String param, Map<String, Object> model){
        model.put("name", user.getUsername());
        Iterable<Product> productIterable = shopRepo.findAll();
        model.put("product", productIterable);
        return "shop";
    }

    @PostMapping("addShop")
    public String addShop(@AuthenticationPrincipal User user,
                          String description,
                          Integer price,
                          String title,
                          Map <String, Object> model){
        Product product = new Product(title, description, price, user);
        shopRepo.save(product);
        model.put("name", user.getUsername());
        return "redirect:/shop";
    }

    @GetMapping("/deleteProduct")
    public String deleteProduct(Integer attribute){
        shopRepo.deleteById(attribute);

        return "redirect:/shop";
    }



}
