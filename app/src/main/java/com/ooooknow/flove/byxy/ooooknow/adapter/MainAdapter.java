package com.ooooknow.flove.byxy.ooooknow.adapter;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ooooknow.flove.byxy.ooooknow.FrescoImageLoader;
import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.model.NewsList;

import java.util.List;

/**
 * Created by limxing on 16/7/23.
 * <p>
 * https://github.com/limxing
 * Blog: http://www.leefeng.me
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MasonryView> {

    private List<NewsList.StoriesBean> list;

    public MainAdapter(List<NewsList.StoriesBean> list) {

        this.list = list;
    }

    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item2, parent, false);
        return new MasonryView(view);
    }

    public void updateData(List<NewsList.StoriesBean> list) {

        this.list = list;
        notifyDataSetChanged();
    }

    public void addData(List<NewsList.StoriesBean> list) {

        if (this.list == null) {
            updateData(list);
        } else {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
        holder.news_title.setText(list.get(position).getTitle());
        FrescoImageLoader f = new FrescoImageLoader();

        Uri uri = Uri.parse(list.get(position).getImages().get(0));
        holder.news_first.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MasonryView extends RecyclerView.ViewHolder {
        public TextView news_title;
        public TextView news_content;
        public SimpleDraweeView news_first;

        public MasonryView(View view) {
            super(view);
            news_title = (TextView) view.findViewById(R.id.news_tv_title);
            news_content = (TextView) view.findViewById(R.id.news_tv_content);
            news_first = (SimpleDraweeView) view.findViewById(R.id.news_img);

        }


    }
}



