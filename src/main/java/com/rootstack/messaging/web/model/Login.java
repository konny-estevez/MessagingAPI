package com.rootstack.messaging.web.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class Login {
    @NotBlank
    @Email
    private String Email;
    @NotBlank
    private String Password;

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
