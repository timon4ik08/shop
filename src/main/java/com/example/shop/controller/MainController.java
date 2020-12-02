package com.example.shop.controller;

import com.example.shop.domain.Message;
import com.example.shop.domain.User;
import com.example.shop.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private MessageRepo messageRepo;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model){
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @GetMapping("/next")
    public String next(Map<String, Object> model){
        model.put("tim", "NEXT");
        return "next";
    }

    @PostMapping("add")
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam String tag,
                      Map<String, Object> model){
        Message message = new Message(text, tag, user);
        messageRepo.save(message);
        Iterable<Message> messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";

    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter,
                         Map<String, Object> model){
        Iterable<Message> messages;
        if(filter != null && !filter.isEmpty())
            messages = messageRepo.findAll();
        else
            messages = messageRepo.findAll();
        model.put("messages", messages);
        return "main";
    }

    @PostMapping("filterId")
    public String filterId(@RequestParam String filterId,
                           Map<String, Object> map){
        Iterable<Message> messages;
        if(filterId != null && !filterId.isEmpty()) {
            messages = messageRepo.findById(Integer.parseInt(filterId));
            messageRepo.deleteById(Integer.parseInt(filterId));
        }
        else
            messages = messageRepo.findAll();
        map.put("messages", messages);
        return "main";
    }

    @GetMapping("/")
    public String index(Map<String, Object> map){

        return "index";
    }

}