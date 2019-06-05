package com.cdye.swipeback;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewConfiguration;

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