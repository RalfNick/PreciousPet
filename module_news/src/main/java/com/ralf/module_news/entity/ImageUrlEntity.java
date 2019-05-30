package com.ralf.module_news.entity;

import java.io.Serializable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ImageUrlEntity
 * @email -
 * @date 2019/05/28 16:22
 **/
public class ImageUrlEntity implements Serializable {

    private String picUrl;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public String toString() {
        return "ImageUrlEntity{" +
                "picUrl='" + picUrl + '\'' +
                '}';
    }
}
