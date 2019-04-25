package com.ralf.module_community.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ChannelDetailEntity
 * @email -
 * @date 2019/03/06 上午11:24
 **/
public class ChannelDetailEntity {

    /**
     * boutique : 0
     * channelId : 1
     * channelMember : 5607
     * channelName : 美宠秀秀
     * channelPic : http://192.168.1.139:8090/upload/image/userPic/dff115b6d39d40c8b62b145752eae812.jpg
     * channelTopicNum : 7
     * city : 涪陵区
     * commentList : [{"responseList":{"commentId":83013,"content":"测试内容f2b8","creatTime":"测试内容8y11","nickName":"测试内容7em7","postId":21323,"responseId":53082,"toNickName":"测试内容83vd","toUserId":13076,"userId":75313},"canDelete":0,"city":"涪陵区","commentId":1,"commentPic":"","content":"1","creatTime":"1502413768","floor":1,"headPortrait":"http://192.168.1.139:8090/upload/image/userPic/6.jpg","nickName":"时光小偷","postId":1,"province":"辽宁省","userId":6},{"responseList":{"commentId":83013,"content":"测试内容f2b8","creatTime":"测试内容8y11","nickName":"测试内容7em7","postId":21323,"responseId":53082,"toNickName":"测试内容83vd","toUserId":13076,"userId":75313},"canDelete":0,"city":"涪陵区","commentId":2,"commentPic":"http://192.168.1.139:8090/upload/image/channelCommentPic/c16449aa8a5f40f3b42e9131bd3d67cf.jpg","content":"1","creatTime":"1502414023","floor":2,"headPortrait":"http://192.168.1.139:8090/upload/image/userPic/6.jpg","nickName":"时光小偷","postId":1,"province":"辽宁省","userId":6}]
     * content : 今天是2017年8月8日
     * creatTime : 1502163055
     * headPortrait : http://192.168.1.139:8090/upload/image/userPic/4.jpg
     * imgUrlList : ["http://192.168.1.139:8090/upload/image/ChannelPic/adf1477ba27f42908052b2872a24a886.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/87cbe45d87264c10ac03dcc941dae98e.jpg","http://192.168.1.139:8090/upload/image/ChannelPic/40f7f4a9e80b4d9395d3509d2476a249.jpg"]
     * isAttention: 0
     * isTop : 0
     * nickName : 发如雪
     * pages : 1
     * petBreed :
     * petHeadPortrait:
     * petName :
     * petSex : 0
     * postId : 1
     * province : 辽宁省
     * publisherId : 4
     * replyTimes : 0
     * topicDetailList : [{"topicDetailId":1,"topicDetailName":"#8月封面萌宠#"},{"topicDetailId":2,"topicDetailName":"#我要上精选#"}]
     * total : 2
     * type : 1
     * userPetId : 0
     * videoUrl :
     * viewTimes : 4
     */

    @SerializedName("boutique")
    private int boutique;
    @SerializedName("channelId")
    private int channelId;
    @SerializedName("channelMember")
    private int channelMember;
    @SerializedName("channelName")
    private String channelName;
    @SerializedName("channelPic")
    private String channelPic;
    @SerializedName("channelTopicNum")
    private int channelTopicNum;
    @SerializedName("city")
    private String city;
    @SerializedName("content")
    private String content;
    @SerializedName("creatTime")
    private String creatTime;
    @SerializedName("headPortrait")
    private String headPortrait;
    @SerializedName("isTop")
    private int isTop;
    @SerializedName("isWish")
    private int isWish;
    @SerializedName("isAttention")
    private int isAttention;
    @SerializedName("nickName")
    private String nickName;
    @SerializedName("pages")
    private int pages;
    @SerializedName("petBreed")
    private String petBreed;
    @SerializedName("petName")
    private String petName;
    @SerializedName("petHeadPortrait")
    private String petHeadPortrait;
    @SerializedName("petSex")
    private int petSex;
    @SerializedName("postId")
    private int postId;
    @SerializedName("province")
    private String province;
    @SerializedName("publisherId")
    private int publisherId;
    @SerializedName("replyTimes")
    private int replyTimes;
    @SerializedName("total")
    private int total;
    @SerializedName("type")
    private int type;
    @SerializedName("userPetId")
    private int userPetId;
    @SerializedName("videoUrl")
    private String videoUrl;
    @SerializedName("viewTimes")
    private int viewTimes;
    @SerializedName("commentList")
    private List<CommentListBean> commentList;
    @SerializedName("imgUrlList")
    private List<String> imgUrlList;
    @SerializedName("topicDetailList")
    private List<TopicDetailListBean> topicDetailList;
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

