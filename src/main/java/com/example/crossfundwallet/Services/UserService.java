package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.User;
import com.example.crossfundwallet.Exception.EmailExistException;
import com.example.crossfundwallet.Exception.InvalidLoginException;
import com.example.crossfundwallet.Exception.PhoneNumberExistException;
import com.example.crossfundwallet.dto.request.LoginRequest;
import com.example.crossfundwallet.dto.request.RegisterUserRequest;
import com.example.crossfundwallet.dto.response.RegisterUserResponse;

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
