package com.shiva.Journal.repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.shiva.Journal.model.Journalmodel;
public interface JournalEntryRepo extends MongoRepository<Journalmodel, ObjectId> {

}
