package com.example.administrator.penapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.administrator.penapp.DIY_View.HouseLoadingView;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class loading_news_Activity extends BaseActivity {
    private HouseLoadingView loadingView;
    private int mProgress = 0;
    private static final int REFRESH_PROGRESS = 0;
    ArrayList<String> list_first_news_image;
    ArrayList<String> list_title_first;
    ArrayList<String> list_author_name;
    HashMap<String,String> pmap;
    String news_get_url;
    String news_url="http://v.juhe.cn/toutiao/index";
    String app_key="2507cdf6e5893f002f3f3d9300cc005d";
    Intent intent_load;
    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case REFRESH_PROGRESS:
                    if (mProgress < 40) {
                        mProgress += 1;
                        // 随机500ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                               50);
                        loadingView.setProgress(mProgress);

                    } else if(mProgress<=100) {
                        mProgress += 1;
                        // 随机800ms以内刷新一次
                        mHandler.sendEmptyMessageDelayed(REFRESH_PROGRESS,
                               100);
                        loadingView.setProgress(mProgress);

                    }
                    else {
                        loadingView.setProgress(100);
                        startActivity(intent_load);
                        finish();

                    }

                    break;

                default:
                    break;
            }



        };


    };



    @Override
    protected int intlayout() {
        return R.layout.house_loading_layout;
    }

    @Override
    protected void initlabel() {
        pmap=new HashMap<>();
        intent_load=new Intent(loading_news_Activity.this,news_Activity.class);


        loadingView = (HouseLoadingView) findViewById(R.id.house);
        list_title_first=new ArrayList<>();
        list_first_news_image=new ArrayList<>();
        list_author_name=new ArrayList<>();
        pmap=new HashMap<>();
        pmap.put("key",app_key);
        news_get_url=news_url+"?"+urlencode(pmap);
        Net(news_get_url,netConn);
        intent_load.putStringArrayListExtra("first_list_image_date",list_first_news_image);
        intent_load.putStringArrayListExtra("first_list_date",list_title_first);
        intent_load.putStringArrayListExtra("first_list_author_name",list_author_name);
        

        Log.e("initlabel: ", news_get_url);
        Message msg=new Message();
        msg.what=0;
        mHandler.sendMessage(msg);


    }

    @Override
    public boolean onCreatePanelMenu(int featureId, Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            mProgress++;
            loadingView.setProgress(mProgress);
            Log.e("test_wqj","mProgress : "+mProgress);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            Log.e("json字符串" ,json);
            try {

                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONObject("result").getJSONArray("data");
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    list_first_news_image.add(jsonObject1.getString("thumbnail_pic_s"));
                    Log.e("json字符串_1" ,jsonObject1.getString("thumbnail_pic_s"));
                    list_title_first.add(jsonObject1.getString("title"));
                    list_author_name.add(jsonObject1.getString("author_name"));


                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void netErroe(String errorMap) {

        }
    };

}
