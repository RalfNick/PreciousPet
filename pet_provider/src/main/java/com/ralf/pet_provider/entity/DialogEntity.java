package com.ralf.pet_provider.entity;

import android.graphics.drawable.Drawable;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DialogEntity
 * @email -
 * @date 2019/01/19 下午8:07
 **/
public class DialogEntity {

    private String title;
    private Drawable resId;

    public DialogEntity(String title) {
        this.title = title;
    }

    public DialogEntity(String title, Drawable resId) {
        this.title = title;
        this.resId = resId;
    }

    public String getTitle() {
        return title;
    }

    public Drawable getResId() {
        return resId;
    }

    @Override
    public String toString() {
        return "DialogEntity{" +
                "title='" + title + '\'' +
                ", resId=" + resId +
                '}';
    }
}
