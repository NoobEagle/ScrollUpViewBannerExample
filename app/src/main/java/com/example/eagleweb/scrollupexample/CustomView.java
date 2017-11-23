package com.example.eagleweb.scrollupexample;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @创建者 帅子
 * @创建时间 17/11/22.
 * @描述
 */

public class CustomView extends LinearLayout {

    private TextView tvNickname1;
    private TextView tvNickname2;


    public CustomView(Context context) {
        this(context, null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    public void initViews() {
        tvNickname1 = (TextView) findViewById(R.id.tv_nickname1);
        tvNickname2 = (TextView) findViewById(R.id.tv_nickname2);
    }


    private void initView() {
        inflate(getContext(), R.layout.item_view, this);
        initViews();
    }

    public void setNickname(String nickname1, String nickname2) {
        tvNickname1.setText(nickname1);
        tvNickname2.setText(nickname2);
    }
}
