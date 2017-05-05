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
import com.ooooknow.flove.byxy.ooooknow.model.LetterInfo;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by co-mall on 2016/9/13.
 */
public class LetterAdapter extends RecyclerView.Adapter<LetterAdapter.MyViewHolder> {
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_NORMAL = 1;
    private View headView;

    private List<LetterInfo> datas = new ArrayList<>();
    private Context mContext;

    private int menuW, menuH;

    public LetterAdapter(Context mContext, List<LetterInfo> datas) {
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

        View view = LayoutInflater.from(mContext).inflate(R.layout.letter_list_item, null);
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
        Uri uri = Uri.parse( datas.get(position).getLetteHead());
        holder.userhead.setImageURI(uri);
        holder.username.setText(datas.get(position).getLetteName());
        holder.time.setText(datas.get(position).getLetteTime());
        holder.message.setText(datas.get(position).getLetteMessage());

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
        TextView message;


        public MyViewHolder(View itemView) {
            super(itemView);
            if (itemView == headView) return;
            userhead = (SimpleDraweeView) itemView.findViewById(R.id.letter_sdv_userhead);
            username = (TextView) itemView.findViewById(R.id.letter_tv_username);
            time = (TextView) itemView.findViewById(R.id.letter_tv_time);
            message = (TextView) itemView.findViewById(R.id.letter_tv_message);
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
