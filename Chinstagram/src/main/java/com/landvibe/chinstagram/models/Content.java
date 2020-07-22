package com.landvibe.chinstagram.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Content {

    @Id
    private int id;

    @Column
    private String script;

    @OneToMany
    @JoinColumn(name = "content_id")
    private List<Image> images;

    @Column
    private LocalDateTime createTime;

    @Column
    private LocalDateTime updateTime;

    @PrePersist
    protected void onCreate() {
        createTime = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updateTime = LocalDateTime.now();
    }

    @Builder
    public Content(int id, String script, List<Image> images, LocalDateTime createTime, LocalDateTime updateTime) {
        this.id = id;
        this.script = script;
        this.images = images;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

}
