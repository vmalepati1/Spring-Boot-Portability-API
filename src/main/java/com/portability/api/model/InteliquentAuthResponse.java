package com.portability.api.model;

import java.io.Serializable;

public class InteliquentAuthResponse implements Serializable {

    private String scope;
    private String tokenType;
    private long expiresIn;
    private String accessToken;

    public String getScope() {
        return scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

}
