package com.veripark.services;

public class HandShakeObject {
    private String aesKey;
    private String aesIV;
    private String authorization;
    private String lifeTime;
    private ResponseStatus status;

    public String getAesKey() {
        return aesKey;
    }

    public void setAeskey(String aeskey) {
        this.aesKey = aeskey;
    }

    public String getAesIV() {
        return aesIV;
    }

    public void setAesIV(String aesIV) {
        this.aesIV = aesIV;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(String lifeTime) {
        this.lifeTime = lifeTime;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
}

