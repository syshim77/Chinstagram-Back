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

    @OneToOne
    @JoinColumn(name = "image_id")
    private Image image;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

    @OneToOne(mappedBy = "profile")
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
