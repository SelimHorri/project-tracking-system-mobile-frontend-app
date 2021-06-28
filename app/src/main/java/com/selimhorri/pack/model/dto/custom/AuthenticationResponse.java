package com.selimhorri.pack.model.dto.custom;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable {

    private String username;
    private Boolean isEligible;

    public AuthenticationResponse() {

    }

    public AuthenticationResponse(String username, Boolean isEligible) {
        this.username = username;
        this.isEligible = isEligible;
    }

    @Override
    public String toString() {
        return "AuthenticationResponse{" +
                "username='" + username + '\'' +
                ", isEligible=" + isEligible +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public AuthenticationResponse setUsername(String username) {
        this.username = username;
        return this;
    }

    public Boolean getEligible() {
        return isEligible;
    }

    public AuthenticationResponse setEligible(Boolean eligible) {
        isEligible = eligible;
        return this;
    }
}
