package com.example.async.controllers;

import com.example.async.models.User;
import com.example.async.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import java.util.concurrent.CompletableFuture;

@RequestMapping("/user")
@RestController
@Async
public class UserController {
   @Autowired
    private UserService userService;

    @GetMapping
    public CompletableFuture<List<User>> findAll(){
        return userService.findAll();
    }
    @GetMapping("/{id}")
    public CompletableFuture<User> findById (@PathVariable("id") Long id){
       return this.userService.findById(id).thenApply(user -> { try {
            return user.get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       });

    }

    @PostMapping
    public CompletableFuture<User> save (@RequestBody User user){
        return this.userService.save(user);
    }
}
