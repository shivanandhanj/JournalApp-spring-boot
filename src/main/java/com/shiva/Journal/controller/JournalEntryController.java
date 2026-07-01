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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.Journal.model.Journalmodel;
import com.shiva.Journal.model.User;
import com.shiva.Journal.service.JournalEntry;
import com.shiva.Journal.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
    private JournalEntry journalEntry;
    @Autowired
    private UserService userService;

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
    @GetMapping("/id/{id}")
    public ResponseEntity<Journalmodel> getById(@PathVariable ObjectId id){
        return journalEntry.getJournalEntryById(id);
    }
    @GetMapping("/user/{username}")
    public ResponseEntity<List<Journalmodel>> getAllEntryByuser(@PathVariable String username){
        User user=userService.findByName(username);
        if(user==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user.getJournalEntries());
    }
    
    @PutMapping("/{id}")
    public void updateEntry(@RequestBody Journalmodel jm, @PathVariable ObjectId id){
        journalEntry.updateJournalEntry(jm,id);
    }
    @DeleteMapping("/{username}/{id}")
    public void deleteEntry(@PathVariable ObjectId id, @PathVariable String username){
        journalEntry.deleteJournalEntryById(id,username);    
    }


    
    

}
