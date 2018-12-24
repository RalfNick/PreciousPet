package com.ralf.module_community.entity;

import android.support.annotation.DrawableRes;

import com.flyco.tablayout.listener.CustomTabEntity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name TabEntity
 * @email -
 * @date 2018/12/24 上午11:38
 **/
public class TabEntity implements CustomTabEntity {

    private String title;
    private int unSelectedIcon;
    private int selectedIcon;

    public TabEntity(String title) {
        this(title, 0, 0);
    }

    public TabEntity(String title, int unSelectedIcon, int selectedIcon) {
        this.title = title;
        this.unSelectedIcon = unSelectedIcon;
        this.selectedIcon = selectedIcon;
    }

    @Override
    public String getTabTitle() {
        return title;
    }

    @Override
    public int getTabSelectedIcon() {
        return selectedIcon;
    }

    @Override
    public int getTabUnselectedIcon() {
        return unSelectedIcon;
    }
}
