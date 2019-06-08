package com.example.administrator.penapp;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

import com.example.administrator.penapp.when_page.PageFrameLayout;
import com.tbruyelle.rxpermissions.RxPermissions;

import rx.functions.Action1;

/**
 * Created by Administrator on 2018/10/27 0027.
 */

public class welcomeActivity extends AppCompatActivity {
    private PageFrameLayout contentFrameLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        RxPermissions.getInstance(welcomeActivity.this)
                .request(Manifest.permission.RECEIVE_MMS,
                        Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.BLUETOOTH,
                        Manifest.permission.CHANGE_WIFI_STATE,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_WIFI_STATE,
                        Manifest.permission.ACCESS_NETWORK_STATE,


                        Manifest.permission.ACCESS_FINE_LOCATION)//多个权限用","隔开
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if (aBoolean) {
                            //当所有权限都允许之后，返回true
                            Log.i("permissions", "btn_more_sametime：" + aBoolean);
                        } else {
                            //只要有一个权限禁止，返回false，
                            //下一次申请只申请没通过申请的权限
                            Log.i("permissions", "btn_more_sametime：" + aBoolean);

                        }
                    }
                });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_main_layout);
        contentFrameLayout = (PageFrameLayout) findViewById(R.id.contentFrameLayout);
        contentFrameLayout.setUpViews(new int[]{
                R.layout.welcome_layout1,
                R.layout.welcome_layout2,
                R.layout.welcome_layout3,
                R.layout.welcome_layout4
        }, R.mipmap.banner_on, R.mipmap.banner_off);


    }
}
