package com.landvibe.chinstagram.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private String pw;

    @Column
    private String token;

//    @OneToOne(mappedBy = "user")
//    private Profile profile;

    @Builder
    public User(String id, String pw, String token) {
        this.id = id;
        this.pw = pw;
        this.token = token;
    }
}