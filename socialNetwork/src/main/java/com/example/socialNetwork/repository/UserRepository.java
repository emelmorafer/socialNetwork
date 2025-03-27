package com.example.socialNetwork.repository;

import com.example.socialNetwork.model.Useer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Useer, Long> {

    public Optional<Useer> getUserByUsername(String username);

}
