package com.ralf.module_community.entity.result;

import com.ralf.module_community.entity.ChannelInfoEntity;
import com.ralf.module_community.entity.ChannelTypeEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelResultEntity
 * @email -
 * @date 2019/04/23 上午11:52
 **/
public class ChannelResultEntity {

    /**
     * cnlInfoList : [{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月8日","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":["http://192.168.1.81:8090/upload/image/systemPic/icon/dog3x.png","http://192.168.1.81:8090/upload/image/systemPic/icon/fish3x.png","http://192.168.1.81:8090/upload/image/systemPic/icon/cat3x.png"],"isTop":1,"nickName":"发如雪","postId":4,"publisherId":4,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}],"type":2,"videoUrl":"http://192.168.1.81:8090/upload/video/1f6589f8ac234ece9c6b2bb7877bc398.mp4"},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月9日，我是纯文本","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":[],"isTop":1,"nickName":"发如雪","postId":5,"publisherId":4,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}],"type":0,"videoUrl":""},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月17日","creatTime":"1502940708","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/dff115b6d39d40c8b62b145752eae812.jpg","imgUrlList":["http://192.168.1.81:8090/upload/video/channelVideo/videoPic/eeed32fe57eb45d28bacd544d9262ad4.jpg"],"isTop":0,"nickName":"大大","postId":18,"publisherId":16444,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}],"type":2,"videoUrl":"http://192.168.1.81:8090/upload/video/channelVideo/videoFile/eeed32fe57eb45d28bacd544d9262ad4.mp4"},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月8日","creatTime":"1502940132","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":["http://192.168.1.81:8090/upload/video/channelVideo/videoPic/eeed32fe57eb45d28bacd544d9262ad4.jpg"],"isTop":0,"nickName":"发如雪","postId":17,"publisherId":4,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}],"type":2,"videoUrl":"http://192.168.1.81:8090/upload/video/channelVideo/videoFile/eeed32fe57eb45d28bacd544d9262ad4.mp4"},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月8日","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":["http://192.168.1.81:8090/upload/image/systemPic/icon/dog3x.png","http://192.168.1.81:8090/upload/image/systemPic/icon/fish3x.png","http://192.168.1.81:8090/upload/image/systemPic/icon/cat3x.png"],"isTop":0,"nickName":"发如雪","postId":1,"publisherId":4,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}],"type":1,"videoUrl":""},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月8日","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":["http://192.168.1.81:8090/upload/image/systemPic/icon/dog3x.png","http://192.168.1.81:8090/upload/image/systemPic/icon/fish3x.png","http://192.168.1.81:8090/upload/image/systemPic/icon/cat3x.png"],"isTop":0,"nickName":"发如雪","postId":2,"publisherId":4,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}],"type":1,"videoUrl":""},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月8日","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":[],"isTop":0,"nickName":"发如雪","postId":3,"publisherId":4,"replyTimes":0,"topicDetailList":[],"type":0,"videoUrl":""},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月9日,我也是纯文本","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":[],"isTop":0,"nickName":"发如雪","postId":6,"publisherId":4,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}],"type":0,"videoUrl":""},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月9日，发完贴都得+1","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":["http://192.168.1.81:8090/upload/image/systemPic/icon/dog3x.png"],"isTop":0,"nickName":"发如雪","postId":7,"publisherId":4,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}],"type":1,"videoUrl":""},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月9日，发完贴都得+1, 换话题，换频道霍霍","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":["http://192.168.1.81:8090/upload/image/systemPic/icon/dog3x.png"],"isTop":0,"nickName":"发如雪","postId":8,"publisherId":4,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"}],"type":1,"videoUrl":""},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月8日","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":["http://192.168.1.81:8090/upload/image/systemPic/icon/dog3x.png"],"isTop":0,"nickName":"发如雪","postId":10,"publisherId":4,"replyTimes":0,"topicDetailList":[{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}],"type":2,"videoUrl":"http://192.168.1.81:8090/upload/video/1f6589f8ac234ece9c6b2bb7877bc398.mp4"},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月8日","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":["http://192.168.1.81:8090/upload/image/systemPic/icon/dog3x.png"],"isTop":0,"nickName":"发如雪","postId":11,"publisherId":4,"replyTimes":0,"topicDetailList":[],"type":2,"videoUrl":"http://192.168.1.81:8090/upload/video/1f6589f8ac234ece9c6b2bb7877bc398.mp4"},{"boutique":0,"channelId":1,"channelName":"美宠秀秀","content":"今天是2017年8月8日","creatTime":"1502674612","headPortrait":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","imgUrlList":["http://192.168.1.81:8090/upload/image/systemPic/icon/dog3x.png"],"isTop":0,"nickName":"发如雪","postId":12,"publisherId":4,"replyTimes":0,"topicDetailList":[],"type":2,"videoUrl":"http://192.168.1.81:8090/upload/video/1f6589f8ac234ece9c6b2bb7877bc398.mp4"}]
     * myList : [{"channelId":1,"channelTitle":"美宠秀秀","channelUrl":"http://192.168.1.81:8090/upload/image/userPic/dff115b6d39d40c8b62b145752eae812.jpg","newNumber":0},{"channelId":2,"channelTitle":"时尚休闲","channelUrl":"http://192.168.1.81:8090/upload/image/userPic/8.jpg","newNumber":0},{"channelId":3,"channelTitle":"囧趣宠事","channelUrl":"http://192.168.1.81:8090/upload/image/userPic/4.jpg","newNumber":0},{"channelId":4,"channelTitle":"喵星人俱乐部","channelUrl":"http://192.168.1.81:8090/upload/image/userPic/7.jpg","newNumber":0},{"channelId":5,"channelTitle":"汪星人俱乐部","channelUrl":"http://192.168.1.81:8090/upload/image/userPic/6.jpg","newNumber":0},{"channelId":6,"channelTitle":"异宠星球","channelUrl":"http://192.168.1.81:8090/upload/image/userPic/15.jpg","newNumber":0}]
     * pages : 1
     * total : 13
     * userId : 16632
     */

