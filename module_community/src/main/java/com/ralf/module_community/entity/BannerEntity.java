package com.ralf.module_community.entity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name BannerEntity
 * @email -
 * @date 2018/12/21 下午7:35
 **/
public class BannerEntity {

    /**
     * imgUrl : http://192.168.1.139:8090/upload/image/carouselPic/14907798156721.jpg
     * id : 1
     */

    private String imgUrl;

    private Integer id;

    private String linkUrl;

    private Integer type;

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
