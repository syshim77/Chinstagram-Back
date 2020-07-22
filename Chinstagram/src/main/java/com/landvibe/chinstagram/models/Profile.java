package com.landvibe.chinstagram.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Profile {

    @Id
    private String id;

    @Column
    private String nickname;

    @Column
    private String intro;

    @OneToOne(mappedBy = "profile")
    private Image image;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }

}
