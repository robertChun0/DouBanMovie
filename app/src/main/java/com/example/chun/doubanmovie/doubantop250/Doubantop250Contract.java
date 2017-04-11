package com.example.chun.doubanmovie.doubantop250;


import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.mvp.BasePresenter;
import com.example.chun.doubanmovie.mvp.BaseView;

import java.util.List;


public class Doubantop250Contract {
    interface View extends BaseView {
        public void initView();

        public void refresh();

        public void showError();

        public void showNoMore();

        public void loadMovie(List<Douban_top250.SubjectsBean> datas);

        public void removeFootItem();

        public void setLoadState();
    }

    interface  Presenter extends BasePresenter<Doubantop250Contract.View> {
        public void loadMovie();
    }
}
