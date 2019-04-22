package com.ralf.module_community.entity.result;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name ResultListEntity
 * @email -
 * @date 2019/04/19 下午4:16
 **/
public class ResultListEntity<T> {

    private List<T> list;
    private int pages;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
