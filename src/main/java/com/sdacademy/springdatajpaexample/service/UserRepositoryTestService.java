package com.sdacademy.springdatajpaexample.service;

import com.sdacademy.springdatajpaexample.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserRepositoryTestService implements CommandLineRunner {
    private final UserRepository repository;

    @Override
    public void run(String... args) throws Exception {

        //create/persist
//        repository.save(new User("Jan", "Kowalski", "kowalskij", "test1"));
//        repository.save(new User("Maja", "Nowak", "nowakm", "test2"));
//        repository.save(new User("Eryk", "Zaremba", "zarembae", "test3"));
//        repository.save(new User("Brygida", "Banach", "banachb", "test4"));
//        repository.save(new User("Mateusz", "Słabeusz", "słabeuszm", "test5"));
//        repository.save(new User("Janusz", "Zaremba", "zarembaj", "test3"));
//        repository.save(new User("Maria", "Zaremba", "zarembam", "test3"));


        log.info("List of users: ");
        printAllUsers();

        //select
        log.info("Finding user by id=1");
        repository.findById(1L).ifPresent(user -> log.info(user.toString()));

        //delete
//        log.info("Deleting user by id");
//        repository.deleteById(1L);
//        printAllUsers();

        repository.findById(2L).ifPresent(user -> { //można tu też używać klamer - klas anonimowych, jeśli musimy zrobić więcej niż jedną rzecz
            user.setFirstName("Agnieszka");
            repository.save(user);
        });


        log.info("Find all users by lastName: ");
        repository.findAllByLastName("Zaremba").forEach(user -> log.info(user.toString()));

        log.info("Find all users by firstName and lastName: ");
        repository.findAllByFirstNameAndLastName("Brygida","Banach").forEach(user -> log.info(user.toString()));

        log.info("Find by login");
        repository.findByLogin("nowakm").ifPresent(user -> log.info(user.toString()));
        log.info("Find by login not existing");
        repository.findByLogin("nowakmjklkj").ifPresent(user -> log.info(user.toString()));

        log.info("Find by firstName like");
        repository.findAllByFirstNameLike("____").forEach(user -> log.info(user.toString()));

        log.info("Find top 2 by lastName: ");
        repository.findTop2ByLastNameOrderByFirstNameDesc("Zaremba").forEach(user -> log.info(user.toString()));

        log.info("Find by password");
        repository.findByPassword("test2").forEach(user -> log.info(user.toString()));

        log.info("Find by login using native SQL");
        repository.findByLoginNative("słabeuszm").ifPresent(user -> log.info(user.toString()));
    }

    private void printAllUsers() {
        repository.findAll().forEach(user -> log.info(user.toString()));
    }
}
