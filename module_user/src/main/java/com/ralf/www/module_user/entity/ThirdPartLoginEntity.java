package com.ralf.www.module_user.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ThirdPartLoginEntity
 * @email -
 * @date 2018/12/13 下午4:48
 **/
public class ThirdPartLoginEntity {

    public String tokenInfo;
    public String accessToken;
    public int platformType;
    public String openid;

    public ThirdPartLoginEntity(String accessToken, int platformType, String openid) {
        this.accessToken = accessToken;
        this.platformType = platformType;
        this.openid = openid;
    }

    public String getTokenInfo() {
        return tokenInfo;
    }

    public void setTokenInfo(String tokenInfo) {
        this.tokenInfo = tokenInfo;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getPlatformType() {
        return platformType;
    }

    public void setPlatformType(int platformType) {
        this.platformType = platformType;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

}
