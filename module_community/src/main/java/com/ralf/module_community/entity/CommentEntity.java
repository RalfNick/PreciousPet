package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name CommentEntity
 * @email -
 * @date 2018/12/21 上午11:22
 **/
public class CommentEntity {

    private Integer commentId;
    private Integer userId;
    private String createTime;
    private String content;
    private Integer toUserId;
    private String nickName;
    private String toNickName;

    /**
     * 个人详情页多的字段
     */
    private Integer dynamicId;
    private String headPortrait;

    public CommentEntity(Builder builder) {
        this.commentId = builder.commentId;
        this.userId = builder.userId;
        this.content = builder.content;
        this.createTime = builder.createTime;
        this.dynamicId = builder.dynamicId;
        this.headPortrait = builder.headPortrait;
        this.nickName = builder.nickName;
        this.toUserId = builder.toUserId;
        this.toNickName = builder.toNickName;
    }

    public Integer getDynamicId() {
        return dynamicId;
    }

    public void setDynamicId(Integer dynamicId) {
        this.dynamicId = dynamicId;
    }

    public String getHeadPortrait() {
        return headPortrait;
    }

    public void setHeadPortrait(String headPortrait) {
        this.headPortrait = headPortrait;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToNickName() {
        return toNickName;
    }

    public void setToNickName(String toNickName) {
        this.toNickName = toNickName;
    }

    @Override
    public String toString() {
        return "CommentListEntity{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", createTime='" + createTime + '\'' +
                ", content='" + content + '\'' +
                ", toUserId=" + toUserId +
                ", nickName='" + nickName + '\'' +
                ", toNickName='" + toNickName + '\'' +
                ", dynamicId=" + dynamicId +
                ", headPortrait='" + headPortrait + '\'' +
                '}';
    }

    public static class Builder {

        private Integer commentId;
        private Integer userId;
        private String createTime;
        private String content;
        private Integer toUserId;
        private String nickName;
        private String toNickName;

        /**
         * 个人详情页多的字段
         */
        private Integer dynamicId;
        private String headPortrait;

        public Builder() {
        }

        public Builder commentId(int commentId) {
            this.commentId = commentId;
            return this;
        }

        public Builder userId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder createTime(String createTime) {
            this.createTime = createTime;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder toUserId(int toUserId) {
            this.toUserId = toUserId;
            return this;
        }

        public Builder nickName(String nickName) {
            this.nickName = nickName;
            return this;
        }

        public Builder toNickName(String toNickName) {
            this.toNickName = toNickName;
            return this;
        }

        public CommentEntity build() {
            return new CommentEntity(this);
        }
    }

}
