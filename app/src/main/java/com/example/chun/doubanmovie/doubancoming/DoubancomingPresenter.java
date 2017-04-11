package com.example.chun.doubanmovie.doubancoming;

import android.util.Log;

import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.mvp.BasePresenterImpl;
import com.example.chun.doubanmovie.network.Network;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DoubancomingPresenter extends BasePresenterImpl<DoubancomingContract.View> implements DoubancomingContract.Presenter{
    private static final String TAG = "DoubancomingPresenter";
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
                mView.loadMovie(douban_top250.getSubjects());
                number+=20;
                total=douban_top250.getTotal();
            }
        };
        if(number<total){
            Network.getDoubanApi().getComingSoon(number)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        }else {
            mView.showNoMore();
            mView.removeFootItem();
            Log.d(TAG, "loadMovie: no more" );
        }
    }
}
