package com.shiva.Journal.controller;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.Journal.model.Journalmodel;
import com.shiva.Journal.service.JournalEntry;

@RestController

public class JournalEntryController {
    @Autowired
    private JournalEntry journalEntry;

    @PostMapping("/{username}")
    public ResponseEntity<Journalmodel> addJournalEntry(@RequestBody Journalmodel jm,@PathVariable String username){

        try{
           journalEntry.saveJournalEntry(jm,username);
            return new ResponseEntity<>(jm,HttpStatus.CREATED);
        }catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public List<Journalmodel> getAllEntry(){
        return journalEntry.getAllJournalEntries();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Journalmodel> getById(@PathVariable ObjectId id){
        return journalEntry.getJournalEntryById(id);
    }
    @PutMapping("/{id}")
    public void updateEntry(@RequestBody Journalmodel jm, @PathVariable ObjectId id){
        journalEntry.updateJournalEntry(jm,id);
    }
    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable ObjectId id){
        journalEntry.deleteJournalEntryById(id);    
    }


    
    

}
