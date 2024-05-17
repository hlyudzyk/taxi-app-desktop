package com.taxiapp.desktop.security;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccessTokenManager {
    private static AccessTokenManager instance;
    private String accessToken;

    private AccessTokenManager() {

    }
    public static AccessTokenManager getInstance() {
        if (instance == null) {
            instance = new AccessTokenManager();
        }
        return instance;
    }

}
