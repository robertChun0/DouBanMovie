package com.example.chun.doubanmovie.adapter;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chun.doubanmovie.R;
import com.example.chun.doubanmovie.bean.Douban_top250;
import com.example.chun.doubanmovie.interfaze.OnItemClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Chun on 17/4/9.
 */

public class DoubanAdapter extends RecyclerView.Adapter{
    public List<Douban_top250.SubjectsBean> datas;
    private Context mContext;
    private static final int TYPE_NORMAL=1;
    private static final int TYPE_FOOT=0;
    private OnItemClickListener mOnItemClickListener;
    public DoubanAdapter(List<Douban_top250.SubjectsBean> datas){
        this.datas=datas;
    }



    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener){
        this.mOnItemClickListener=mOnItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext=parent.getContext();
        switch (viewType){
            case TYPE_NORMAL:
            {
                View view= LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
                return new DoubanViewHolder(view,mOnItemClickListener);
            }
            case TYPE_FOOT: {
                View view = LayoutInflater.from(mContext).inflate(R.layout.foot_item, parent, false);
                return new FootViewHolder(view);
            }
        }
        View view= LayoutInflater.from(mContext).inflate(R.layout.item,parent,false);
        return new DoubanViewHolder(view,mOnItemClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof DoubanViewHolder) {
            DoubanViewHolder doubanViewHolder = (DoubanViewHolder) holder;
            Douban_top250.SubjectsBean subjectsBean = datas.get(position);


            Glide.with(mContext)
                    .load(subjectsBean.getImages()
                            .getLarge())
                    .error(R.mipmap.ic_launcher)
                    .into((doubanViewHolder).mImageView);


            String title = "电影名: ";
            doubanViewHolder.title.setText(title + subjectsBean.getTitle());


            StringBuffer sb = new StringBuffer();
            sb.append("演员表: ");
            for (Douban_top250.SubjectsBean.CastsBean castsBean : subjectsBean.getCasts()) {
                sb.append(castsBean.getName().toString()).append("/");
            }
            sb.deleteCharAt(sb.length() - 1);
            doubanViewHolder.casts.setText(sb.toString());


            sb = new StringBuffer();
            sb.append("类型: ");
            for (int i = 0; i < subjectsBean.getGenres().size(); i++) {
                sb.append(subjectsBean.getGenres().get(i).toString()).append("/");
            }
            sb.deleteCharAt(sb.length() - 1);
            doubanViewHolder.genres.setText(sb.toString());


            String rating = "评分: ";
            doubanViewHolder.rating.setText(rating + String.valueOf(subjectsBean.getRating().getAverage()));

        }
    }


    @Override
    public int getItemViewType(int position) {
        if((position+1)==getItemCount()){
            return TYPE_FOOT;
        }
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size()+1;
    }

    static class FootViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.progressBar)
        ProgressBar mProgressBar;
        public FootViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    static class DoubanViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.cardView)
        CardView mCardView;
        @BindView(R.id.imageView)
        ImageView mImageView;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.casts)
        TextView casts;
        @BindView(R.id.genres)
        TextView genres;
        @BindView(R.id.rating)
        TextView rating;
        public DoubanViewHolder(View itemView, final OnItemClickListener mOnItemClickListener) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(mOnItemClickListener!=null)
                    mOnItemClickListener.OnItemClick(view,getLayoutPosition());
                }
            });
        }
    }
}

