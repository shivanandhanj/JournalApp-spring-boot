package com.shiva.Journal.service;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.shiva.Journal.model.Journalmodel;
import com.shiva.Journal.model.User;
import com.shiva.Journal.repository.JournalEntryRepo;
@Service
public class JournalEntry {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    public void saveJournalEntry(Journalmodel journal,String username){
        User user=userService.findByName(username);
        journal.setCreatedAt(java.util.Date.from(java.time.ZonedDateTime.now().toInstant()));
        Journalmodel jm=journalEntryRepo.save(journal);
        user.getJournalEntries().add(jm);
        userService.saveuser(user);

        

    }
    


    public List<Journalmodel> getAllJournalEntries(){
        return journalEntryRepo.findAll();
    }
    // public Journalmodel getJournalEntryById(ObjectId id){
    //     return journalEntryRepo.findById(id).orElse(null);
    // }
    public ResponseEntity<Journalmodel> getJournalEntryById(ObjectId id){
        Optional<Journalmodel> journalmodel=journalEntryRepo.findById(id);

        if(journalmodel.isPresent()) return new ResponseEntity<>(journalmodel.get(),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    public void deleteJournalEntryById(ObjectId id){
        journalEntryRepo.deleteById(id);

    }
    public void updateJournalEntry(Journalmodel journal,ObjectId id){
        Journalmodel old=journalEntryRepo.findById(id).orElse(null);
        if(old!=null){
            old.setTitle(journal.getTitle()!=null && !journal.getTitle().equals("")?journal.getTitle():old.getTitle());
            old.setContent(journal.getContent()!=null && !journal.getContent().equals("")?journal.getContent():old.getContent());
            journalEntryRepo.save(old);
        }

    }





}
