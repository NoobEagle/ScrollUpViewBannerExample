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

        ArrayList<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("哟喂：" + i);
        }
        scroll.setList(strings);
        scroll.startScroll();
    }
}
