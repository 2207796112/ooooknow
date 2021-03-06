package com.ooooknow.flove.byxy.ooooknow.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.model.AnswerInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by co-mall on 2016/9/13.
 */
public class RecommendedAdapter extends RecyclerView.Adapter<RecommendedAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;

    private List<AnswerInfo> datas = new ArrayList<>();
    private Context mContext;

    private int menuW, menuH;

    public RecommendedAdapter(Context mContext, List<AnswerInfo> datas) {
        this.datas = datas;
        this.mContext = mContext;
        DisplayMetrics display = new DisplayMetrics();
        Activity mActivity = (Activity) mContext;
        mActivity.getWindowManager().getDefaultDisplay().getMetrics(display);
        menuW = display.widthPixels / 2;
        menuH = LinearLayout.LayoutParams.WRAP_CONTENT;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (headView != null && viewType == TYPE_HEADER) {
            return new MyViewHolder(headView);
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.answer_list_item, null);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "a" + position, Toast.LENGTH_SHORT).show();
            }
        });

        Uri uri = Uri.parse( datas.get(position).getAnswerUserhead());
        holder.userhead.setImageURI(uri);
        holder.username.setText(datas.get(position).getAnswerUsername());
        holder.time.setText(datas.get(position).getAnswerTime());
        holder.title.setText(datas.get(position).getAnswerTitle());
        holder.focus.setText(datas.get(position).getAnswerFocus());
        holder.answer.setText(datas.get(position).getAnswerAnswer());
        holder.tag.setText(datas.get(position).getAnswerTag());
    }

    @Override
    public int getItemViewType(int position) {
        if (headView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return headView == null ? datas.size() : datas.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView userhead;
        TextView username;
        TextView time;
        TextView title;
        TextView focus;
        TextView answer;
        TextView tag;



        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headView) return;
            userhead = (SimpleDraweeView) itemView.findViewById(R.id.answer_sdv_userhead);
            username = (TextView) itemView.findViewById(R.id.answer_tv_username);
            time = (TextView) itemView.findViewById(R.id.answer_tv_time);
            title = (TextView) itemView.findViewById(R.id.answer_tv_title);
            focus = (TextView) itemView.findViewById(R.id.answer_tv_focus);
            answer = (TextView) itemView.findViewById(R.id.answer_tv_answer);
            tag = (TextView) itemView.findViewById(R.id.answer_tv_tag);
        }

    }

    public void setHeadView(View view) {
        headView = view;
        notifyItemInserted(0);
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int pos = holder.getLayoutPosition();
        return headView == null ? pos : pos - 1;
    }
}

