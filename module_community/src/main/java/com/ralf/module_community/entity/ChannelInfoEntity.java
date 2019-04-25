package com.ralf.module_community.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * 频道列表信息实体类
 * @name ChannelInfoEntity
 * @email -
 * @date 2019/04/23 下午12:04
 **/
public class ChannelInfoEntity {

    /**
     * boutique : 0
     * channelId : 1
     * channelName : 美宠秀秀
     * content : 今天是2017年8月8日
     * creatTime : 1502674612
     * headPortrait : http://192.168.1.81:8090/upload/image/userPic/4.jpg
     * imgUrlList : ["http://192.168.1.81:8090/upload/image/systemPic/icon/dog3x.png","http://192.168.1.81:8090/upload/image/systemPic/icon/fish3x.png","http://192.168.1.81:8090/upload/image/systemPic/icon/cat3x.png"]
     * isTop : 1
     * nickName : 发如雪
     * postId : 4
     * publisherId : 4
     * replyTimes : 0
     * topicDetailList : [{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}]
     * type : 2
     * videoUrl : http://192.168.1.81:8090/upload/video/1f6589f8ac234ece9c6b2bb7877bc398.mp4
     */

    private int boutique;
    private int channelId;
    private String channelName;
    private String content;
    private String creatTime;
    private String headPortrait;
    private int isTop;
    private String nickName;
    private int postId;
    private int publisherId;
    private int replyTimes;
    private int type;
    private String videoUrl;
    private List<String> imgUrlList;
    private List<TopicBean> topicDetailList;
    private int videoHeight;
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

    public int getBoutique() {
        return boutique;
    }

    public void setBoutique(int boutique) {
        this.boutique = boutique;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
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

    public int getIsTop() {
        return isTop;
    }

    public void setIsTop(int isTop) {
        this.isTop = isTop;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
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

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public List<TopicBean> getTopicDetailList() {
        return topicDetailList;
    }

    public void setTopicDetailList(List<TopicBean> topicDetailList) {
        this.topicDetailList = topicDetailList;
    }

    @Override
    public String toString() {
        return "ChannelInfoBean{" +
                "boutique=" + boutique +
                ", channelId=" + channelId +
                ", channelName='" + channelName + '\'' +
                ", content='" + content + '\'' +
                ", creatTime='" + creatTime + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", isTop=" + isTop +
                ", nickName='" + nickName + '\'' +
                ", postId=" + postId +
                ", publisherId=" + publisherId +
                ", replyTimes=" + replyTimes +
                ", type=" + type +
                ", videoUrl='" + videoUrl + '\'' +
                ", imgUrlList=" + imgUrlList +
                ", topicDetailList=" + topicDetailList +
                '}';
    }

    public static class TopicBean {
        /**
         * topicDetailId : 1
         * topicDetailName : #8月封面萌宠#
         */

        private int topicDetailId;
        private String topicDetailName;

        public int getTopicDetailId() {
            return topicDetailId;
        }

        public void setTopicDetailId(int topicDetailId) {
            this.topicDetailId = topicDetailId;
        }

        public String getTopicDetailName() {
            return topicDetailName;
        }

        public void setTopicDetailName(String topicDetailName) {
            this.topicDetailName = topicDetailName;
        }
    }

}
