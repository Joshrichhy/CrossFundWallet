package com.example.crossfundwallet.Data.Repositories;

import com.example.crossfundwallet.Data.Models.Card;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {
}
