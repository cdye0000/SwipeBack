package com.cdye.swipeback;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import java.lang.reflect.Field;

public abstract class BaseActivity extends AppCompatActivity implements SlidingPaneLayout.PanelSlideListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        initSlidingPaneLayout();
        super.onCreate(savedInstanceState);
    }

    /**
     *封装baseActivity，
     * setContentView是将我们的ui布局放入PhoneWindow的DecorView中的一个Layout中
     * 现在把Layout给他 remove掉，替换成SlidingPaneLayout，并且添加一个无背景的panel View，然后再把Layout作为SlidingPaneLayout的content添加进来
     *
     */
    private void initSlidingPaneLayout(){
        PagerEnabledSlidingPaneLayout slidingPaneLayout=new PagerEnabledSlidingPaneLayout(this);
        //通过反射改变mOverhangSize的值为0，这个mOverhangSize值为菜单到右边屏幕的最短距离，默认
        //是32dp，现在给它改成0   this.mOverhangSize = (int)(32.0F * density + 0.5F);
        try {
            Field mOverhangSize = SlidingPaneLayout.class.getDeclaredField("mOverhangSize");
            mOverhangSize.setAccessible(true);
            mOverhangSize.set(slidingPaneLayout,0);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        slidingPaneLayout.setPanelSlideListener(this);
        slidingPaneLayout.setCoveredFadeColor(getResources().getColor(android.R.color.transparent));
        View leftView=new View(this);
        leftView.setLayoutParams(new SlidingPaneLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        slidingPaneLayout.addView(leftView,0);
        ViewGroup decorView= (ViewGroup) getWindow().getDecorView();
        ViewGroup parent= (ViewGroup) decorView.getChildAt(0);
        parent.setBackgroundColor(getResources().getColor(android.R.color.white));
        decorView.removeView(parent);
        slidingPaneLayout.addView(parent,1);
        decorView.addView(slidingPaneLayout);

    }

    @Override
    public void onPanelSlide(@NonNull View view, float v) {

    }

    @Override
    public void onPanelOpened(@NonNull View view) {
        finish();
        this.overridePendingTransition(0,R.anim.slide_out_right);
    }

    @Override
    public void onPanelClosed(@NonNull View view) {

    }
}
