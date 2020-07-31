package com.Betts.userdatabase.dao;

import com.Betts.userdatabase.Models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    Optional<User> findByPublicID(UUID publicID);
    Optional<User> findByEmailAddress(String emailAddress);
}