    public int getChannelMember() {
        return channelMember;
    }

    public void setChannelMember(int channelMember) {
        this.channelMember = channelMember;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelPic() {
        return channelPic;
    }

    public void setChannelPic(String channelPic) {
        this.channelPic = channelPic;
    }

    public int getChannelTopicNum() {
        return channelTopicNum;
    }

    public void setChannelTopicNum(int channelTopicNum) {
        this.channelTopicNum = channelTopicNum;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getPetHeadPortrait() {
        return petHeadPortrait;
    }

    public void setPetHeadPortrait(String petHeadPortrait) {
        this.petHeadPortrait = petHeadPortrait;
    }

    public int getPetSex() {
        return petSex;
    }

    public void setPetSex(int petSex) {
        this.petSex = petSex;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getUserPetId() {
        return userPetId;
    }

    public void setUserPetId(int userPetId) {
        this.userPetId = userPetId;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(int viewTimes) {
        this.viewTimes = viewTimes;
    }

    public List<CommentListBean> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentListBean> commentList) {
        this.commentList = commentList;
    }

    public List<String> getImgUrlList() {
        return imgUrlList;
    }

    public void setImgUrlList(List<String> imgUrlList) {
        this.imgUrlList = imgUrlList;
    }

    public List<TopicDetailListBean> getTopicDetailList() {
        return topicDetailList;
    }

    public void setTopicDetailList(List<TopicDetailListBean> topicDetailList) {
        this.topicDetailList = topicDetailList;
    }

    public int getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(int isAttention) {
        this.isAttention = isAttention;
    }

    public int getIsWish() {
        return isWish;
    }

    public void setIsWish(int isWish) {
        this.isWish = isWish;
    }

    @Override
    public String toString() {
        return "ChannelPostsDetailsEntity{" +
                "boutique=" + boutique +
                ", channelId=" + channelId +
                ", channelMember=" + channelMember +
                ", channelName='" + channelName + '\'' +
                ", channelPic='" + channelPic + '\'' +
                ", channelTopicNum=" + channelTopicNum +
                ", city='" + city + '\'' +
                ", content='" + content + '\'' +
                ", creatTime='" + creatTime + '\'' +
                ", headPortrait='" + headPortrait + '\'' +
                ", isTop=" + isTop +
                ", isWish=" + isWish +
                ", isAttention=" + isAttention +
                ", nickName='" + nickName + '\'' +
                ", pages=" + pages +
                ", petBreed='" + petBreed + '\'' +
                ", petName='" + petName + '\'' +
                ", petHeadPortrait='" + petHeadPortrait + '\'' +
                ", petSex=" + petSex +
                ", postId=" + postId +
                ", province='" + province + '\'' +
                ", publisherId=" + publisherId +
                ", replyTimes=" + replyTimes +
                ", total=" + total +
                ", type=" + type +
                ", userPetId=" + userPetId +
                ", videoUrl='" + videoUrl + '\'' +
                ", viewTimes=" + viewTimes +
                ", commentList=" + commentList +
                ", imgUrlList=" + imgUrlList +
                ", topicDetailList=" + topicDetailList +
                '}';
    }

    public static class CommentListBean {
        /**
         * responseList : {"commentId":83013,"content":"测试内容f2b8","creatTime":"测试内容8y11","nickName":"测试内容7em7","postId":21323,"responseId":53082,"toNickName":"测试内容83vd","toUserId":13076,"userId":75313}
         * canDelete : 0
         * city : 涪陵区
         * commentId : 1
         * commentPic :
         * content : 1
         * creatTime : 1502413768
         * floor : 1
         * headPortrait : http://192.168.1.139:8090/upload/image/userPic/6.jpg
         * nickName : 时光小偷
         * postId : 1
         * province : 辽宁省
         * userId : 6
         */

        @SerializedName("responseList")
        private List<ResponseListBean> responseList;
        @SerializedName("canDelete")
        private int canDelete;
        @SerializedName("city")
        private String city;
        @SerializedName("commentId")
        private int commentId;
        @SerializedName("commentPic")
        private String commentPic;
        @SerializedName("content")
        private String content;
        @SerializedName("creatTime")
        private String creatTime;
        @SerializedName("floor")
        private int floor;
        @SerializedName("headPortrait")
        private String headPortrait;
        @SerializedName("nickName")
        private String nickName;
        @SerializedName("postId")
        private int postId;
        @SerializedName("province")
        private String province;
        @SerializedName("userId")
        private int userId;

        public List<ResponseListBean> getResponseList() {
            return responseList;
        }

        public void setResponseList(List<ResponseListBean> responseList) {
            this.responseList = responseList;
        }

        public int getCanDelete() {
            return canDelete;
        }

        public void setCanDelete(int canDelete) {
            this.canDelete = canDelete;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getCommentId() {
            return commentId;
        }

        public void setCommentId(int commentId) {
            this.commentId = commentId;
        }

        public String getCommentPic() {
            return commentPic;
        }

        public void setCommentPic(String commentPic) {
            this.commentPic = commentPic;
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

        public int getFloor() {
            return floor;
        }

        public void setFloor(int floor) {
            this.floor = floor;
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

        public int getPostId() {
            return postId;
        }

        public void setPostId(int postId) {
            this.postId = postId;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "CommentListBean{" +
                    "responseList=" + responseList +
                    ", canDelete=" + canDelete +
                    ", city='" + city + '\'' +
                    ", commentId=" + commentId +
                    ", commentPic='" + commentPic + '\'' +
                    ", content='" + content + '\'' +
                    ", creatTime='" + creatTime + '\'' +
                    ", floor=" + floor +
                    ", headPortrait='" + headPortrait + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", postId=" + postId +
                    ", province='" + province + '\'' +
                    ", userId=" + userId +
                    '}';
        }

        public static class ResponseListBean {
            /**
             * commentId : 83013
             * content : 测试内容f2b8
             * creatTime : 测试内容8y11
             * nickName : 测试内容7em7
             * postId : 21323
             * responseId : 53082
             * toNickName : 测试内容83vd
             * toUserId : 13076
             * userId : 75313
             */

            @SerializedName("commentId")
            private int commentId;
            @SerializedName("content")
            private String content;
            @SerializedName("creatTime")
            private String creatTime;
            @SerializedName("nickName")
            private String nickName;
            @SerializedName("postId")
            private int postId;
            @SerializedName("responseId")
            private int responseId;
            @SerializedName("toNickName")
            private String toNickName;
            @SerializedName("toUserId")
            private int toUserId;
            @SerializedName("userId")
            private int userId;

            public int getCommentId() {
                return commentId;
            }

            public void setCommentId(int commentId) {
                this.commentId = commentId;
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

            public int getResponseId() {
                return responseId;
            }

            public void setResponseId(int responseId) {
                this.responseId = responseId;
            }

            public String getToNickName() {
                return toNickName;
            }

            public void setToNickName(String toNickName) {
                this.toNickName = toNickName;
            }

            public int getToUserId() {
                return toUserId;
            }

            public void setToUserId(int toUserId) {
                this.toUserId = toUserId;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            @Override
            public String toString() {
                return "ResponseListBean{" +
                        "commentId=" + commentId +
                        ", content='" + content + '\'' +
                        ", creatTime='" + creatTime + '\'' +
                        ", nickName='" + nickName + '\'' +
                        ", postId=" + postId +
                        ", responseId=" + responseId +
                        ", toNickName='" + toNickName + '\'' +
                        ", toUserId=" + toUserId +
                        ", userId=" + userId +
                        '}';
            }
        }
    }

    public static class TopicDetailListBean {
        /**
         * topicDetailId : 1
         * topicDetailName : #8月封面萌宠#
         */

        @SerializedName("topicDetailId")
        private int topicDetailId;
        @SerializedName("topicDetailName")
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

        @Override
        public String toString() {
            return "TopicBean{" +
                    "topicDetailId=" + topicDetailId +
                    ", topicDetailName='" + topicDetailName + '\'' +
                    '}';
        }
    }

}
