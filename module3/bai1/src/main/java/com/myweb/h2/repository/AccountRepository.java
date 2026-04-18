package com.myweb.h2.repository;

import com.myweb.h2.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findById(Integer accountId);

    @Query("SELECT a FROM Account a WHERE gender = ?1 ORDER BY id DESC")
    List<Account> findByGender(String gender);
}
