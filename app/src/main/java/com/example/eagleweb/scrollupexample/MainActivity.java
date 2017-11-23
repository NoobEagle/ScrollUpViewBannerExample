package com.example.eagleweb.scrollupexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ScrollBannerView scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.scroll = (ScrollBannerView) findViewById(R.id.scroll);

        ArrayList<Bean> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Bean bean = new Bean();
            bean.setTime(i % 2 == 0 ? 3000 : 6000);
            bean.setVip(i);
            bean.setText1("文字1:" + i);
            bean.setText2("文字2:" + i);
            bean.setText3("文字3:" + i);
            strings.add(bean);
        }
        scroll.setList(strings);
        scroll.startScroll();
    }
}
