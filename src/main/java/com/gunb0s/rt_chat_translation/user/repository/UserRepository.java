package com.gunb0s.rt_chat_translation.user.repository;

import com.gunb0s.rt_chat_translation.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByGithubId(Long githubId);
    Optional<User> findByUsername(String username);

    Optional<User> findByGithubId(Long githubUserId);
}
