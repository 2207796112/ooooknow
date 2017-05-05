package com.ooooknow.flove.byxy.ooooknow.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.model.NewsComments;

import java.util.List;

/**
 * Created by hcc on 16/4/23 12:36
 * 100332338@qq.com
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MasonryView> {

    private List<NewsComments.CommentsBean> list;

    public CommentAdapter(List<NewsComments.CommentsBean> list) {

        this.list = list;
    }


    @Override
    public MasonryView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        return new MasonryView(view);
    }


    @Override
    public void onBindViewHolder(MasonryView holder, int position) {
/*        holder.news_title.setText(list.get(position).getTitle());
        FrescoImageLoader f = new FrescoImageLoader();

        Uri uri = Uri.parse(list.get(position).getImages().get(0));
        holder.news_first.setImageURI(uri);*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MasonryView extends RecyclerView.ViewHolder {
        public SimpleDraweeView mUserPic;

        public TextView mUserName;

        public TextView mUserPariseNum;

        public TextView mUserContent;

        public TextView mUserTime;

        public MasonryView(View view) {
            super(view);
            mUserPic = (SimpleDraweeView) itemView.findViewById(R.id.user_pic);
            mUserName = (TextView) itemView.findViewById(R.id.user_name);
            mUserPariseNum = (TextView) itemView.findViewById(R.id.user_parise_num);
            mUserContent = (TextView) itemView.findViewById(R.id.user_content);
            mUserTime = (TextView) itemView.findViewById(R.id.user_time);
        }
    }
}



