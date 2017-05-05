package com.ooooknow.flove.byxy.ooooknow.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.model.NewsInfo;

import java.util.ArrayList;

/**
 * Created by jianghejie on 15/11/26.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    public ArrayList<NewsInfo> datas = null;

    public NewsAdapter(ArrayList<NewsInfo> datas) {
        this.datas = datas;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_list_item, viewGroup, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.news_title.setText(datas.get(position).getNews_title());
        viewHolder.news_content.setText(datas.get(position).getNews_content());
        ImageLoader.getInstance().displayImage(datas.get(position).getNews_first(),viewHolder.news_first);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView news_title;
        public TextView news_content;
        public ImageView news_first;

        public ViewHolder(View view) {
            super(view);
            news_title = (TextView) view.findViewById(R.id.news_tv_title);
            news_content = (TextView) view.findViewById(R.id.news_tv_content);
            news_first = (ImageView) view.findViewById(R.id.news_img);

        }
    }
}
