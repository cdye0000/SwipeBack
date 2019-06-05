package com.cdye.swipeback;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import java.util.Random;

import static android.widget.RelativeLayout.CENTER_IN_PARENT;
import static android.widget.RelativeLayout.LEFT_OF;

public class CanSwipeBackActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initView();
        initViewPager();
    }
    private void initViewPager(){
        ViewPager viewPager = new ViewPager(this);
        viewPager.setId(R.id.id);
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        pagerAdapter.addFragment(new RandomFragment());
        pagerAdapter.addFragment(new RandomFragment());
        pagerAdapter.addFragment(new RandomFragment());
        viewPager.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(viewPager);
    }

    private void initView(){
        RelativeLayout relativeLayout=new RelativeLayout(this);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        Button button=new Button(this);
        RelativeLayout.LayoutParams layoutParams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(CENTER_IN_PARENT);
        relativeLayout.addView(button,layoutParams);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CanSwipeBackActivity.this,CanSwipeBackActivity.class));

            }
        });
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        relativeLayout.setBackgroundColor(Color.argb(255,red,green,blue));
        setContentView(relativeLayout);
    }
}
