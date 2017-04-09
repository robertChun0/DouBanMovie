package com.example.chun.doubanmovie.douban;

import android.util.Log;
import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.mvp.BasePresenterImpl;
import com.example.chun.doubanmovie.network.Network;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class DoubanPresenter extends BasePresenterImpl<DoubanContract.View> implements DoubanContract.Presenter{
    private static final String TAG = "DoubanPresenter";
    private static int number=-20;
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
            }
        };
        number+=20;
        Network.getDoubanApi().getTop250(number)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
