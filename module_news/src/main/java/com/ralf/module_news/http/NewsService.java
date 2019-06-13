package com.ralf.module_news.http;

import com.google.gson.JsonObject;
import com.ralf.module_news.entity.CollectionEntity;
import com.ralf.module_news.entity.NewsDetailEntity;
import com.ralf.module_news.entity.NewsResultEntity;
import com.ralf.pet_provider.http.BaseEntity;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name NewsService
 * @email -
 * @date 2019/05/15 19:57
 **/
public interface NewsService {

    /**
     * 请求推荐数据
     *
     * @param object 参数
     * @return
     */
    @POST("api/news/recommendList")
    Observable<BaseEntity<NewsResultEntity>> getRecommendData(@Body JsonObject object);

    /**
     * 资讯详情
     *
     * @param object 参数
     * @return
     */
    @POST("api/news/articleContent")
    Observable<BaseEntity<NewsDetailEntity>> getNewsDetailData(@Body JsonObject object);

    /**
     * 资讯收藏
     *
     * @param object 参数
     * @return
     */
    @POST("api/news/articleWish")
    Observable<BaseEntity<CollectionEntity>> requestCollection(@Body JsonObject object);
}
