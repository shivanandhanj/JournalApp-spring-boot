package com.shiva.Journal.repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.shiva.Journal.model.User;
public interface UserRepository  extends MongoRepository<User, ObjectId>{
    public User findByName(String name);
}
