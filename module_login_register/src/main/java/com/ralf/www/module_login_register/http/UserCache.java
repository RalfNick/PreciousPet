package com.ralf.www.module_login_register.http;

import java.util.concurrent.TimeUnit;

import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

/**
 * @author Ralf(wanglixin)
 * DESCRIPTION
 * @name UserCache
 * @email -
 * @date 2018/12/10 下午2:23
 **/
public interface UserCache {

//    /**
//     * 缓存课程信息
//     * @param courseInfo
//     * @param idLastQueried
//     * @param evictProvider
//     * @return
//     */
//    @LifeCache(duration = 5, timeUnit = TimeUnit.MINUTES)
//    Observable<Reply<CourseInfo>> getCourseInfo(Observable<CourseInfo> courseInfo,
//                                                DynamicKey idLastQueried,
//                                                EvictProvider evictProvider);
}
