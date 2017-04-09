package com.example.chun.doubanmovie.douban;

import android.content.Context;

import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.mvp.BasePresenter;
import com.example.chun.doubanmovie.mvp.BaseView;

import java.util.List;


public class DoubanContract {
    interface View extends BaseView {
       public void initView();

        public void refresh();

        public void showError();

        public void loadMovie(List<Douban_top250.SubjectsBean> datas);
    }

    interface  Presenter extends BasePresenter<View> {
        public void loadMovie();
    }
}
