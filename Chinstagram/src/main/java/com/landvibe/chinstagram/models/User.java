package com.landvibe.chinstagram.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
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

    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

}