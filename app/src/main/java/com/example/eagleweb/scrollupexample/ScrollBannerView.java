package com.example.eagleweb.scrollupexample;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * @创建者 帅子
 * @创建时间 17/11/21.
 * @描述
 */

public class ScrollBannerView extends LinearLayout {

    private TextView mBannerTV1;
    private TextView mBannerTV2;
    private Handler  handler;
    private boolean  isShow;
    private int      startY1, endY1, startY2, endY2;
    private Runnable     runnable;
    private List<String> list;
    private int position = 0;
    private int offsetY  = 100;


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
        mBannerTV1 = (TextView) view.findViewById(R.id.tv_banner1);
        mBannerTV2 = (TextView) view.findViewById(R.id.tv_banner2);

        handler = new Handler();

        runnable = new Runnable() {
            @Override
            public void run() {
                isShow = !isShow;

                if (position == list.size()) {
                    position = 0;
                }


                startY1 = isShow ? 0 : offsetY;
                endY1 = isShow ? -offsetY : 0;


                ObjectAnimator translationY1 = ObjectAnimator.ofFloat(mBannerTV1, "translationY", startY1, endY1);
                translationY1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (isShow) {
                                    mBannerTV1.setText(list.get(position++));
                                } else {
                                    //                                    mBannerTV2.setText(list.get(position++));
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
                });
                translationY1.setDuration(300).start();

                startY2 = isShow ? offsetY : 0;
                endY2 = isShow ? 0 : -offsetY;
                ObjectAnimator translationY2 = ObjectAnimator.ofFloat(mBannerTV2, "translationY", startY2, endY2);
                translationY2.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (isShow) {
                                    //                                    mBannerTV1.setText(list.get(position++));
                                } else {
                                    mBannerTV2.setText(list.get(position++));
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
                });
                translationY2.setDuration(300).start();


                handler.postDelayed(runnable, 3000);
            }
        };
    }


    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void startScroll() {
        handler.post(runnable);
    }

    public void stopScroll() {
        handler.removeCallbacks(runnable);
    }
}
