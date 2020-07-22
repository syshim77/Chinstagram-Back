package com.landvibe.chinstagram.models;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LogInRequest {
    private String id;
    private String pw;

    @Builder
    public LogInRequest(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

}