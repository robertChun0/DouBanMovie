package com.example.chun.doubanmovie.details;


import com.example.chun.doubanmovie.mvp.BasePresenter;
import com.example.chun.doubanmovie.mvp.BaseView;


public class DetailsContract {
    interface View extends BaseView {
        void initView();

        void loadData(String title,String imageUrl,String content);

        void showError();
    }

    interface  Presenter extends BasePresenter<View> {

        void loadDetail(int id);
    }
}
