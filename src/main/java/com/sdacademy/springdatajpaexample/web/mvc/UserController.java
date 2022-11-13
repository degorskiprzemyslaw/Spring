package com.sdacademy.springdatajpaexample.web.mvc;


import com.sdacademy.springdatajpaexample.model.User;
import com.sdacademy.springdatajpaexample.service.UserService;
import com.sdacademy.springdatajpaexample.web.mvc.form.CreateUserForm;
import com.sdacademy.springdatajpaexample.web.mvc.mappers.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/mvc/user")
public class UserController {

    private static final String MESSAGE_KEY = "message";

    private final UserService userService;

    @GetMapping
    public String create(ModelMap map){
        map.addAttribute("user", new CreateUserForm());
        return "create-user";
    }

    @PostMapping
    public String handleCreate(@ModelAttribute("user") @Valid CreateUserForm form, Errors errors, final RedirectAttributes redirectAttributes){
        log.info("Creating user from form: {}", form);
        if(errors.hasErrors()){
            return "create-user";
        }
        userService.save(UserMapper.toEntity(form));
        redirectAttributes.addAttribute(MESSAGE_KEY, "Użytkownik o loginie: " + form.getLogin() + " został pomyślnie dodany!");

        return "redirect:/mvc/user/list";
    }

    @GetMapping("/list")
    public String list(ModelMap map, @ModelAttribute(MESSAGE_KEY) String message){
        map.addAttribute("users", userService.findAll());
        if(!message.isBlank()){
            map.addAttribute(MESSAGE_KEY, message);
        }
        return "user-list";
    }


    @GetMapping("/update/{id}")
    public String update(ModelMap map, @PathVariable("id") Long id){
        //User user = userService.update(userService.findById(id));

        map.addAttribute("user", userService.findById(id));

        return "update-user";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("user") @Valid User user, Errors errors, final ModelMap map){
        userService.update(user);
        return "redirect:/mvc/user/list";

    }

    @GetMapping("/delete/{id}")
    public String delete( @PathVariable("id") Long id){
        userService.delete(id);;

        return "redirect:/mvc/user/list";
    }



    /*@PutMapping("/update/{id}")
    public String updatePage(@ModelAttribute("user") @Valid CreateUserForm form, Errors errors, ModelMap map, @PathVariable String id){
        userService.update(UserMapper.toEntity(form));
        map.addAttribute("user", form);
        return "redirect:/mvc/user/list";
    }*/
}
