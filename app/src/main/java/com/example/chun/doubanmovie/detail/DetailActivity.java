//package com.example.chun.doubanmovie.detail;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.chun.doubanmovie.R;
//
///**
// * Created by Chun on 17/4/10.
// */
//
//public class DetailActivity extends AppCompatActivity {
//    private static final String TAG = "DetailActivity";
//    private DetailFragment detailFragment;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail);
//        detailFragment = DetailFragment.getInstance();
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.container, detailFragment)
//                .commit();
//
//        Intent intent=getIntent();
//        String id=intent.getStringExtra("movie_id");
//        if(id==null){
//            Toast.makeText(this,"获取id失败",Toast.LENGTH_SHORT).show();
//        }else {
//            Log.d(TAG, "onCreate: "+ id);
//            int Id=Integer.parseInt(id);
//            detailFragment.mPresenter.loadDetail(Id);
//        }
//
//    }
//}
