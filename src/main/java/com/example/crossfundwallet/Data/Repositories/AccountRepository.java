package com.example.crossfundwallet.Data.Repositories;


import com.example.crossfundwallet.Data.Models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
}
