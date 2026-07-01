package com.shiva.Journal.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shiva.Journal.model.User;
import com.shiva.Journal.repository.UserRepository;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    public void saveuser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("ROLE_USER"));
        userRepository.save(user);
    }
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(ObjectId id){
        return userRepository.findById(id);

    }
    public void deleteUserById(ObjectId id){
        userRepository.deleteById(id);

    }
    public User findByName(String name){
        return userRepository.findByName(name);
    }


}
