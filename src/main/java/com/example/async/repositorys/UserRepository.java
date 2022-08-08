package com.example.async.repositorys;

import com.example.async.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Repository
@Async("customThreadPool")

public interface UserRepository extends JpaRepository<User, Long> {
    CompletableFuture<Optional<User>> findOneById(Long id);

    CompletableFuture<List<User>> findAllBy();
}
