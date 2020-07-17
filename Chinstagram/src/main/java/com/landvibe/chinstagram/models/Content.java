package com.landvibe.chinstagram.models;

import java.time.LocalDateTime;

public class Content {

    private int id;
    private String script;

    private String[] image = new String[2];
    private String imageName;
    private String imagePath;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
