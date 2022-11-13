package com.sdacademy.springdatajpaexample.service;

import com.sdacademy.springdatajpaexample.model.User;
import com.sdacademy.springdatajpaexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public List<User> findAll(){
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList()); //poniewaz findAll jest Iterable trzeba to przerobic do Listy
        //trzeba zrobic z tego streama a potem zkolekcjonowac do listy
        //parallel oznacza czy ma to byc wielowatkowa - false to jednowatkowe
    }

    public User findById(Long id){
        return repository.findById(id).orElseThrow( () -> new RuntimeException("User with id " + id + " not found"));
    }

    public User save(User user) {
        return repository.save(user);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public User update(User user){
        User current = findById(user.getId());
        if(user.getFirstName() == null){
            user.setFirstName(current.getFirstName());
        }
        if(user.getLastName() == null){
            user.setLastName(current.getLastName());
        }
        if(user.getLogin() == null){
            user.setLogin(current.getLogin());
        }
        if(user.getPassword() == null){
            user.setPassword(current.getPassword());
        }
        return repository.save(user);
    }

    public List<User> findByFirstNameAndLastName(String firstName, String lastName){
        return repository.findAllByFirstNameAndLastName(firstName, lastName);
    }
}
