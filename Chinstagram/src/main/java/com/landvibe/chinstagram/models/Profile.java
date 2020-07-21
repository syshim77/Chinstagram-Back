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

    @Column
    private Image image;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

}
