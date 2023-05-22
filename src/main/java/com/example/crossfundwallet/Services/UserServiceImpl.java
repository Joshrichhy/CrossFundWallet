package com.example.crossfundwallet.Services;

import com.example.crossfundwallet.Data.Models.User;
import com.example.crossfundwallet.Data.Repositories.UserRepository;
import com.example.crossfundwallet.Exception.EmailExistException;
import com.example.crossfundwallet.Exception.InvalidLoginException;
import com.example.crossfundwallet.Exception.PhoneNumberExistException;
import com.example.crossfundwallet.Mapper;
import com.example.crossfundwallet.dto.request.LoginRequest;
import com.example.crossfundwallet.dto.request.RegisterUserRequest;
import com.example.crossfundwallet.dto.response.RegisterUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RegisterUserResponse registerUserResponse;
    @Override
    public RegisterUserResponse register(RegisterUserRequest registerRequest) throws EmailExistException, PhoneNumberExistException {
        if (userEmailExist(registerRequest.getEmailAddress()))
            throw new EmailExistException(registerRequest.getEmailAddress() + "already exist");
        if (!validatePhoneNumber(registerRequest.getPhoneNumber()))
            throw new PhoneNumberExistException("Invalid phone number" + registerRequest.getPhoneNumber());
        User user = Mapper.mapUserRequest(registerRequest);
        User savedUser = userRepository.save(user);
        registerUserResponse.setFirstName(savedUser.getFirstName());
        registerUserResponse.setLastName(savedUser.getLastName());
        registerUserResponse.setEmailAddress(savedUser.getEmailAddress());
        registerUserResponse.setDOB(String.valueOf(savedUser.getDOB()));
        registerUserResponse.setDateOfRegistration(LocalDate.now());
        return registerUserResponse;
    }
    private boolean userEmailExist(String emailAddress) {
    User foundUser =userRepository.findByEmailAddress(emailAddress);
    return foundUser != null;
    }
    public boolean validatePhoneNumber(String phoneNumber){
        for (int i = 0; i <phoneNumber.length() ; i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public String login(LoginRequest loginRequest) throws InvalidLoginException {
        User user = userRepository.findByEmailAddress(loginRequest.getEmailAddress());
        if(user == null || !user.getPassword().equals(loginRequest.getPassword()) ||
                !user.getEmailAddress().equals(loginRequest.getEmailAddress())){
            throw new InvalidLoginException("invalid email or password");
        }
        else return "login successful";
    }
    @Override
    public String closeAccount(String accountNumber) {
        User user = userRepository.findByAccountNumber(accountNumber);
        userRepository.delete(user);
        return "Account successfully closed";
    }

    @Override
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public void deleteById(String id) {
    userRepository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}