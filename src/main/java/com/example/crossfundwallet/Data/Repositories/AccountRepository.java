package com.example.crossfundwallet.Data.Repositories;


import com.example.crossfundwallet.Data.Models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByAccountNumber(String accountNumber);
}
