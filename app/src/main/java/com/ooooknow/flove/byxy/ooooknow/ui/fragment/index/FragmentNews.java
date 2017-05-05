package com.ooooknow.flove.byxy.ooooknow.ui.fragment.index;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ooooknow.flove.byxy.ooooknow.FrescoImageLoader;
import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.adapter.MainAdapter;
import com.ooooknow.flove.byxy.ooooknow.lfrecycler.LFRecyclerView;
import com.ooooknow.flove.byxy.ooooknow.lfrecycler.OnItemClickListener;
import com.ooooknow.flove.byxy.ooooknow.model.NewsList;
import com.ooooknow.flove.byxy.ooooknow.ui.activity.DetailsActivity;
import com.ooooknow.flove.byxy.ooooknow.widget.CircleProgressView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class FragmentNews extends Fragment implements OnItemClickListener, LFRecyclerView.LFRecyclerViewListener, LFRecyclerView.LFRecyclerViewScrollChange, OnBannerListener {


    private OkHttpClient client = new OkHttpClient();
    private Handler newhandler, addhandler;
    View view;
    private LFRecyclerView recycleview;
    private boolean b;
    private List<NewsList.StoriesBean> newsList;
    private List<NewsList.TopStoriesBean> newsTop;
    private MainAdapter adapter;

    private int conditions = 1;

    View headView;
    Banner banner;

    FloatingActionButton fab;
    boolean fabOpened = false;
    TextView cloud;

    CircleProgressView mCircleProgressView;
    //测试
    int num = 0;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_index_test, container, false);
        initView();
        initData();
        start();
        String url = "http://news-at.zhihu.com/api/4/stories/latest";
        loadData(url);
        showProgress();
        newhandler = new FragmentNews.NewHandler();

        return view;
    }

    private void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
        recycleview.setVisibility(View.GONE);
    }

    public void hideProgress() {
        mCircleProgressView.setVisibility(View.GONE);
        mCircleProgressView.stopSpinning();
        recycleview.setVisibility(View.VISIBLE);
    }


    class NewHandler extends Handler {

        public Message msgs;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            this.msgs = msg;
            // list = (List<NewsBean.Second.Third>) msg.obj;
            NewsList news = (NewsList) msg.obj;
            newsTop = news.getTop_stories();
            newsList = news.getStories();

            if (conditions == 1) {
                adapter.updateData(newsList);
                List table = new ArrayList();
                List img = new ArrayList();
                for (int i = 0; i < 5; i++) {
                    table.add(newsTop.get(i).getTitle());
                    img.add(newsTop.get(i).getImage());
                }

                banner.update(img, table);

            } else if (conditions == 2) {
                Toast.makeText(getActivity(), "2", Toast.LENGTH_SHORT).show();
            } else if (conditions == 3) {
                Toast.makeText(getActivity(), "3", Toast.LENGTH_SHORT).show();
            }

            hideProgress();

        }
    }

    private void loadData(String url) {
        final Gson gson = new Gson();
        final Request request = new Request.Builder()
                .get()
                .url(url)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response response;
                try {
                    response = client.newCall(request).execute();
                    if (response.isSuccessful()) {

                        String content = response.body().string();
                        //新闻列表
                        NewsList news = gson.fromJson(content, NewsList.class);

                        Message msg = newhandler.obtainMessage();
                        msg.obj = news;
                        newhandler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //初始化控件
    private void initView() {
        mCircleProgressView = (CircleProgressView) view.findViewById(R.id.index_progress);
        newsList = new ArrayList<NewsList.StoriesBean>();

        recycleview = (LFRecyclerView) view.findViewById(R.id.recycleview);
        adapter = new MainAdapter(newsList);
        headView = LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_header, recycleview, false);
        banner = (Banner) headView.findViewById(R.id.banner);
        //样式
        fab = (FloatingActionButton) view.findViewById(R.id.index_fab);
        cloud = (TextView) view.findViewById(R.id.index_cloud);

    }

    private void initData() {
        cloud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeMenu(fab);
            }
        });
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!fabOpened) {
                    openMenu(v);
                } else {
                    closeMenu(v);
                }
            }
        });
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        //点击事件
        banner.setOnBannerListener(this);
        //滑动间隔
        banner.setDelayTime(3000);


        recycleview.setLoadMore(true);
        recycleview.setRefresh(true);
        recycleview.setNoDateShow();
        recycleview.setAutoLoadMore(true);
        recycleview.setOnItemClickListener(this);
        recycleview.setLFRecyclerViewListener(this);
        recycleview.setScrollChangeListener(this);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.setAdapter(adapter);
        recycleview.setHeaderView(banner);
        recycleview.addOnScrollListener(new RecyclerView.OnScrollListener() {

            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (Math.abs(dy) > 5) {
                    if (dy > 0) {
                        fab.hide();
                    } else {
                        fab.show();
                    }
                }
            }
        });

    }

    public void start() {
        List table = new ArrayList();
        List img = new ArrayList();
        for (int i = 0; i < 5; i++) {
            table.add("");
            img.add("");
            adapter.notifyItemInserted(i);
        }
        banner.setBannerTitles(table);
        banner.setImages(img);
        banner.setImageLoader(new FrescoImageLoader());
        banner.start();
    }


    public List<NewsList.StoriesBean> getList() {
        List<NewsList.StoriesBean> newList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            NewsList.StoriesBean storiesBean = new NewsList.StoriesBean();
            List<String> a = new ArrayList();
            a.add("http://60.205.183.108/background.jpg");
            storiesBean.setImages(a);
            storiesBean.setTitle("aaaaa");

            newList.add(storiesBean);

        }
        num++;
        return newList;
    }


    //列表点击事件
    public void onClick(int position) {

        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        Bundle bundle = new Bundle();

        //传递name参数为tinyphp
        bundle.putInt("newsID", newsList.get(position).getId());
        intent.putExtras(bundle);

        getActivity().startActivity(intent);
    }

    //列表长按事件
    public void onLongClick(int po) {

    }

    //下拉刷新
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                /*recycleview.stopRefresh(true);
                newhandler = new FragmentNews.NewHandler();
                adapter.updateData(newsList);*/

                recycleview.stopRefresh(true);
                adapter.addData(newsList);
                newhandler = new FragmentNews.NewHandler();
            }
        }, 2000);
    }


    //上拉新增
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
               /* recycleview.stopRefresh(true);
                newhandler = new FragmentNews.NewHandler();
                adapter.addData(newsList);*/

                recycleview.stopLoadMore();
                conditions = 3;
                newhandler = new FragmentNews.NewHandler();
            }
        }, 2000);

    }

    @Override
    public void onRecyclerViewScrollChange(View view, int i, int i1) {

    }


    //轮播图片点击事件
    public void OnBannerClick(int position) {


        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        Bundle bundle = new Bundle();

        //传递name参数为tinyphp
        bundle.putInt("newsID", newsTop.get(position).getId());
        intent.putExtras(bundle);

        getActivity().startActivity(intent);
    }


    //悬浮按钮点击动画
    public void openMenu(View v) {

        cloud.setVisibility(View.VISIBLE);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 0.7f);
        alphaAnimation.setDuration(500);
        alphaAnimation.setFillAfter(true);
        cloud.startAnimation(alphaAnimation);
        fabOpened = true;

    }

    //还原悬浮按钮点击动画
    public void closeMenu(View v) {

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.7f, 0);
        alphaAnimation.setDuration(500);
        cloud.startAnimation(alphaAnimation);
        cloud.setVisibility(View.GONE);
        fabOpened = false;
    }

}
