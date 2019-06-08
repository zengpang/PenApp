package com.example.administrator.penapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/10/21 0021.
 */

public class loadActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load_layout);

        Timer timer=new Timer();
        timer.schedule(new Task(),3000);
    }
    private class Task extends TimerTask {
        @Override
        public void run() {
            startActivity(new Intent(loadActivity.this,ViewPage_Main.class));
        }

    }
}
