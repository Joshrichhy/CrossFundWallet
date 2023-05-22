package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.User;
import com.example.crossfundwallet.Exceptions.EmailExistException;
import com.example.crossfundwallet.Exceptions.InvalidLoginException;
import com.example.crossfundwallet.Exceptions.PhoneNumberExistException;
import com.example.crossfundwallet.dtos.request.LoginRequest;
import com.example.crossfundwallet.dtos.request.RegisterUserRequest;
import com.example.crossfundwallet.dtos.response.RegisterUserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    RegisterUserResponse register(RegisterUserRequest registerRequest) throws EmailExistException, PhoneNumberExistException;
    String login(LoginRequest loginRequest) throws InvalidLoginException;

    String closeAccount(String accountNumber);

    Optional<User> findById(String id);
    List<User> findAll();
    long count();
    void deleteById(String id);
    void delete(User user);


}
