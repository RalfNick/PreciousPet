package com.ralf.module_community.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelPostEntity
 * @email -
 * @date 2019/04/30 11:01
 **/
public class ChannelPostEntity {

    /**
     * imgUrl : ["http://192.168.1.139:8090/upload/image/ChannelPic/adf1477ba27f42908052b2872a24a886.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/87cbe45d87264c10ac03dcc941dae98e.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/40f7f4a9e80b4d9395d3509d2476a249.jpg"]
     * themeList : [{"themeId":1,"themeName":"#8月封面萌宠#"}]
     * content : 今天是2017年8月8日
     * creatTime : 1天前
     * headPortrait : http://192.168.1.139:8090/upload/image/userPic/4.jpg
     * nickName : 发如雪
     * publisherId : 4
     * replyTimes : 0
     * topicId : 1
     * type : 1
     * videoUrl :
     */

    @SerializedName("content")
    private String content;
    @SerializedName("creatTime")
    private String creatTime;
    @SerializedName("headPortrait")
    private String headPortrait;
    @SerializedName("nickName")
    private String nickName;
    @SerializedName("publisherId")
    private int publisherId;
    @SerializedName("replyTimes")
    private int replyTimes;
    @SerializedName("topicId")
    private int topicId;
    @SerializedName("type")
    private int type;
    @SerializedName("videoUrl")
    private String videoUrl;
    @SerializedName("imgUrl")
    private List<String> imgUrl;
    @SerializedName("themeList")
    private List<ChannelPostEntity.ThemeListBean> themeList;
    @SerializedName("videoHeight")
    private int videoHeight;
    @SerializedName("videoWidth")
    private int videoWidth;

    public int getVideoHeight() {
        return videoHeight;
    }

    public void setVideoHeight(int videoHeight) {
        this.videoHeight = videoHeight;
    }

    public int getVideoWidth() {
        return videoWidth;
    }

    public void setVideoWidth(int videoWidth) {
        this.videoWidth = videoWidth;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getReplyTimes() {
        return replyTimes;
    }

    public void setReplyTimes(int replyTimes) {
        this.replyTimes = replyTimes;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public List<String> getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(List<String> imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<ChannelPostEntity.ThemeListBean> getThemeList() {
        return themeList;
    }

    public void setThemeList(List<ChannelPostEntity.ThemeListBean> themeList) {
        this.themeList = themeList;
    }

    @Override
    public String toString() {
        return "ChannelPostListBean{" +
                "content='" + content + '\'' +
                ", creatTime='" + creatTime + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", nickName='" + nickName + '\'' +
                ", publisherId=" + publisherId +
                ", replyTimes=" + replyTimes +
                ", topicId=" + topicId +
                ", type=" + type +
                ", videoUrl='" + videoUrl + '\'' +
                ", imgUrl=" + imgUrl +
                ", themeList=" + themeList +
                '}';
    }

    public static class ThemeListBean {
        /**
         * themeId : 1
         * themeName : #8月封面萌宠#
         */

        @SerializedName("themeId")
        private int themeId;
        @SerializedName("themeName")
        private String themeName;

        public int getThemeId() {
            return themeId;
        }

        public void setThemeId(int themeId) {
            this.themeId = themeId;
        }

        public String getThemeName() {
            return themeName;
        }

        public void setThemeName(String themeName) {
            this.themeName = themeName;
        }
    }

}
