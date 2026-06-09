package com.myweb.account_mysql_service.repository;

import com.myweb.account_mysql_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    Optional<User> findByUserId(String userId);

    @Query(value = "SELECT * FROM user WHERE user_id = :userId", nativeQuery = true)
    Optional<User> findByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM user WHERE username = :username", nativeQuery = true)
    Optional<User> findByUsername(String username);

    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
