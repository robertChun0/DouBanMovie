package com.example.chun.doubanmovie.mvp;


public interface  BasePresenter <V extends BaseView>{
    void attachView(V view);

    void detachView();
}
