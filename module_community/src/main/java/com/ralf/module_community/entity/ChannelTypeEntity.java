package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * 频道分类实体类
 * @name ChannelTypeEntity
 * @email -
 * @date 2019/04/23 下午12:06
 **/
public class ChannelTypeEntity {

    /**
     * channelId : 1
     * channelTitle : 美宠秀秀
     * channelUrl : http://192.168.1.81:8090/upload/image/userPic/dff115b6d39d40c8b62b145752eae812.jpg
     * newNumber : 0
     */

    private int channelId;
    private String channelTitle;
    private String channelUrl;
    private int newNumber;

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(String channelTitle) {
        this.channelTitle = channelTitle;
    }

    public String getChannelUrl() {
        return channelUrl;
    }

    public void setChannelUrl(String channelUrl) {
        this.channelUrl = channelUrl;
    }

    public int getNewNumber() {
        return newNumber;
    }

    public void setNewNumber(int newNumber) {
        this.newNumber = newNumber;
    }

    @Override
    public String toString() {
        return "ChannelDesBean{" +
                "channelId=" + channelId +
                ", channelTitle='" + channelTitle + '\'' +
                ", channelUrl='" + channelUrl + '\'' +
                ", newNumber=" + newNumber +
                '}';
    }

}
