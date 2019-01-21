package com.ralf.pet_provider.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name DownloadPicService
 * @email -
 * @date 2019/01/18 下午4:23
 **/
public interface DownloadPicService {

    /**
     * 获取图片数据
     *
     * @param url 地址
     * @return 图片
     */
    @GET
    Observable<ResponseBody> downLoadPicture(@Url String url);
}