    private int pages;
    private int total;
    private int userId;
    private List<ChannelInfoEntity> cnlInfoList;
    private List<ChannelTypeEntity> myList;
    private int likeStatus;
    private int members;
    private int status;
    private String themePicUrl;
    private int topicDetailId;
    private String topicDetailName;
    private int topicNum;
    private String about;
    private int channelId;

    public int getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(int likeStatus) {
        this.likeStatus = likeStatus;
    }

    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getThemePicUrl() {
        return themePicUrl;
    }

    public void setThemePicUrl(String themePicUrl) {
        this.themePicUrl = themePicUrl;
    }

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

    public int getTopicNum() {
        return topicNum;
    }

    public void setTopicNum(int topicNum) {
        this.topicNum = topicNum;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ChannelInfoEntity> getCnlInfoList() {
        return cnlInfoList;
    }

    public void setCnlInfoList(List<ChannelInfoEntity> cnlInfoList) {
        this.cnlInfoList = cnlInfoList;
    }

    public List<ChannelTypeEntity> getMyList() {
        return myList;
    }

    public void setMyList(List<ChannelTypeEntity> myList) {
        this.myList = myList;
    }

    @Override
    public String toString() {
        return "ChannelEntivity{" +
                "pages=" + pages +
                ", total=" + total +
                ", userId=" + userId +
                ", cnlInfoList=" + cnlInfoList +
                ", myList=" + myList +
                ", likeStatus=" + likeStatus +
                ", members=" + members +
                ", status=" + status +
                ", themePicUrl='" + themePicUrl + '\'' +
                ", topicDetailId=" + topicDetailId +
                ", topicDetailName='" + topicDetailName + '\'' +
                ", topicNum=" + topicNum +
                ", about='" + about + '\'' +
                ", channelId=" + channelId +
                '}';
    }
}
