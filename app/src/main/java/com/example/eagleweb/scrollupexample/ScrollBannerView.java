package com.example.eagleweb.scrollupexample;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

/**
 * @创建者 帅子
 * @创建时间 17/11/21.
 * @描述
 */

public class ScrollBannerView extends LinearLayout {

    private CustomView mBannerTV1;
    private CustomView mBannerTV2;
    private Handler    handler;
    private boolean    isShow;
    private int        startY1, endY1, startY2, endY2;
    private Runnable   runnable;
    private List<Bean> list;
    private int position = 0;
    private int offsetY  = 100;
    private ObjectAnimator mTranslationY1;
    private ObjectAnimator mTranslationY2;
    private Bean           mBean1;
    private Bean           mBean2;


    public ScrollBannerView(Context context) {
        this(context, null);
    }

    public ScrollBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ScrollBannerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.view_scroll_banner, this);
        mBannerTV1 = (CustomView) view.findViewById(R.id.tv_banner1);
        mBannerTV2 = (CustomView) view.findViewById(R.id.tv_banner2);

        handler = new Handler();
        offsetY = (int) getResources().getDimension(R.dimen.height);
        runnable = new Runnable() {
            @Override
            public void run() {
                isShow = !isShow;

                if (position == list.size()) {
                    position = 0;
                }

                startY1 = isShow ? 0 : offsetY;
                endY1 = isShow ? -offsetY : 0;

                mTranslationY1 = ObjectAnimator.ofFloat(mBannerTV1, "translationY", startY1, endY1);
                mTranslationY1.addListener(listener1);
                mTranslationY1.setDuration(300).start();

                startY2 = isShow ? offsetY : 0;
                endY2 = isShow ? 0 : -offsetY;

                mTranslationY2 = ObjectAnimator.ofFloat(mBannerTV2, "translationY", startY2, endY2);
                mTranslationY2.addListener(listener2);
                mTranslationY2.setDuration(300).start();

                System.out.println("本次搞的事情： endY1：" + endY1 + "   endY2：" + endY2);
                int time = 3000;
                if (endY1 == 0) {
                    // 第一个的延时时间
                    time = mBean1.getTime();
                } else {
                    // 第二个的延时时间
                    time = mBean2.getTime();
                }

                handler.postDelayed(runnable, time);
            }
        };
    }

    Animator.AnimatorListener listener1 = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (isShow) {
                        mBean1 = list.get(position++);
                        updateView(mBannerTV1, mBean1);
                    }
                }
            });
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };
    Animator.AnimatorListener listener2 = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animator) {

        }

        @Override
        public void onAnimationEnd(Animator animator) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    if (!isShow) {
                        mBean2 = list.get(position++);
                        updateView(mBannerTV2, mBean2);
                    }
                }
            });
        }

        @Override
        public void onAnimationCancel(Animator animator) {

        }

        @Override
        public void onAnimationRepeat(Animator animator) {

        }
    };

    private void updateView(CustomView bannerTV, Bean bean) {
        bannerTV.setNickname(bean.getText1(), bean.getText2() + " time:" + bean.getTime());
    }


    public List<Bean> getList() {
        return list;
    }

    public void setList(List<Bean> list) {
        this.list = list;

        mBean1 = list.get(position++);
        mBean2 = list.get(position++);
    }

    public void startScroll() {
        handler.post(runnable);
    }

    public void stopScroll() {
        handler.removeCallbacks(runnable);
    }

    public void onResume() {
        handler.removeCallbacks(runnable);
        handler.post(runnable);
    }

    public void onPause() {
        if (mTranslationY1 != null) {
            mTranslationY1.cancel();
        }
        if (mTranslationY2 != null) {
            mTranslationY2.cancel();
        }
        handler.removeCallbacks(runnable);
    }

    public void onDestroy() {
        if (mTranslationY1 != null) {
            mTranslationY1.cancel();
            mTranslationY1 = null;
        }
        if (mTranslationY2 != null) {
            mTranslationY2.cancel();
            mTranslationY2 = null;
        }
        if (handler != null) {
            handler.removeCallbacks(runnable);
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        if (listener1 != null) {
            listener1 = null;
        }
        if (listener2 != null) {
            listener2 = null;
        }
    }
}
