package com.ralf.module_news.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name BannerEntity
 * @email -
 * @date 2019/05/16 17:48
 **/
public class BannerEntity {

    /**
     * title : 鐚负浠�涔堝枩娆㈣汉鍦ㄨ姳鐩嗛噷
     * imgUrl : http://192.168.1.139:8090/upload/image/carouselPic/14907798156721.jpg
     * id : 1
     */

    private String title;
    private String imgUrl;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BannerListBean{" +
                "title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", id=" + id +
                '}';
    }
}
