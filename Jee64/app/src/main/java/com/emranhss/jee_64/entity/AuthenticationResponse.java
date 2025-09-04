package com.emranhss.jee_64.entity;

public class AuthenticationResponse {

    private String token;
    private String message;

    public AuthenticationResponse() {}


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
