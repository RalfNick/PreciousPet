package com.ralf.module_news.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsDetailEntity
 * @email -
 * @date 2019/05/28 16:20
 **/
public class NewsDetailEntity implements Serializable {

    /**
     * about :
     * author : 鍚勬挄鍏跺疄
     * carousel : 1
     * commentList : [{"city":"閭㈠彴甯�","commentId":0,"commentPraise":0,"commentPraiseCount":1,"content":"鎴戞槸鐢ㄦ埛1,鎴戠粰璧勮1鍙戜簡涓�鏉¤瘎璁�","createTime":"1493785497","headPortrait":"http://192.168.1.139:8090/upload/image/userPic/12.jpg","nickName":"娴呮ⅵ娌睈","province":"閲嶅簡甯�","replyList":[{"content":"鎴戞槸鐢ㄦ埛2,鎴戝洖澶嶄簡鐢ㄦ埛1鐨勮瘎璁�","createTime":"1493785548","nickName":"涓栦笂鍙湁涓�涓垜","replyNickName":"","toNickName":"娴呮ⅵ娌睈","toUserId":1,"userId":2},{"content":"鎴戞槸鐢ㄦ埛2,鎴戝洖澶嶄簡鐢ㄦ埛1鐨勫洖澶�","createTime":"1493785567","nickName":"涓栦笂鍙湁涓�涓垜","replyNickName":"","toNickName":"娴呮ⅵ娌睈","toUserId":1,"userId":2}],"replyListCount":0,"userId":1},{"city":"閭㈠彴甯�","commentId":0,"commentPraise":0,"commentPraiseCount":1,"content":"娴嬭瘯1","createTime":"1494986889","headPortrait":"http://192.168.1.139:8090/upload/image/userPic/12.jpg","nickName":"娴呮ⅵ娌睈","province":"閲嶅簡甯�","replyList":[],"replyListCount":0,"userId":1}]
     * content : sss
     * contentCount : 9
     * createTime : 1475607800
     * featured : 0
     * id : 3
     * imgUrl : http://192.168.1.139:8090/upload/image/carouselPic/14907798156721.jpg
     * pages : 1
     * picCount : 0
     * picList : []
     * recommendList : [{"author":"鍚勬挄鍏跺疄","imgUrl":"http://192.168.1.139:8090/upload/image/carouselPic/14907798350083.jpg","picList":[],"title":"鑷繁閫夋嫨鐨勫皬绁栧畻 璺潃涔熻瀹犲畬","viewTimes":22},{"author":"鍚勬挄鍏跺疄","imgUrl":"http://192.168.1.139:8090/upload/image/carouselPic/14907798308232.jpg","picList":[],"title":"浼氳璇濈殑钀ㄦ懇鑰堕ケ楗�","viewTimes":44}]
     * title : 鐚负浠�涔堝枩娆㈣汉鍦ㄨ姳鐩嗛噷
     * top : 0
     * type : 0
     * url : http://192.168.1.139:8090https://www.baidu.com/
     * videoUrl :
     * viewTimes : 22
     * wish : 0
     */

    private String about;
    private String author;
    private Integer carousel;
    private String content;
    private Integer contentCount;
    private String createTime;
    private Integer featured;
    private Integer id;
    private String imgUrl;
    private Integer pages;
    private Integer picCount;
    private String title;
    private Integer top;
    private Integer type;
    private String url;
    private String videoUrl;
    private Integer viewTimes;
    private Integer wish;
    private Integer imgHigh;
    private Integer imgWidth;
    private List<NewsCommentEntity> commentList;
    private List<ImageUrlEntity> picList;
    private List<RecommendListBean> recommendList;

    public Integer getImgHigh() {
        return imgHigh;
    }

    public void setImgHigh(Integer imgHigh) {
        this.imgHigh = imgHigh;
    }

    public Integer getImgWidth() {
        return imgWidth;
    }

    public void setImgWidth(Integer imgWidth) {
        this.imgWidth = imgWidth;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCarousel() {
        return carousel;
    }

    public void setCarousel(Integer carousel) {
        this.carousel = carousel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getContentCount() {
        return contentCount;
    }

    public void setContentCount(Integer contentCount) {
        this.contentCount = contentCount;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getFeatured() {
        return featured;
    }

    public void setFeatured(Integer featured) {
        this.featured = featured;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPicCount() {
        return picCount;
    }

    public void setPicCount(Integer picCount) {
        this.picCount = picCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getTop() {
        return top;
    }

    public void setTop(Integer top) {
        this.top = top;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(Integer viewTimes) {
        this.viewTimes = viewTimes;
    }

    public Integer getWish() {
        return wish;
    }

    public void setWish(Integer wish) {
        this.wish = wish;
    }

    public List<NewsCommentEntity> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<NewsCommentEntity> commentList) {
        this.commentList = commentList;
    }

    public List<ImageUrlEntity> getPicList() {
        return picList;
    }

    public void setPicList(List<ImageUrlEntity> picList) {
        this.picList = picList;
    }

    public List<RecommendListBean> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<RecommendListBean> recommendList) {
        this.recommendList = recommendList;
    }

    @Override
    public String toString() {
        return "ZixunXiangqingEntity{" +
                "about='" + about + '\'' +
                ", author='" + author + '\'' +
                ", carousel=" + carousel +
                ", content='" + content + '\'' +
                ", contentCount=" + contentCount +
                ", createTime='" + createTime + '\'' +
                ", featured=" + featured +
                ", id=" + id +
                ", imgUrl='" + imgUrl + '\'' +
                ", pages=" + pages +
                ", picCount=" + picCount +
                ", title='" + title + '\'' +
                ", top=" + top +
                ", type=" + type +
                ", url='" + url + '\'' +
                ", videoUrl='" + videoUrl + '\'' +
                ", viewTimes=" + viewTimes +
                ", wish=" + wish +
                ", imgHigh=" + imgHigh +
                ", imgWidth=" + imgWidth +
                ", commentList=" + commentList +
                ", picList=" + picList +
                ", recommendList=" + recommendList +
                '}';
    }

    public static class RecommendListBean implements Serializable{
        /**
         * author : 鍚勬挄鍏跺疄
         * imgUrl : http://192.168.1.139:8090/upload/image/carouselPic/14907798350083.jpg
         * picList : []
         * title : 鑷繁閫夋嫨鐨勫皬绁栧畻 璺潃涔熻瀹犲畬
         * viewTimes : 22
         */

        private String author;
        private String imgUrl;
        private String title;
        private Integer viewTimes;
        private Integer articleId;
        private List<ImageUrlEntity> picList;

        public Integer getArticleId() {
            return articleId;
        }

        public void setArticleId(Integer articleId) {
            this.articleId = articleId;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getViewTimes() {
            return viewTimes;
        }

        public void setViewTimes(Integer viewTimes) {
            this.viewTimes = viewTimes;
        }

        public List<ImageUrlEntity> getPicList() {
            return picList;
        }

        public void setPicList(List<ImageUrlEntity> picList) {
            this.picList = picList;
        }
    }

}
