package com.everyday.repository;

import com.everyday.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUserId(String userId);

    List<User> findByUserIdContaining(String userId);
}
