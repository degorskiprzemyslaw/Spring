package com.sdacademy.springdatajpaexample.web.mvc;

import com.sdacademy.springdatajpaexample.model.User;
import com.sdacademy.springdatajpaexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc")
public class WelcomeController {
    private final UserService userService;

    @GetMapping
    public String welcomePage(ModelMap map){
        User user = userService.findById(2L);
        map.addAttribute("user", user);
        return "index";
    }
}
