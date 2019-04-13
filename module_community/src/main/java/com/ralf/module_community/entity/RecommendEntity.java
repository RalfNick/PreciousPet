package com.ralf.module_community.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name RecommendEntity
 * @email -
 * @date 2019/04/13 下午1:18
 **/
public class RecommendEntity {

    /**
     * code : 0
     * data : {"beauty":[{"id":42,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/10.jpg","isVideo":0,"type":0},
     * {"id":43,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/14.jpg","isVideo":0,"type":0},
     * {"id":44,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/14.jpg","isVideo":0,"type":0}],
     * "charm":[{"id":2,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/2.jpg","isVideo":0,"type":0},
     * {"id":1,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/1.jpg","isVideo":0,"type":0},
     * {"id":6,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/6.jpg","isVideo":0,"type":0},
     * {"id":10,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/10.jpg","isVideo":0,"type":0}],
     * "friend":[{"id":9,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/d9.jpg","isVideo":0,"type":1},
     * {"id":6,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/d6.jpg","isVideo":0,"type":1},
     * {"id":4,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/d4.jpg","isVideo":0,"type":1},
     * {"id":3,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/d3.jpg","isVideo":0,"type":1}],
     * "latest":[{"id":245,"imgUrl":"http://222.161.184.10:8088/upload/video/videoPic/1f103c8c8ba3477693d4c8b02ab27e26.jpg",
     * "isVideo":1,"type":1},{"id":244,"imgUrl":"http://222.161.184.10:8088/upload/video/videoPic/1f103c8c8ba3477693d4c8b02ab27e26.jpg","isVideo":1,"type":1},
     * {"id":243,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/ead996fbdace4d55a18846a98c53217a.jpg","isVideo":0,"type":1},
     * {"id":242,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/5e2d0ff3e514405db8d662421033da4b.jpg","isVideo":0,"type":1}],
     * "nearPet":[{"id":39,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/h5.jpg","isVideo":0,"type":1},
     * {"id":34,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/h1.jpg","isVideo":0,"type":1},
     * {"id":35,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/h2.jpg","isVideo":0,"type":1}],
     * "newPet":[{"id":16,"imgUrl":"http://222.161.184.10:8088/upload/video/videoPic/1490776077081video.jpg",
     * "isVideo":1,"type":1},{"id":17,"imgUrl":"http://222.161.184.10:8088/upload/video/videoPic/1490776077081video.jpg",
     * "isVideo":1,"type":1},{"id":15,"imgUrl":"http://222.161.184.10:8088/upload/video/videoPic/1490776077081video.jpg",
     * "isVideo":1,"type":1},{"id":12,"imgUrl":"http://222.161.184.10:8088/upload/video/videoPic/e2229fc4d9054a02a37f10b9b1f36349.jpg","isVideo":1,"type":1}],
     * "praise":[{"id":1,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/d1.jpg","isVideo":0,"type":1},{"id":2,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/d2.jpg",
     * "isVideo":0,"type":1},{"id":3,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/d3.jpg","isVideo":0,"type":1},{"id":4,"imgUrl":"http://222.161.184.10:8088/upload/image/dynamicPic/d4.jpg",
     * "isVideo":0,"type":1}],"starAndReds":[{"id":1,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/12.jpg","isVideo":0,"type":0},{"id":2,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/8.jpg","isVideo":0,"type":0},
     * {"id":3,"imgUrl":"http://222.161.184.10:8088/upload/image/userPic/11.jpg","isVideo":0,"type":0}]}
     * message : 鎴愬姛
     */
    private List<InnerBean> beauty;
    private List<InnerBean> charm;
    private List<InnerBean> friend;
    private List<InnerBean> latest;
    private List<InnerBean> nearPet;
    private List<InnerBean> newPet;
    private List<InnerBean> praise;
    private List<InnerBean> starAndReds;

    public List<InnerBean> getBeauty() {
        return beauty;
    }

    public void setBeauty(List<InnerBean> beauty) {
        this.beauty = beauty;
    }

    public List<InnerBean> getCharm() {
        return charm;
    }

    public void setCharm(List<InnerBean> charm) {
        this.charm = charm;
    }

    public List<InnerBean> getFriend() {
        return friend;
    }

    public void setFriend(List<InnerBean> friend) {
        this.friend = friend;
    }

    public List<InnerBean> getLatest() {
        return latest;
    }

    public void setLatest(List<InnerBean> latest) {
        this.latest = latest;
    }

    public List<InnerBean> getNearPet() {
        return nearPet;
    }

    public void setNearPet(List<InnerBean> nearPet) {
        this.nearPet = nearPet;
    }

    public List<InnerBean> getNewPet() {
        return newPet;
    }

    public void setNewPet(List<InnerBean> newPet) {
        this.newPet = newPet;
    }

    public List<InnerBean> getPraise() {
        return praise;
    }

    public void setPraise(List<InnerBean> praise) {
        this.praise = praise;
    }

    public List<InnerBean> getStarAndReds() {
        return starAndReds;
    }

    public void setStarAndReds(List<InnerBean> starAndReds) {
        this.starAndReds = starAndReds;
    }

    public static class InnerBean {
        /**
         * id : 42
         * imgUrl : http://222.161.184.10:8088/upload/image/userPic/10.jpg
         * isVideo : 0
         * type : 0
         */

        private int id;
        private String imgUrl;
        private int isVideo;
        private int type;
        private String title;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getIsVideo() {
            return isVideo;
        }

        public void setIsVideo(int isVideo) {
            this.isVideo = isVideo;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
