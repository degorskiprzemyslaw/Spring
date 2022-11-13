package com.sdacademy.springdatajpaexample.web.rest;

import com.sdacademy.springdatajpaexample.model.User;
import com.sdacademy.springdatajpaexample.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
* GET /user, request GET http://localhost:8080/user -> pobranie całej listy -> request sie pisze z poziomu przegladarki
* GET /user/{id}, request: GET http://localhost:8080/user/1 -> pobranie konkretnego użytkownika
* GET /user/{id}, request: GET http://localhost:8080/user/search?firstName=Jan&lastName=Kowalski
* POST /user
* PUT /user
* DELETE /user/{id}
* */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;


    @GetMapping //nie dopisujemy /user, bo jest to doklejane z globalnej sciezki na klasie UserRestController
    public List<User> getUsers(){
    return userService.findAll();
    }

    @GetMapping("/{id}") //tu zapisujemy po prostu pod jakim adresemmozemy sie do tego dostad
    public User getById(@PathVariable("id") Long id){
    return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
    userService.delete(id);
    }

    @PutMapping
    public User update(@RequestBody User user){
        return userService.update(user);
    }

    @PatchMapping
    public User updatePassword(@RequestBody User user){
        return userService.update(user);
    }
    @GetMapping("/search")
    public List<User> findByFirstNameAndLastName(@RequestParam(name = "fName") String firstName, @RequestParam String lastName){
        return userService.findByFirstNameAndLastName(firstName, lastName);
    }

}
