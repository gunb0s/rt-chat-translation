package com.gunb0s.rt_chat_translation.user.repository;

import com.gunb0s.rt_chat_translation.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByGithubId(Long githubId);
}
