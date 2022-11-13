package com.sdacademy.springdatajpaexample.repository;

import com.sdacademy.springdatajpaexample.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByLastName(String lastName);

    List<User> findAllByFirstNameAndLastName(String firstName, String lastName);

    Optional<User> findByLogin(String login);

    List<User> findAllByFirstNameLike(String likePattern);

    List<User> findTop2ByLastNameOrderByFirstNameDesc(String lastName);

    @Query(value = "SELECT u FROM User u WHERE u.password = :password")
    List<User> findByPassword(@Param("password") String password);

    @Query(value = "SELECT * FROM User WHERE login = :login", nativeQuery = true)
    Optional<User> findByLoginNative(@Param("login") String login);

}
