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

import java.util.Optional;
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
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
       return this.userService.findById(id).thenApply(user -> { try {
            return user.get();
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
       });

    }
    @PostMapping
    public CompletableFuture<User> save (@RequestBody User user){
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
        return this.userService.save(user);
    }
    @PatchMapping
    public CompletableFuture<User> update (@RequestBody User user){
        return userService.update(user);
    }
    @DeleteMapping("/{id}")
    public CompletableFuture<Optional<User>> delete(@PathVariable ("id") Long id) {
        System.out.println("Controller Thread: " + Thread.currentThread().getName());
        if (this.userService.findById(id).join().isPresent()){
            return this.userService.delete(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
