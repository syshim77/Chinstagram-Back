package com.landvibe.chinstagram.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Image {

    @Id
    private String name;

    @Column
    private String path;

//    @OneToOne
//    @JoinColumn(name = "profile_id")
//    private Profile profile;
//
//    @ManyToOne
//    @JoinColumn(name = "content_id")
//    private Content content;
}
