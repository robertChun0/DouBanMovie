//package com.example.chun.doubanmovie.detail;
//
//import android.content.Context;
//import android.util.Log;
//
//import com.example.chun.doubanmovie.bean.Douban_Detail;
//import com.example.chun.doubanmovie.mvp.BasePresenterImpl;
//import com.example.chun.doubanmovie.network.Network;
//
//import rx.Observer;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * MVPPlugin
// *  邮箱 784787081@qq.com
// */
//
//public class DetailPresenter extends BasePresenterImpl<DetailContract.View> implements DetailContract.Presenter{
//
//
//    private String title;
//    private String imageUrl;
//    private String content;
//    private static final String TAG = "DetailPresenter";
//    public DetailPresenter() {
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public void setContent(String content) {
//        this.content = content;
//    }
//
//    @Override
//    public void loadData() {
//        mView.loadData(title,imageUrl,content);
//
//    }
//
//    @Override
//    public void loadDetail(int id) {
//
//        Observer<Douban_Detail>mObserver=new Observer<Douban_Detail>() {
//            @Override
//            public void onCompleted() {
//                Log.d(TAG, "onCompleted: ");
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: ");
//            }
//
//            @Override
//            public void onNext(Douban_Detail douban_detail) {
//                String title=douban_detail.getTitle();
//                Log.d(TAG, "onNext: "+title);
//                String imageUrl=douban_detail.getImages().getLarge();
//                Log.d(TAG, "onNext: "+imageUrl);
//                String content=douban_detail.getSummary();
//                Log.d(TAG, "onNext: "+content);
//                mView.loadData(title,imageUrl,content);
//            }
//        };
//
//        Network.getDoubanApi()
//                .getMovieDetail(id)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(mObserver);
//
//        Log.d(TAG, "loadDetail: ");
//    }
//
//
//}
