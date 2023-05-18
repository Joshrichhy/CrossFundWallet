package com.example.crossfundwallet.Data.Repositories;

import com.example.crossfundwallet.Data.Models.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
