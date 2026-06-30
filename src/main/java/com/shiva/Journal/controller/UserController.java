package com.shiva.Journal.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shiva.Journal.model.User;
import com.shiva.Journal.service.UserService;

@RestController
@RequestMapping("/user")

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping
    public void saveUser(@RequestBody User user){
        userService.saveuser(user);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByName(@PathVariable String username){
        User user=userService.findByName(username);
        if(user!=null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }
   @PutMapping("/{username}")
   public ResponseEntity<?> updateUser (@RequestBody User user, @PathVariable String username){
    User userInDb=userService.findByName(username);
    if(userInDb!=null){
        userInDb.setPassword(user.getPassword()!=null && !user.getPassword().isBlank()?user.getPassword():userInDb.getPassword());
        userService.saveuser(userInDb);
        return  ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
   }
    

}
