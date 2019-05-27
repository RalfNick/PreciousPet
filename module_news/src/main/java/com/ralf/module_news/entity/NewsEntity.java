package com.ralf.module_news.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsEntity
 * @email -
 * @date 2019/05/16 17:47
 **/
public class NewsEntity {

    /**
     * articleId : 1
     * title : 涓虹嫍鐙楁墦椋炴満鎬庝箞灏辨垚浜嗘棩甯哥敓娲�
     * top : 1
     * featured : 1
     * author : 鐜嬩簩鐙�01
     * viewTimes : 22222
     * createTime : 1490837280
     * img : http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg
     * type : 0
     * picTotal : 9
     * picList : [{"picId":1,"picUrl":"http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg"},{"picId":2,"picUrl":"http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg"},{"picId":3,"picUrl":"http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg"}]
     */

    private int articleId;
    private String title;
    private int top;
    private int featured;
    private String author;
    private int viewTimes;
    private String createTime;
    private String img;
    private int type;
    private int picTotal;
    private List<PicListBean> picList;
    private String videoUrl;
    private int high;
    private int width;

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getFeatured() {
        return featured;
    }

    public void setFeatured(int featured) {
        this.featured = featured;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getViewTimes() {
        return viewTimes;
    }

    public void setViewTimes(int viewTimes) {
        this.viewTimes = viewTimes;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPicTotal() {
        return picTotal;
    }

    public void setPicTotal(int picTotal) {
        this.picTotal = picTotal;
    }

    public List<PicListBean> getPicList() {
        return picList;
    }

    public void setPicList(List<PicListBean> picList) {
        this.picList = picList;
    }

    public static class PicListBean {
        /**
         * picId : 1
         * picUrl : http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg
         */

        private int picId;
        private String picUrl;

        public int getPicId() {
            return picId;
        }

        public void setPicId(int picId) {
            this.picId = picId;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        @Override
        public String toString() {
            return "PicListBean{" +
                    "picId=" + picId +
                    ", picUrl='" + picUrl + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "RecommendListBean{" +
                "articleId=" + articleId +
                ", title='" + title + '\'' +
                ", top=" + top +
                ", featured=" + featured +
                ", author='" + author + '\'' +
                ", viewTimes=" + viewTimes +
                ", createTime='" + createTime + '\'' +
                ", img='" + img + '\'' +
                ", type=" + type +
                ", picTotal=" + picTotal +
                ", picList=" + picList +
                '}';
    }

}
