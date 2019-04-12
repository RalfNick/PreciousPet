package com.ralf.module_community.entity.result;

import com.ralf.module_community.entity.PraiseEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name PraiseListResultEntity
 * @email -
 * @date 2019/04/08 下午5:14
 **/
public class PraiseListResultEntity {

    /**
     * pages : 1
     * list : [{"sex":"0","userId":9,"petList":[],"headPortrait":"","petTotal":0,"province":"","praiseId":6,"city":"","nickName":"asdasd"},{"sex":"1","userId":8,"petList":[],"headPortrait":"","petTotal":0,"province":"","praiseId":5,"city":"","nickName":"asd"},{"sex":"1","userId":2,"petList":[],"headPortrait":"3","petTotal":0,"province":"","praiseId":3,"city":"","nickName":"鎴戞槸鐢ㄦ埛2ID绛変簬2"}]
     */

    private int pages;
    private List<PraiseEntity> list;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<PraiseEntity> getList() {
        return list;
    }

    public void setList(List<PraiseEntity> list) {
        this.list = list;
    }
}
