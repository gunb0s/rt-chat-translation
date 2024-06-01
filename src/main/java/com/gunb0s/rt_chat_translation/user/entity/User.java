package com.gunb0s.rt_chat_translation.user.entity;

import com.gunb0s.rt_chat_translation.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "githubId", name = "uk_user_github_id"),
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, unique = true)
    private Long githubId;

    @Column()
    private String username;

    @Builder
    public User(Long githubId, String username) {
        this.githubId = githubId;
        this.username = username;
    }
}
