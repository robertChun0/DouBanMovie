package com.example.chun.doubanmovie.doubantop250;

import android.util.Log;

import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.mvp.BasePresenterImpl;
import com.example.chun.doubanmovie.network.Network;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class Doubantop250Presenter extends BasePresenterImpl<Doubantop250Contract.View> implements Doubantop250Contract.Presenter{

    private static final String TAG = "Doubantop250Presenter";
    private static int number=0;
    private static int total=1;//避免一开始无法加载
    @Override
    public void loadMovie() {
        Observer<Douban_top250> observer=new Observer<Douban_top250>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                mView.showError();
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(Douban_top250 douban_top250) {
                Log.d(TAG, "onNext: ");
                mView.loadMovie(douban_top250.getSubjects());
                number+=20;
                total=douban_top250.getTotal();
                Log.d(TAG, "onNext: "+"total="+total+"   number="+number);
            }
        };

        if(number<total){
        Network.getDoubanApi()
                .getTop250(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        }else {
            mView.showNoMore();
            mView.removeFootItem();
        }

    }
}
