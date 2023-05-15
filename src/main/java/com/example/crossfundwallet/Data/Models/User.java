package com.example.crossfundwallet.Data.Models;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String EmailAddress;
    private LocalDate DOB;
    private LocalDate DateOfRegistration;
    private List<Account> account = new ArrayList<>();
    private int age;
    private String password;
    private List<Card> cards = new ArrayList<>();


}
