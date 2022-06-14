package com.rootstack.messaging.web.model;

import com.rootstack.messaging.persistence.entity.UserEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class NewUser {
    @NotBlank
    private String Name;
    @NotBlank
    @javax.validation.constraints.Email
    private String Email;
    @NotBlank
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$")
    private String Password;

    public UserEntity fromModel() {
        return new UserEntity(this.getName(), this.getEmail(), this.getPassword());
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
