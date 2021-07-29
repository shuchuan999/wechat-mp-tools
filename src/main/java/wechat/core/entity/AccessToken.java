package wechat.core.entity;

import java.sql.Timestamp;


public class AccessToken {
    private String accessToken;
    private Timestamp expireTime;

    public AccessToken(String accessToken, Timestamp expireTime) {
        this.accessToken = accessToken;
        this.expireTime = expireTime;
    }

    public AccessToken() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }
}
