package com.example.chun.doubanmovie.details;

import android.content.Context;
import android.util.Log;

import com.example.chun.doubanmovie.bean.Douban_Detail;
import com.example.chun.doubanmovie.mvp.BasePresenterImpl;
import com.example.chun.doubanmovie.network.Network;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DetailsPresenter extends BasePresenterImpl<DetailsContract.View> implements DetailsContract.Presenter{
    private static final String TAG = "DetailsPresenter";


    @Override
    public void loadDetail(int id) {
        Observer<Douban_Detail> mObserver=new Observer<Douban_Detail>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
                mView.showError();
            }

            @Override
            public void onNext(Douban_Detail douban_detail) {
                String title=douban_detail.getTitle();
                Log.d(TAG, "onNext: "+title);
                String imageUrl=douban_detail.getImages().getLarge();
                Log.d(TAG, "onNext: "+imageUrl);
                String content=douban_detail.getSummary();
                Log.d(TAG, "onNext: "+content);
                mView.loadData(title,imageUrl,content);
            }
        };

        Network.getDoubanApi()
                .getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);

        Log.d(TAG, "loadDetail: ");
    }
}
