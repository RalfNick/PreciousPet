package com.ralf.module_community.entity.result;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ListMultipleEntity
 * @email -
 * @date 2019/04/08 上午11:51
 **/
public class ListMultipleEntity<T> implements MultiItemEntity {

    private T mDataBean;
    private String mTitle;
    private int mItemType;

    @Override
    public int getItemType() {
        return mItemType;
    }

    public ListMultipleEntity(int itemType) {
        mItemType = itemType;
    }

    public T getDataBean() {
        return mDataBean;
    }

    public void setDataBean(T dataBean) {
        mDataBean = dataBean;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
}
