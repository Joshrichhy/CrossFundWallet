package com.example.crossfundwallet.Data.Repositories;

import com.example.crossfundwallet.Data.Models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailAddress(String emailAddress);


    User findByAccountNumber(String accountNumber);

}
