package com.landvibe.chinstagram.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpResponse {
    private String id;
    private String email;
    private String name;

    @Builder
    public SignUpResponse(String id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }
}
