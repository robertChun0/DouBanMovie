package com.example.chun.doubanmovie.doubantop250;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.chun.doubanmovie.R;
import com.example.chun.doubanmovie.adapter.DoubanAdapter;
import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.mvp.MVPBaseFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Doubantop250Fragment extends MVPBaseFragment<Doubantop250Contract.View, Doubantop250Presenter> implements Doubantop250Contract.View {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    public DoubanAdapter mDoubanAdapter;
    public int lastVisibleItem=0;
    private static Doubantop250Fragment doubantop250Fragment=new Doubantop250Fragment();
    private Doubantop250Fragment() {
    }


    public static Doubantop250Fragment getInstance(){
        return doubantop250Fragment;
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

        refreshLayout.setRefreshing(true);

        mPresenter.loadMovie();

        if(mDoubanAdapter!=null) {
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if ((newState == RecyclerView.SCROLL_STATE_IDLE)
                            && (lastVisibleItem + 1) == mDoubanAdapter.getItemCount()) {
                        mPresenter.loadMovie();
                    }
                }

                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition();
                }
            });
        }


    }

    @Override
    public void refresh() {
        refreshLayout.setRefreshing(true);
        mPresenter.loadMovie();
    }

    @Override
    public void showError() {
        refreshLayout.setRefreshing(false);
        Toast.makeText(getContext(),R.string.error,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadMovie(List<Douban_top250.SubjectsBean> datas) {
        refreshLayout.setRefreshing(false);
        if(mDoubanAdapter==null){
            mDoubanAdapter=new DoubanAdapter(datas);
            recyclerView.setAdapter(mDoubanAdapter);
        }else{
            mDoubanAdapter.datas.addAll(datas);
            mDoubanAdapter.notifyDataSetChanged();
        }
        Toast.makeText(getContext(),R.string.success,Toast.LENGTH_SHORT).show();
    }
}
