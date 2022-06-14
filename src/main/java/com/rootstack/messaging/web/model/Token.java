package com.rootstack.messaging.web.model;

import java.util.Date;

public class Token {
    private String Value;
    private String Type;
    private Date ExpiresOn;

    public Token(String tokenValue, String tokenType, Date expiration) {
        Value = tokenValue;
        Type = tokenType;
        ExpiresOn = expiration;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Date getExpiresOn() {
        return ExpiresOn;
    }

    public void setExpiresOn(Date expiresOn) {
        ExpiresOn = expiresOn;
    }
}
