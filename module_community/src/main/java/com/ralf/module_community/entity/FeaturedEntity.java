package com.ralf.module_community.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * 精选的实体类
 * @name FeaturedEntity
 * @email -
 * @date 2018/12/20 下午5:58
 **/
public class FeaturedEntity {

    private Integer pages;
    private List<DynamicEntity> dynamicList;
    private List<BannerEntity> bannerList;

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<DynamicEntity> getDynamicList() {
        return dynamicList;
    }

    public void setDynamicList(List<DynamicEntity> dynamicList) {
        this.dynamicList = dynamicList;
    }

    public List<BannerEntity> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerEntity> bannerList) {
        this.bannerList = bannerList;
    }

    @Override
    public String toString() {
        return "FeaturedEntity{" +
                "pages=" + pages +
                ", dynamicList=" + dynamicList +
                ", bannerList=" + bannerList +
                '}';
    }
}
