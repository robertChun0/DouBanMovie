//package com.example.chun.doubanmovie.detail;
//
//
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.design.widget.AppBarLayout;
//import android.support.design.widget.CollapsingToolbarLayout;
//import android.support.design.widget.CoordinatorLayout;
//import android.support.v4.widget.NestedScrollView;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.example.chun.doubanmovie.R;
//import com.example.chun.doubanmovie.mvp.MVPBaseFragment;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * MVPPlugin
// * 邮箱 784787081@qq.com
// */
//
//public class DetailFragment extends MVPBaseFragment<DetailContract.View, DetailPresenter> implements DetailContract.View {
//
//    @BindView(R.id.detail_image)
//    ImageView detailImage;
//    @BindView(R.id.detail_toolbar)
//    Toolbar detailToolbar;
//    @BindView(R.id.toolbarLayout)
//    CollapsingToolbarLayout toolbarLayout;
//    @BindView(R.id.appBar)
//    AppBarLayout appBar;
//    @BindView(R.id.detail_text)
//    TextView detailText;
//    @BindView(R.id.nestedScrollView)
//    NestedScrollView nestedScrollView;
//    @BindView(R.id.coordinatorlayout)
//    CoordinatorLayout coordinatorlayout;
//    private static final String TAG = "DetailFragment";
//
//    private DetailFragment(){
//
//    }
//
//    private static DetailFragment mDetailFragment;
//
//
//    public static DetailFragment getInstance(){
//        if (mDetailFragment==null){
//            return new DetailFragment();
//        }
//        else {
//            return mDetailFragment;
//        }
//    }
//
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_detail, container, false);
//        ButterKnife.bind(this, view);
//        initView();
//        return view;
//    }
//
//
//    @Override
//    public void initView() {
//        DetailActivity activity = (DetailActivity) getActivity();
//        activity.setSupportActionBar(detailToolbar);
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        setHasOptionsMenu(true);
//    }
//
//    @Override
//    public void loadData(String title, String imageUrl, String content) {
//        Log.d(TAG, "loadData: ");
//        detailToolbar.setTitle(title);
//        Glide.with(getContext()).load(imageUrl).into(detailImage);
//        detailText.setText(content);
//    }
//
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case android.R.id.home:
//                getActivity().onBackPressed();
//                break;
//            default:
//                break;
//        }
//        return true;
//    }
//}
