package com.example.chun.doubanmovie.network;

import com.example.chun.doubanmovie.bean.Douban_top250;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Chun on 17/4/9.
 */

public interface DoubanApi {
    @GET("/v2/movie/top250")
    Observable<Douban_top250>getTop250(@Query("start") int start);
}
