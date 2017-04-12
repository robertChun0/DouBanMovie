package com.example.chun.doubanmovie.doubancoming;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chun.doubanmovie.R;
import com.example.chun.doubanmovie.adapter.DoubanAdapter;
import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.details.DetailsActivity;
import com.example.chun.doubanmovie.interfaze.OnItemClickListener;
import com.example.chun.doubanmovie.mvp.MVPBaseFragment;
import com.example.chun.doubanmovie.util.ToastUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DoubancomingFragment extends MVPBaseFragment<DoubancomingContract.View, DoubancomingPresenter> implements DoubancomingContract.View {


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    public DoubanAdapter mDoubanAdapter;
    private static final String TAG = "DoubancomingFragment";
    public int lastVisibleItem=0;
    private static boolean state=true;
    private static boolean isInit=false;
    private static DoubancomingFragment doubancomingFragment=new DoubancomingFragment();
    private DoubancomingFragment() {
    }


    public static DoubancomingFragment getInstance(){
        return doubancomingFragment;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) //懒加载
        {
            if(!isInit) {
                refresh();
                isInit=true;
            }
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.content_main, container, false);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });

        refreshLayout.setColorSchemeResources(R.color.refresh_1,R.color.refresh_2,R.color.refresh_3);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            boolean isSlidingToLast = false;
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState==RecyclerView.SCROLL_STATE_IDLE) {
                    int totalItemCount = layoutManager.getItemCount();
                    int lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                    if (lastVisibleItem == (totalItemCount - 1) && isSlidingToLast&&state) {
                        mPresenter.loadMovie();
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                isSlidingToLast = dy > 0;
            }
        });



    }

    @Override
    public void refresh() {
        refreshLayout.setRefreshing(true);
        mPresenter.loadMovie();
    }

    @Override
    public void showError() {
        refreshLayout.setRefreshing(false);
        Snackbar.make(getView(),R.string.error,Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh();
                    }
                })
                .show();
        setLoadState();
    }

    @Override
    public void showNoMore() {
        ToastUtil.showToast(getContext(),R.string.no_more);
        if (refreshLayout.isRefreshing()){
            refreshLayout.setRefreshing(false);
        }
    }


    @Override
    public void loadMovie(List<Douban_top250.SubjectsBean> datas) {
        refreshLayout.setRefreshing(false);
        if(mDoubanAdapter==null){
            mDoubanAdapter=new DoubanAdapter(datas);
            mDoubanAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void OnItemClick(View view, int position) {
                    Intent intent=new Intent(getContext(),DetailsActivity.class);
                    intent.putExtra("movie_id",mDoubanAdapter.datas.get(position).getId());
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(mDoubanAdapter);
        }else{
            mDoubanAdapter.datas.addAll(datas);
            mDoubanAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void removeFootItem() {
        mDoubanAdapter.notifyItemRemoved(mDoubanAdapter.getItemCount());
    }

    @Override
    public void setLoadState() {
        state=!state;
    }
}
