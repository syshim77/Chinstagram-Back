package com.landvibe.chinstagram.models;

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

    @OneToOne(mappedBy = "profile")
    private Profile profile;
}