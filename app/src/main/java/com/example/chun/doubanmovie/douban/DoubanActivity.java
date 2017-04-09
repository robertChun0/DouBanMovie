package com.example.chun.doubanmovie.douban;


import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.example.chun.doubanmovie.adapter.DoubanAdapter;
import com.example.chun.doubanmovie.R;
import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.mvp.MVPBaseActivity;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DoubanActivity extends MVPBaseActivity<DoubanContract.View, DoubanPresenter> implements DoubanContract.View, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    public DoubanAdapter mDoubanAdapter;
    public int lastVisibleItem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();


    }

    @Override
    public void initView() {
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        recyclerView.setHasFixedSize(true);

        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
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

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if ((newState==RecyclerView.SCROLL_STATE_IDLE)
                        &&(lastVisibleItem+1)==mDoubanAdapter.getItemCount())
                {
                    mPresenter.loadMovie();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem= layoutManager.findLastCompletelyVisibleItemPosition();
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
        Toast.makeText(this,R.string.error,Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this,R.string.success,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
