package com.landvibe.chinstagram.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "content")
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id
    private int id;

    @Column
    private String script;

    @Column
    private List<Image> images;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;


}
