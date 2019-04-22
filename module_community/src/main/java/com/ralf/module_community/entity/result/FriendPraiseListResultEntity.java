package com.ralf.module_community.entity.result;

import com.ralf.module_community.entity.FriendPraiseEntity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name FriendPraiseListResultEntity
 * @email -
 * @date 2019/04/15 下午1:24
 **/
public class FriendPraiseListResultEntity {

    /**
     * newPetList : [{"createTime":1492136125,"dynamicId":16,"dynamicsPath":"http://192.168.1.95:8090/upload/video/videoFile/e2229fc4d9054a02a37f10b9b1f36349.mp4","high":150,"nickName":"","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/h8.jpg","petId":16,"petName":"澶ф瘺","petSex":0,"type":1,"userHeadPortrait":"","videoPrintscreen":"http://192.168.1.95:8090/upload/video/videoPic/1490776077081video.jpg","width":150},{"createTime":1492136126,"dynamicId":17,"dynamicsPath":"http://192.168.1.95:8090/upload/video/videoFile/e2229fc4d9054a02a37f10b9b1f36349.mp4","high":150,"nickName":"","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/h6.jpg","petId":17,"petName":"璞嗚眴","petSex":0,"type":1,"userHeadPortrait":"","videoPrintscreen":"http://192.168.1.95:8090/upload/video/videoPic/1490776077081video.jpg","width":150},{"createTime":1492136124,"dynamicId":15,"dynamicsPath":"http://192.168.1.95:8090/upload/video/videoFile/e2229fc4d9054a02a37f10b9b1f36349.mp4","high":150,"nickName":"鍕垮姩55","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/h5.jpg","petId":15,"petName":"灏廎鍕垮姩","petSex":0,"type":1,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/15.jpg","videoPrintscreen":"http://192.168.1.95:8090/upload/video/videoPic/1490776077081video.jpg","width":150},{"createTime":1492136121,"dynamicId":12,"dynamicsPath":"http://192.168.1.95:8090/upload/video/videoFile/e2229fc4d9054a02a37f10b9b1f36349.mp4","high":150,"nickName":"鍕垮姩22","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/h2.jpg","petId":12,"petName":"灏廈鍕垮姩","petSex":0,"type":1,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/12.jpg","videoPrintscreen":"http://192.168.1.95:8090/upload/video/videoPic/e2229fc4d9054a02a37f10b9b1f36349.jpg","width":150},{"createTime":1492136123,"dynamicId":14,"dynamicsPath":"http://192.168.1.95:8090/upload/video/videoFile/e2229fc4d9054a02a37f10b9b1f36349.mp4","high":150,"nickName":"鍕垮姩44","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/h4.jpg","petId":14,"petName":"灏廌鍕垮姩","petSex":0,"type":1,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/14.jpg","videoPrintscreen":"http://192.168.1.95:8090/upload/video/videoPic/1490776077081video.jpg","width":150},{"createTime":1492136122,"dynamicId":13,"dynamicsPath":"http://192.168.1.95:8090/upload/video/videoFile/e2229fc4d9054a02a37f10b9b1f36349.mp4","high":150,"nickName":"鍕垮姩33","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/h3.jpg","petId":13,"petName":"灏廋鍕垮姩","petSex":0,"type":1,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/13.jpg","videoPrintscreen":"http://192.168.1.95:8090/upload/video/videoPic/1490776077081video.jpg","width":150},{"createTime":1492136120,"dynamicId":11,"dynamicsPath":"http://192.168.1.95:8090/upload/video/videoFile/e2229fc4d9054a02a37f10b9b1f36349.mp4","high":150,"nickName":"娓呴鍚规垜瑗�","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/h1.jpg","petId":11,"petName":"灏廇鍕垮姩","petSex":0,"type":1,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/8.jpg","videoPrintscreen":"http://192.168.1.95:8090/upload/video/videoPic/e2229fc4d9054a02a37f10b9b1f36349.jpg","width":150},{"createTime":1492136110,"dynamicId":1,"dynamicsPath":"http://192.168.1.95:8090/upload/image/dynamicPic/d1.jpg","high":300,"nickName":"娴呮ⅵ娌睈","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d1.jpg","petId":1,"petName":"鐜嬪摬","petSex":0,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/12.jpg","videoPrintscreen":"http://192.168.1.95:8090","width":220},{"createTime":1492136111,"dynamicId":2,"dynamicsPath":"http://192.168.1.95:8090/upload/image/dynamicPic/d2.jpg","high":600,"nickName":"涓栦笂鍙湁涓�涓垜","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d2.jpg","petId":2,"petName":"鐜嬪摬","petSex":1,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/8.jpg","videoPrintscreen":"http://192.168.1.95:8090","width":600},{"createTime":1492136119,"dynamicId":10,"dynamicsPath":"http://192.168.1.95:8090/upload/image/dynamicPic/d10.jpg","high":600,"nickName":"娓呴鍚规垜瑗�","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d10.jpg","petId":10,"petName":"鐜嬪摬","petSex":0,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/8.jpg","videoPrintscreen":"http://192.168.1.95:8090","width":600},{"createTime":1492136118,"dynamicId":9,"dynamicsPath":"http://192.168.1.95:8090/upload/image/dynamicPic/d9.jpg","high":600,"nickName":"娓呴鍚规垜瑗�","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d9.jpg","petId":9,"petName":"鐜嬪摬","petSex":0,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/8.jpg","videoPrintscreen":"http://192.168.1.95:8090","width":600},{"createTime":0,"dynamicId":0,"dynamicsPath":"","high":0,"nickName":"娓呴鍚规垜瑗�","owner":0,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d8.jpg","petId":8,"petName":"鐜嬪摬","petSex":0,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/8.jpg","videoPrintscreen":"","width":0},{"createTime":0,"dynamicId":0,"dynamicsPath":"","high":0,"nickName":"鍗冮噷钀借姳椋�","owner":0,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d7.jpg","petId":7,"petName":"鐜嬪摬","petSex":0,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/7.jpg","videoPrintscreen":"","width":0},{"createTime":1492136115,"dynamicId":6,"dynamicsPath":"http://192.168.1.95:8090/upload/image/dynamicPic/d6.jpg","high":300,"nickName":"鏃跺厜灏忓伔","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d6.jpg","petId":6,"petName":"鐜嬪摬","petSex":0,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/6.jpg","videoPrintscreen":"http://192.168.1.95:8090","width":445},{"createTime":1492136112,"dynamicId":3,"dynamicsPath":"http://192.168.1.95:8090/upload/image/dynamicPic/d3.jpg","high":600,"nickName":"鍗冮噷钀借姳椋�","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d3.jpg","petId":3,"petName":"鐜嬪摬","petSex":0,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/11.jpg","videoPrintscreen":"http://192.168.1.95:8090","width":600},{"createTime":1492136113,"dynamicId":4,"dynamicsPath":"http://192.168.1.95:8090/upload/image/dynamicPic/d4.jpg","high":600,"nickName":"鎸侀厭鍔濇枩闃�","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d4.jpg","petId":4,"petName":"鐜嬪摬","petSex":0,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/10.jpg","videoPrintscreen":"http://192.168.1.95:8090","width":600},{"createTime":1492136114,"dynamicId":5,"dynamicsPath":"http://192.168.1.95:8090/upload/image/dynamicPic/d5.jpg","high":600,"nickName":"椋庢墤杩涙��","owner":1,"petHeadPortrait":"http://192.168.1.95:8090/upload/image/petPic/d5.jpg","petId":5,"petName":"鐜嬪摬","petSex":0,"type":0,"userHeadPortrait":"http://192.168.1.95:8090/upload/image/userPic/5.jpg","videoPrintscreen":"http://192.168.1.95:8090","width":600}]
     * pages : 1
     */

    private int pages;
    private List<FriendPraiseEntity> newPetList;

    public List<FriendPraiseEntity> getFriendPraiseList() {
        return friendPraiseList;
    }

    public void setFriendPraiseList(List<FriendPraiseEntity> friendPraiseList) {
        this.friendPraiseList = friendPraiseList;
    }

    private List<FriendPraiseEntity> friendPraiseList;

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<FriendPraiseEntity> getNewPetList() {
        return newPetList;
    }

    public void setNewPetList(List<FriendPraiseEntity> newPetList) {
        this.newPetList = newPetList;
    }
}
