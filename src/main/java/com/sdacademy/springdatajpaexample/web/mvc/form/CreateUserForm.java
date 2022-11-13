package com.sdacademy.springdatajpaexample.web.mvc.form;

import lombok.*;


import javax.validation.constraints.*;


//jest to klasa POJO
//te pola będą zmapowane z klasą User

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserForm {
    private String firstName;
    private String lastName;
    @NotBlank(message="Pole nie moze być puste")
    @Email(message = "Login powinien być poprawnym formatem adresu email")
    private String login;
    @Size(min = 8, message = "Minimalna liczba znaków: 8")
    @NotBlank(message="Pole nie moze być puste")
    private String password;
}
