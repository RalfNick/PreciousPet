package com.ralf.module_news.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsCommentEntity
 * @email -
 * @date 2019/05/28 16:23
 **/
public class NewsCommentEntity implements Serializable {

    /**
     * city : 閭㈠彴甯�
     * commentId : 0
     * commentPraise : 0
     * commentPraiseCount : 1
     * content : 鎴戞槸鐢ㄦ埛1,鎴戠粰璧勮1鍙戜簡涓�鏉¤瘎璁�
     * createTime : 1493785497
     * headPortrait : http://192.168.1.139:8090/upload/image/userPic/12.jpg
     * nickName : 娴呮ⅵ娌睈
     * province : 閲嶅簡甯�
     * replyList : [{"content":"鎴戞槸鐢ㄦ埛2,鎴戝洖澶嶄簡鐢ㄦ埛1鐨勮瘎璁�","createTime":"1493785548","nickName":"涓栦笂鍙湁涓�涓垜","replyNickName":"","toNickName":"娴呮ⅵ娌睈","toUserId":1,"userId":2},{"content":"鎴戞槸鐢ㄦ埛2,鎴戝洖澶嶄簡鐢ㄦ埛1鐨勫洖澶�","createTime":"1493785567","nickName":"涓栦笂鍙湁涓�涓垜","replyNickName":"","toNickName":"娴呮ⅵ娌睈","toUserId":1,"userId":2}]
     * replyListCount : 0
     * userId : 1
     */

    private String city;
    private Integer commentId;
    private Integer commentPraise;
    private Integer commentPraiseCount;
    private String content;
    private String createTime;
    private String headPortrait;
    private String nickName;
    private String province;
    private Integer replyListCount;
    private Integer userId;
    private Integer pages;

    public String getTransTime() {
        return transTime;
    }

    public void setTransTime(String transTime) {
        this.transTime = transTime;
    }

    private String transTime;
    private List<ReplyListBean> replyList;

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCommentPraise() {
        return commentPraise;
    }

    public void setCommentPraise(Integer commentPraise) {
        this.commentPraise = commentPraise;
    }

    public Integer getCommentPraiseCount() {
        return commentPraiseCount;
    }

    public void setCommentPraiseCount(Integer commentPraiseCount) {
        this.commentPraiseCount = commentPraiseCount;
    }

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Integer getReplyListCount() {
        return replyListCount;
    }

    public void setReplyListCount(Integer replyListCount) {
        this.replyListCount = replyListCount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<ReplyListBean> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyListBean> replyList) {
        this.replyList = replyList;
    }

    public static class ReplyListBean implements Serializable{
        public Integer getCommentId() {
            return commentId;
        }

        public void setCommentId(Integer commentId) {
            this.commentId = commentId;
        }

        /**
         * content : 鎴戞槸鐢ㄦ埛2,鎴戝洖澶嶄簡鐢ㄦ埛1鐨勮瘎璁�
         * createTime : 1493785548
         * nickName : 涓栦笂鍙湁涓�涓垜
         * replyNickName :
         * toNickName : 娴呮ⅵ娌睈
         * toUserId : 1
         * userId : 2
         */

        private Integer commentId;
        private String content;
        private String createTime;
        private String replyNickName;
        private String toNickName;
        private Integer toUserId;
        private Integer userId;

        public String getTransTime() {
            return transTime;
        }

        public void setTransTime(String transTime) {
            this.transTime = transTime;
        }

        private String transTime;

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

        public String getReplyNickName() {
            return replyNickName;
        }

        public void setReplyNickName(String replyNickName) {
            this.replyNickName = replyNickName;
        }

        public String getToNickName() {
            return toNickName;
        }

        public void setToNickName(String toNickName) {
            this.toNickName = toNickName;
        }

        public Integer getToUserId() {
            return toUserId;
        }

        public void setToUserId(Integer toUserId) {
            this.toUserId = toUserId;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }
    }

}
