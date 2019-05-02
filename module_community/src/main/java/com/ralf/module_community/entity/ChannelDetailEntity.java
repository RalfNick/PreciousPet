package com.ralf.module_community.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailEntity
 * @email -
 * @date 2019/04/30 11:00
 **/
public class ChannelDetailEntity {

    /**
     * channelId : 1
     * channelPostList : [{"imgUrl":["http://192.168.1.139:8090/upload/image/ChannelPic/adf1477ba27f42908052b2872a24a886.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/87cbe45d87264c10ac03dcc941dae98e.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/40f7f4a9e80b4d9395d3509d2476a249.jpg"],"themeList":[{"themeId":1,"themeName":"#8月封面萌宠#"}],"content":"今天是2017年8月8日","creatTime":"1天前","headPortrait":"http://192.168.1.139:8090/upload/image/userPic/4.jpg","nickName":"发如雪","publisherId":4,"replyTimes":0,"topicId":1,"type":1,"videoUrl":""},{"imgUrl":["http://192.168.1.139:8090/upload/image/ChannelPic/adf1477ba27f42908052b2872a24a886.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/87cbe45d87264c10ac03dcc941dae98e.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/40f7f4a9e80b4d9395d3509d2476a249.jpg"],"themeList":[{"themeId":2,"themeName":"#我要上精选#"}],"content":"今天是2017年8月8日","creatTime":"1天前","headPortrait":"http://192.168.1.139:8090/upload/image/userPic/4.jpg","nickName":"发如雪","publisherId":4,"replyTimes":0,"topicId":2,"type":1,"videoUrl":""},{"imgUrl":["http://192.168.1.139:8090/upload/image/ChannelPic/adf1477ba27f42908052b2872a24a886.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/87cbe45d87264c10ac03dcc941dae98e.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/40f7f4a9e80b4d9395d3509d2476a249.jpg"],"themeList":[{"themeId":1,"themeName":"#8月封面萌宠#"}],"content":"今天是2017年8月8日","creatTime":"3小时前","headPortrait":"http://192.168.1.139:8090/upload/image/userPic/4.jpg","nickName":"发如雪","publisherId":4,"replyTimes":0,"topicId":4,"type":2,"videoUrl":"http://192.168.1.139:8090/upload/video/channelVideo/videoFile/eeed32fe57eb45d28bacd544d9262ad4.mp4"},{"imgUrl":["http://192.168.1.139:8090/upload/image/ChannelPic/adf1477ba27f42908052b2872a24a886.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/87cbe45d87264c10ac03dcc941dae98e.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/40f7f4a9e80b4d9395d3509d2476a249.jpg"],"themeList":[{"themeId":2,"themeName":"#我要上精选#"}],"content":"今天是2017年8月9日，我是纯文本","creatTime":"3小时前","headPortrait":"http://192.168.1.139:8090/upload/image/userPic/4.jpg","nickName":"发如雪","publisherId":4,"replyTimes":0,"topicId":5,"type":0,"videoUrl":""},{"imgUrl":["http://192.168.1.139:8090/upload/image/ChannelPic/adf1477ba27f42908052b2872a24a886.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/87cbe45d87264c10ac03dcc941dae98e.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/40f7f4a9e80b4d9395d3509d2476a249.jpg"],"themeList":[{"themeId":1,"themeName":"#8月封面萌宠#"}],"content":"今天是2017年8月9日,我也是纯文本","creatTime":"3小时前","headPortrait":"http://192.168.1.139:8090/upload/image/userPic/4.jpg","nickName":"发如雪","publisherId":4,"replyTimes":0,"topicId":6,"type":0,"videoUrl":""}]
     * channelStickList : [{"title":"的话你刚刚","topicId":3}]
     * channelTitle : {"attention":1,"channelName":"美宠秀秀","channelUrl":"http://192.168.1.139:8090","members":0,"topicNum":0}
     * pageCount : 10
     * userId : 1
     */

    @SerializedName("channelId")
    private int channelId;
    @SerializedName("channelTitle")
    private ChannelTitleBean channelTitle;
    @SerializedName("pageCount")
    private int pageCount;
    @SerializedName("userId")
    private int userId;
    @SerializedName("channelPostList")
    private List<ChannelPostEntity> channelPostList;
    @SerializedName("channelStickList")
    private List<ChannelStickListBean> channelStickList;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public ChannelTitleBean getChannelTitle() {
        return channelTitle;
    }

    public void setChannelTitle(ChannelTitleBean channelTitle) {
        this.channelTitle = channelTitle;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<ChannelPostEntity> getChannelPostList() {
        return channelPostList;
    }

    public void setChannelPostList(List<ChannelPostEntity> channelPostList) {
        this.channelPostList = channelPostList;
    }

    public List<ChannelStickListBean> getChannelStickList() {
        return channelStickList;
    }

    public void setChannelStickList(List<ChannelStickListBean> channelStickList) {
        this.channelStickList = channelStickList;
    }

    @Override
    public String toString() {
        return "ChannelDetailsEntity{" +
                "channelId=" + channelId +
                ", channelTitle=" + channelTitle +
                ", pageCount=" + pageCount +
                ", userId=" + userId +
                ", channelPostList=" + channelPostList +
                ", channelStickList=" + channelStickList +
                '}';
    }

    public static class ChannelTitleBean {


        /**
         * about : xx
         * attention : 1
         * channelName : 美宠秀秀
         * channelUrl : http://192.168.1.139:8090
         * members : 0
         * topicNum : 0
         */
        @SerializedName("about")
        private String about;
        @SerializedName("attention")
        private int attention;
        @SerializedName("channelName")
        private String channelName;
        @SerializedName("channelUrl")
        private String channelUrl;
        @SerializedName("members")
        private int members;
        @SerializedName("topicNum")
        private int topicNum;

        public int getAttention() {
            return attention;
        }

        public void setAttention(int attention) {
            this.attention = attention;
        }

        public String getChannelName() {
            return channelName;
        }

        public void setChannelName(String channelName) {
            this.channelName = channelName;
        }

        public String getChannelUrl() {
            return channelUrl;
        }

        public void setChannelUrl(String channelUrl) {
            this.channelUrl = channelUrl;
        }

        public int getMembers() {
            return members;
        }

        public void setMembers(int members) {
            this.members = members;
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

        @Override
        public String toString() {
            return "ChannelTitleBean{" +
                    "about='" + about + '\'' +
                    ", attention=" + attention +
                    ", channelName='" + channelName + '\'' +
                    ", channelUrl='" + channelUrl + '\'' +
                    ", members=" + members +
                    ", topicNum=" + topicNum +
                    '}';
        }
    }

    public static class ChannelStickListBean {
        /**
         * title : 的话你刚刚
         * topicId : 3
         */

        @SerializedName("title")
        private String title;
        @SerializedName("topicId")
        private int topicId;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getTopicId() {
            return topicId;
        }

        public void setTopicId(int topicId) {
            this.topicId = topicId;
        }

        @Override
        public String toString() {
            return "ChannelStickListBean{" +
                    "title='" + title + '\'' +
                    ", topicId=" + topicId +
                    '}';
        }
    }

}
