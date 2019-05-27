package com.ralf.module_news.entity;

import java.util.List;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsResultEntity
 * @email -
 * @date 2019/05/16 17:44
 **/
public class NewsResultEntity {

    /**
     * bannerList : [{"title":"鐚负浠�涔堝枩娆㈣汉鍦ㄨ姳鐩嗛噷","imgUrl":"http://192.168.1.139:8090/upload/image/carouselPic/14907798156721.jpg","id":1},{"title":"鐚负浠�涔堝枩娆㈣汉鍦ㄨ姳鐩嗛噷","imgUrl":"http://192.168.1.139:8090/upload/image/carouselPic/14907798308232.jpg","id":2},{"title":"鐚负浠�涔堝枩娆㈣汉鍦ㄨ姳鐩嗛噷","imgUrl":"http://192.168.1.139:8090/upload/image/carouselPic/14907798350083.jpg","id":3},{"title":"鐚负浠�涔堝枩娆㈣汉鍦ㄨ姳鐩嗛噷","imgUrl":"http://192.168.1.139:8090/upload/image/carouselPic/14907798388874.jpg","id":4},{"title":"鐚负浠�涔堝枩娆㈣汉鍦ㄨ姳鐩嗛噷","imgUrl":"http://192.168.1.139:8090/upload/image/carouselPic/14907798426565.jpg","id":5}]
     * pages : 1
     * recommendList : [{"articleId":1,"title":"涓虹嫍鐙楁墦椋炴満鎬庝箞灏辨垚浜嗘棩甯哥敓娲�","top":1,"featured":1,"author":"鐜嬩簩鐙�01","viewTimes":22222,"createTime":"1490837280","img":"http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg","type":0},{"articleId":2,"title":"涓虹嫍鐙楁墦椋炴満鎬庝箞灏辨垚浜嗘棩甯哥敓娲�","top":1,"featured":1,"author":"鐜嬩簩鐙�11","viewTimes":22222,"createTime":"1490837280","img":"http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg","type":0},{"articleId":3,"title":"涓虹嫍鐙楁墦椋炴満鎬庝箞灏辨垚浜嗘棩甯哥敓娲�","top":1,"featured":1,"author":"鐜嬩簩鐙�21","viewTimes":22222,"createTime":"1490837280","type":1,"img":"http://192.168.1.139:8090/upload/video/videoPic/1490776077081video.jpg"},{"articleId":4,"title":"涓虹嫍鐙楁墦椋炴満鎬庝箞灏辨垚浜嗘棩甯哥敓娲�","top":1,"featured":1,"author":"鐜嬩簩鐙�31","viewTimes":22222,"createTime":"1490837280","img":"http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg","type":0},{"articleId":5,"title":"涓嶅吇鐙楃殑浣狅紝涓�瀹氭棤娉曠粡鍘嗚繖浜�","top":0,"featured":0,"picTotal":9,"viewTimes":9,"author":"鐜嬩簲","picList":[{"picId":1,"picUrl":"http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg"},{"picId":2,"picUrl":"http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg"},{"picId":3,"picUrl":"http://192.168.1.139:8090/upload/image/petPic/1490779318421dog.jpg"}]}]
     */

    private int pages;
    private List<BannerEntity> bannerList;
    private List<NewsEntity> recommendList;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public List<BannerEntity> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<BannerEntity> bannerList) {
        this.bannerList = bannerList;
    }

    public List<NewsEntity> getRecommendList() {
        return recommendList;
    }

    public void setRecommendList(List<NewsEntity> recommendList) {
        this.recommendList = recommendList;
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "pages=" + pages +
                ", bannerList=" + bannerList +
                ", recommendList=" + recommendList +
                '}';
    }
}
