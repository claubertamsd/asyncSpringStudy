package com.example.async.services;

import com.example.async.models.User;
import com.example.async.repositorys.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public CompletableFuture<Optional<User>> findById (Long id){
        return userRepository.findOneById(id);
    }

    public CompletableFuture<List<User>> findAll(){
        return userRepository.findAllBy();
    }

    public CompletableFuture<User> save(User user){
        return CompletableFuture.completedFuture(userRepository.save(user));
    }

    public CompletableFuture<User> update (User user){
        return CompletableFuture.completedFuture(userRepository.save(user));
    }
    public CompletableFuture<Optional<User>> delete (Long id){
         userRepository.deleteById(id);
         return null;
    }
}
