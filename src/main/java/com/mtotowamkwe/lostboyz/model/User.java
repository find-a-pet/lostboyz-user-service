package com.mtotowamkwe.lostboyz.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private String signUpCode;

    public User(@JsonProperty("id") UUID id, @JsonProperty("email") String email) {
        this.id = id;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSignUpCode() {
        return signUpCode;
    }

    public void setSignUpCode(String signUpCode) {
        this.signUpCode = signUpCode;
    }

    @Override
    public String toString() {
        return "User { id = '" + this.id +
                "', name = '" + this.name +
                "', email = '" + this.email +
                "', phoneNumber = '" + this.phoneNumber +
                "', signUpCode = '" + this.signUpCode +
                "'}";
    }
}
