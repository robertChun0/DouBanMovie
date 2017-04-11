package com.example.chun.doubanmovie.details;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.chun.doubanmovie.R;
import com.example.chun.doubanmovie.mvp.MVPBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class DetailsActivity extends MVPBaseActivity<DetailsContract.View, DetailsPresenter> implements DetailsContract.View {


    @BindView(R.id.detail_image)
    ImageView detailImage;
    @BindView(R.id.toolbar)
    Toolbar detailToolbar;
    @BindView(R.id.toolbarLayout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.detail_text)
    TextView detailText;
    @BindView(R.id.nestedScrollView)
    NestedScrollView nestedScrollView;
    @BindView(R.id.coordinatorlayout)
    CoordinatorLayout coordinatorlayout;
    private static final String TAG = "DetailsActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        initView();


        Intent intent=getIntent();
        String id=intent.getStringExtra("movie_id");
        if(id==null){
            Toast.makeText(this,"获取id失败",Toast.LENGTH_SHORT).show();
        }else {
            Log.d(TAG, "onCreate: "+ id);
            int Id=Integer.parseInt(id);
            mPresenter.loadDetail(Id);
        }
    }

    @Override
    public void initView() {
        setSupportActionBar(detailToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return true;
    }


    @Override
    public void loadData(String title, String imageUrl, String content) {
        toolbarLayout.setTitle(title);
        Glide.with(getContext()).load(imageUrl).into(detailImage);
        detailText.setText(content);
    }
}
