package com.cdye.swipeback;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

/**
 * 当页面中使用viewpager时，SlidingPaneLayout会屏蔽viewpager的滑动事件
 * 因此重写onInterceptTouchEvent，当右滑时并且panel是关闭状态（即打开panel），如果SlidingPaneLayout内部有控件可以滑动，使SlidingPaneLayout不响应当前滑动事件
 */
public class PagerEnabledSlidingPaneLayout extends SlidingPaneLayout {

    private float mInitialMotionX;
    private float mInitialMotionY;
    private float mEdgeSlop;

    public PagerEnabledSlidingPaneLayout(Context context) {
        this(context, null);
    }

    public PagerEnabledSlidingPaneLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PagerEnabledSlidingPaneLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        ViewConfiguration config = ViewConfiguration.get(context);
        mEdgeSlop = config.getScaledEdgeSlop();
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("mEdgeSlop=",mEdgeSlop+"");

        switch (MotionEventCompat.getActionMasked(ev)) {
            case MotionEvent.ACTION_DOWN: {
                mInitialMotionX = ev.getX();
                mInitialMotionY = ev.getY();
                break;
            }

            case MotionEvent.ACTION_MOVE: {
                final float x = ev.getX();
                final float y = ev.getY();
                if (mInitialMotionX > mEdgeSlop && !isOpen() && canScroll(this, false,Math.round(x - mInitialMotionX), Math.round(x), Math.round(y))) {
                    MotionEvent cancelEvent = MotionEvent.obtain(ev);
                    cancelEvent.setAction(MotionEvent.ACTION_CANCEL);
                    Log.d("mEdgeSlop=",mEdgeSlop+"ACTION_CANCEL");
                    return super.onInterceptTouchEvent(cancelEvent);
                }
                Log.d("mEdgeSlop=",mEdgeSlop+"ACTION_CANCEL_NOT");
            }
        }
        return super.onInterceptTouchEvent(ev);
    }
}