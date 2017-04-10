package com.example.chun.doubanmovie.details;

import android.content.Context;

import com.example.chun.doubanmovie.mvp.BasePresenter;
import com.example.chun.doubanmovie.mvp.BaseView;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public class DetailsContract {
    interface View extends BaseView {
        void initView();

        void loadData(String title,String imageUrl,String content);
    }

    interface  Presenter extends BasePresenter<View> {

        void loadDetail(int id);
    }
}
