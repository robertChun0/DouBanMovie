package com.example.chun.doubanmovie.doubancoming;

import android.content.Context;

import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.doubantop250.Doubantop250Contract;
import com.example.chun.doubanmovie.mvp.BasePresenter;
import com.example.chun.doubanmovie.mvp.BaseView;

import java.util.List;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DoubancomingContract {
    interface View extends BaseView {
        public void initView();

        public void refresh();

        public void showError();

        public void showNoMore();

        public void loadMovie(List<Douban_top250.SubjectsBean> datas);

        public void removeFootItem();
    }

    interface  Presenter extends BasePresenter<DoubancomingContract.View> {
        public void loadMovie();
    }
}
