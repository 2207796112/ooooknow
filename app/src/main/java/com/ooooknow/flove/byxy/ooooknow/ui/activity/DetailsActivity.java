package com.ooooknow.flove.byxy.ooooknow.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.model.DailyDetail;
import com.ooooknow.flove.byxy.ooooknow.utils.HtmlUtil;
import com.ooooknow.flove.byxy.ooooknow.widget.CircleProgressView;
import com.ooooknow.flove.byxy.ooooknow.widget.ScrollWebView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/*
用户详情
 */
public class DetailsActivity extends AppCompatActivity {
    ImageView mDetailImage;
    TextView mDetailTitle;
    TextView mDetailSource;
    ScrollWebView webView;
    private OkHttpClient client = new OkHttpClient();
    private Handler handler;
    CircleProgressView mCircleProgressView;

    Toolbar mToolbar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        mCircleProgressView = (CircleProgressView) findViewById(R.id.details_progress);
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        int name = bundle.getInt("newsID");


        String url2 = "http://news-at.zhihu.com/api/4/news/" + name;
        String url3 = "http://news-at.zhihu.com/api/4/story-extra/" + name;
        loadData(url2);

        handler = new DetailsActivity.MyHandler();
        mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        setSupportActionBar(mToolbar);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (item.getItemId() == R.id.action_thank) {
                    Toast.makeText(DetailsActivity.this, "a", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.action_praise) {
                    Toast.makeText(DetailsActivity.this, "b", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.action_share) {
                    Toast.makeText(DetailsActivity.this, "c", Toast.LENGTH_SHORT).show();
                } else if (item.getItemId() == R.id.action_comments) {
                    Toast.makeText(DetailsActivity.this, "d", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });

        hideProgress();

        initView();
        initData();
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_samples, menu);
        return true;
    }


    public void initView() {
        webView = (ScrollWebView) findViewById(R.id.detail_web_view);
        mDetailImage = (ImageView) findViewById(R.id.detail_image);
        mDetailTitle = (TextView) findViewById(R.id.detail_title);
        mDetailSource = (TextView) findViewById(R.id.detail_source);
    }

    public void initData() {
        mToolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        mToolbar.setTitle("");//设置主标题
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        webView.setListener(new ScrollWebView.OnScrollListener() {
            @Override
            public void onScrollUp() {
                getSupportActionBar().show();
            }

            @Override
            public void onScrollDown() {
                getSupportActionBar().hide();
            }


        });

    }


    public void showProgress() {

        mCircleProgressView.setVisibility(View.VISIBLE);
        mCircleProgressView.spin();
    }

    public void hideProgress() {

        mCircleProgressView.setVisibility(View.GONE);
        mCircleProgressView.stopSpinning();
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // list = (List<NewsBean.Second.Third>) msg.obj;
            showProgress();


            DailyDetail dailyDetail = (DailyDetail) msg.obj;
            String htmlData = HtmlUtil.createHtmlData(dailyDetail);
            webView.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
            Glide.with(DetailsActivity.this).load(dailyDetail.getImage()).placeholder(R.drawable.account_avatar).into(mDetailImage);
            //设置标题
            mDetailTitle.setText(dailyDetail.getTitle());
            //设置图片来源
            mDetailSource.setText(dailyDetail.getImage_source());
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

                        DailyDetail dailyDetail = gson.fromJson(content, DailyDetail.class);

                        Message msg = handler.obtainMessage();
                        msg.obj = dailyDetail;
                        handler.sendMessage(msg);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
