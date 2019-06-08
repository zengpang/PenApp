package com.example.administrator.penapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.penapp.api_get_js.AppConfig_url;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;
import com.example.administrator.penapp.api_get_js.gupiao_getjson;
import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class money_Activity extends BaseActivity {
   HashMap<String,String> pmap;
   String pid;

   Button ceshi;
    ImageView gupiao_pictore;
    PullToRefreshView pull_refsh;
    TextView text_todayMax;
    TextView text_todayMin;
    TextView text_competitivePri;
    TextView text_reservePri;
    EditText et_gupiao_number;



    @Override
    protected int intlayout() {
        return R.layout.money_layout;
    }

    @Override
    protected void initlabel() {
        gupiao_pictore=(ImageView) findViewById(R.id.gif_gupiao);
        text_todayMax=(TextView) findViewById(R.id.money_todayMax);
        text_todayMin=(TextView) findViewById(R.id.money_todayMin);
        text_competitivePri=(TextView) findViewById(R.id.money_competitivePri);
        text_reservePri=(TextView)findViewById(R.id.money_reservePri);
        et_gupiao_number=(EditText)findViewById(R.id.gupiao_number);
       pmap=new HashMap<>();
       pid="sh601009";
     if(et_gupiao_number.getText().toString()==null)
       {
          Toast.makeText(money_Activity.this,"请填写股票序号",Toast.LENGTH_SHORT).show();
      }
      else
      {
           pid=et_gupiao_number.getText().toString();

      }
       pmap.put("gid","sh601009");
       final String strUrl;
       pmap.put("key","6db9a65106bfa373c5709f9849d433c8");




        strUrl = AppConfig_url.gupiao_hs+"?"+urlencode(pmap);

        pull_refsh=(PullToRefreshView)findViewById(R.id.pull_money_refsh);
        pull_refsh.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Net(strUrl,netConn);
                pull_refsh.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pull_refsh.setRefreshing(false);
                    }
                },4000);
            }
        });

    }
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            Log.e("json字符串" ,json);
            String result_url=null;
            String result_todayMax=null;
            String result_todayMin=null;
            String result_competitivePri=null;
            String result_reservePri=null;



            try {
                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONArray("result");
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);

                    result_url=jsonObject1.getJSONObject("gopicture").getString("dayurl");
              result_todayMax=jsonObject1.getJSONObject("data").getString("todayMax");
                   result_todayMin=jsonObject1.getJSONObject("data").getString("todayMin");
                   result_competitivePri=jsonObject1.getJSONObject("data").getString("competitivePri");
                   result_reservePri=jsonObject1.getJSONObject("data").getString("reservePri");


                }
          text_todayMax.setText("今日最高价"+result_todayMax+"元");
               text_todayMin.setText("今日最低价"+result_todayMin+"元");
               text_competitivePri.setText("竞买价"+result_competitivePri+"元");
               text_reservePri.setText("竞卖价"+result_reservePri+"元");
                RequestOptions gif_options=new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(money_Activity.this).load(result_url).apply(gif_options).into(gupiao_pictore);
                Log.e("netok: ",result_url );
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void netErroe(String errorMap) {


        }
    };
}
