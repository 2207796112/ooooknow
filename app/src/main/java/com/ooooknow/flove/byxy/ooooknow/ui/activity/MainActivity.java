package com.ooooknow.flove.byxy.ooooknow.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationItem;
import com.luseen.luseenbottomnavigation.BottomNavigation.BottomNavigationView;
import com.luseen.luseenbottomnavigation.BottomNavigation.OnBottomNavigationItemClickListener;
import com.ooooknow.flove.byxy.ooooknow.R;
import com.ooooknow.flove.byxy.ooooknow.ui.fragment.FragmentIndex;
import com.ooooknow.flove.byxy.ooooknow.ui.fragment.FragmentMessage;
import com.ooooknow.flove.byxy.ooooknow.ui.fragment.FragmentMy;
import com.ooooknow.flove.byxy.ooooknow.ui.fragment.FragmentQna;

import java.util.ArrayList;
import java.util.List;

/*
主界面
 */
public class MainActivity extends AppCompatActivity {
    //标题
    private Toolbar mToolbar;
    //底部菜单
    private BottomNavigationView bottomNavigationView;
    //底部菜单图片
    private int[] image = {R.drawable.index_blue_64, R.drawable.qna_blue_64,
            R.drawable.message_blue_64, R.drawable.my_blue_64};
    //底部菜单切换颜色
    private int color = R.color.skin_tabbar_bg;

    //声明一个Fragment
    private Fragment mFrag;
    //创建用于存放Fragment的集合
    private List<Fragment> frags = new ArrayList<>();

    // 定义Fragment页面
    private FragmentIndex fragmentIndex;
    private FragmentQna fragmentAnswer;
    private FragmentMessage fragmentMessage;
    private FragmentMy fragmentMy;
    // 定义布局对象
    private FrameLayout indexFl, answerFl, messageFl, myFl;

    private int num = 0;

    // 定义图片组件对象
    private ImageView indexIv, answerIv, messageIv, myIv;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentIndex = new FragmentIndex();
        fragmentAnswer = new FragmentQna();
        fragmentMessage = new FragmentMessage();
        fragmentMy = new FragmentMy();
        frags.add(fragmentIndex);
        frags.add(fragmentAnswer);
        frags.add(fragmentMessage);
        frags.add(fragmentMy);
        loadFragment(0);



        bottomNavigationView = (BottomNavigationView) findViewById(R.id.main_bottom);

        if (bottomNavigationView != null) {
            bottomNavigationView.isWithText(false);
            bottomNavigationView.isColoredBackground(false);
            bottomNavigationView.setTextActiveSize(getResources().getDimension(R.dimen.text_active));
            bottomNavigationView.setTextInactiveSize(getResources().getDimension(R.dimen.text_inactive));
            bottomNavigationView.setItemActiveColorWithoutColoredBackground(ContextCompat.getColor(this, R.color.blue));
            bottomNavigationView.disableShadow();
        }

        BottomNavigationItem bottomNavigationItem = new BottomNavigationItem
                ("首页", color, image[0]);
        BottomNavigationItem bottomNavigationItem1 = new BottomNavigationItem
                ("问答", color, image[1]);
        BottomNavigationItem bottomNavigationItem2 = new BottomNavigationItem
                ("消息", color, image[2]);
        BottomNavigationItem bottomNavigationItem3 = new BottomNavigationItem
                ("我的", color, image[3]);


        bottomNavigationView.addTab(bottomNavigationItem);
        bottomNavigationView.addTab(bottomNavigationItem1);
        bottomNavigationView.addTab(bottomNavigationItem2);
        bottomNavigationView.addTab(bottomNavigationItem3);

        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                switch (index) {
                    case 0:
                        loadFragment(0);
                        break;
                    case 1:
                        loadFragment(1);
                        break;
                    case 2:
                        loadFragment(2);
                        break;
                    case 3:
                        loadFragment(3);
                        break;
                }
            }
        });


    }

    //进行Fragment 加载的方法，position 是Activity 中选中Fragment在Tab中的序号
    private void loadFragment(int position) {

//从集合中获取相对序号的Fragment
        Fragment f = frags.get(position);

        FragmentTransaction fragmentTransaction = this
                .getSupportFragmentManager().beginTransaction();
//首先判断mFrag 是否为空，如果不为，先隐藏起来，接着判断从List 获取的Fragment是否已经添加到Transaction中，如果未添加，添加后显示，如果已经添加，直接显示
        if (mFrag != null) {
            fragmentTransaction.hide(mFrag);
        }
        if (!f.isAdded()) {
            fragmentTransaction.add(R.id.main_frame_content, f);

        } else {
            fragmentTransaction.show(f);
        }
//将获取的Fragment 赋值给声明的Fragment 中，提交
        mFrag = f;
        fragmentTransaction.commit();

    }

    private long firstTime = 0;

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                long secondTime = System.currentTimeMillis();
                if (secondTime - firstTime > 2000) {
                    Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                    firstTime = secondTime;
                    return true;
                } else {
                    System.exit(0);
                }
                break;
        }
        return super.onKeyUp(keyCode, event);
    }


}

