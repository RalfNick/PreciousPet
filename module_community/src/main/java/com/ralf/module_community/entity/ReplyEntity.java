package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ReplyEntity
 * @email -
 * @date 2019/03/08 下午8:21
 **/
public class ReplyEntity {

    /**
     * award : 测试内容pc26
     * commentDetail : {"content":"评论你怎么地","createTime":"1493348827","dynamicId":1,"id":757,"nickName":"浅梦沫汐","publisherId":1,"toNickName":"浅梦沫汐","toUserId":1,"userId":1}
     */

    private String award;
    private CommentDetailBean commentDetail;

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public CommentDetailBean getCommentDetail() {
        return commentDetail;
    }

    public void setCommentDetail(CommentDetailBean commentDetail) {
        this.commentDetail = commentDetail;
    }

    public static class CommentDetailBean {
        /**
         * content : 评论你怎么地
         * createTime : 1493348827
         * dynamicId : 1
         * id : 757
         * nickName : 浅梦沫汐
         * publisherId : 1
         * toNickName : 浅梦沫汐
         * toUserId : 1
         * userId : 1
         */

        private String content;
        private String createTime;
        private int dynamicId;
        private int id;
        private String nickName;
        private int publisherId;
        private String toNickName;
        private int toUserId;
        private int userId;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getDynamicId() {
            return dynamicId;
        }

        public void setDynamicId(int dynamicId) {
            this.dynamicId = dynamicId;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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
    }
}
