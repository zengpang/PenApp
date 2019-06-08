package com.example.administrator.penapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;


import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.administrator.penapp.api_get_js.gupiao_getjson.DEF_CHATSET;

public class tianqi_Activity extends BaseActivity {
    private LocationClient mLocationClient;
    InputStream is = null;
    String city = null;
    BufferedReader reader = null;
    String rs = "无";
    HashMap<String, String> pmap;
    String townID = "CHBJ000000";
    TextView tianqi_city;
    TextView wendu_today;
    TextView tianqi_today;
    TextView tianqi_car;
    TextView tianqi_cx;
    TextView tianday_1;
    TextView tianday_2;
    TextView tianday_3;
    TextView tianday_4;
    TextView tianday_5;
    ImageView img_1;
    ImageView img_2;
    ImageView img_3;
    ImageView img_4;
    ImageView img_5;
    List<TextView> tianday_list;
    List<ImageView> img_list;
    List<TextView> wendu_list;
    TextView wendu_day_1;
    TextView wendu_day_2;
    TextView wendu_day_3;
    TextView wendu_day_4;
    TextView wendu_day_5;
    RelativeLayout re_background;
    ImageView bt_genxin;

    String tianqi_url = "http://tj.nineton.cn/Heart/index/all";



    @Override
    protected int intlayout() {
        return R.layout.tianqi_layout;
    }

    @Override
    protected void initlabel() {
        initLocation();
        requestLocation();
        tianday_list = new ArrayList<>();
        img_list = new ArrayList<>();
        wendu_list = new ArrayList<>();
        re_background = (RelativeLayout) findViewById(R.id.background_tianqi);
        tianqi_city = (TextView) findViewById(R.id.tianqi_city);
        wendu_today = (TextView) findViewById(R.id.wendu_text);
        tianqi_today = (TextView) findViewById(R.id.tianti_today);
        tianqi_car = (TextView) findViewById(R.id.tianqi_car);
        bt_genxin = (ImageView) findViewById(R.id.tianqi_genxin);
        tianqi_cx = (TextView) findViewById(R.id.tianqi_cx);
        tianday_1 = (TextView) findViewById(R.id.tianqi_day_1);
        tianday_list.add(tianday_1);
        tianday_2 = (TextView) findViewById(R.id.tianqi_day_2);
        tianday_list.add(tianday_2);
        tianday_3 = (TextView) findViewById(R.id.tianqi_day_3);
        tianday_list.add(tianday_3);
        tianday_4 = (TextView) findViewById(R.id.tianqi_day_4);
        tianday_list.add(tianday_4);
        tianday_5 = (TextView) findViewById(R.id.tianqi_day_5);
        tianday_list.add(tianday_5);
        wendu_day_1 = (TextView) findViewById(R.id.wendu_day_1);
        wendu_list.add(wendu_day_1);
        wendu_day_2 = (TextView) findViewById(R.id.wendu_day_2);
        wendu_list.add(wendu_day_2);
        wendu_day_3 = (TextView) findViewById(R.id.wendu_day_3);
        wendu_list.add(wendu_day_3);
        wendu_day_4 = (TextView) findViewById(R.id.wendu_day_4);
        wendu_list.add(wendu_day_4);
        wendu_day_5 = (TextView) findViewById(R.id.wendu_day_5);
        wendu_list.add(wendu_day_5);
        img_1 = (ImageView) findViewById(R.id.img_day_1);
        img_list.add(img_1);
        img_2 = (ImageView) findViewById(R.id.img_day_2);
        img_list.add(img_2);
        img_3 = (ImageView) findViewById(R.id.img_day_3);
        img_list.add(img_3);
        img_4 = (ImageView) findViewById(R.id.img_day_4);
        img_list.add(img_4);
        img_5 = (ImageView) findViewById(R.id.img_day_5);
        img_list.add(img_5);


        pmap = new HashMap<>();

        StringBuffer sb = new StringBuffer();
        try {
            is = getAssets().open("tianqi_CityCode");
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            Log.e("错误报告——天气页面", rs);
            JSONArray jsonArray = new JSONArray(rs);
            for (int i = 0; i < jsonArray.length(); i++) {
                String getcity = jsonArray.getJSONObject(i).getString("cityName");
                if (getcity == city) {
                    townID = jsonArray.getJSONObject(i).getString("townID");
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (townID == "") {
            Toast.makeText(tianqi_Activity.this, "未定位到所在城市", Toast.LENGTH_SHORT).show();
            Log.e("错误报告——天气页面", "未定位到所在城市");
        } else {
            pmap.put("city", townID);
            pmap.put("language", "zh-chs");
            tianqi_url = tianqi_url + "?" + urlencode(pmap);
            Net(tianqi_url, netConn);
        }
        bt_genxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Net(tianqi_url, netConn_genxin);
            }
        });


    }
    private void requestLocation() {
        LocationClientOption  option = new LocationClientOption();

        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }
    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                StringBuilder currentPosition = new StringBuilder();
                city=bdLocation.getCity();

                Toast.makeText(tianqi_Activity.this,city,Toast.LENGTH_LONG).show();

                if (bdLocation.getLocType() == BDLocation.TypeGpsLocation) {
                    currentPosition.append("GPS");
                } else if (bdLocation.getLocType() == BDLocation.TypeNetWorkLocation) {
                    currentPosition.append("网络");
                }


            }
        });

    }





    NetConn netConn_genxin=new NetConn() {
        @Override
        public void netok(String json) {
            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONArray("weather");
                for(int g=0;g<jsonArray.length();g++)
                {
                    String wendu=jsonArray.getJSONObject(g).getJSONObject("now").getString("temperature");
                    wendu_today.setText(wendu);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        public void netErroe(String errorMap) {

        }
    };
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            try {
                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONArray("weather");
                for(int i=0;i<jsonArray.length();i++)
                {
                 tianqi_city.setText(jsonArray.getJSONObject(i).getString("city_name"));
                 String wendu=jsonArray.getJSONObject(i).getJSONObject("now").getString("temperature");
                 wendu_today.setText(wendu);
                 if(Integer.parseInt(wendu)<=0)
                 {
                     re_background.setBackgroundResource(R.drawable.tianqi_dongtian);
                 }
                    if(Integer.parseInt(wendu)>0&&Integer.parseInt(wendu)<=10)
                    {
                        re_background.setBackgroundResource(R.drawable.tianqi_duoyun);
                    }
                    if(Integer.parseInt(wendu)>10&&Integer.parseInt(wendu)<23)
                    {
                        re_background.setBackgroundResource(R.drawable.tianqi_chun);
                    }
                    if(Integer.parseInt(wendu)>=23)
                    {
                        re_background.setBackgroundResource(R.drawable.chun_xia);
                    }

                 tianqi_today.setText(jsonArray.getJSONObject(i).getJSONObject("now").getString("text"));
                 tianqi_car.setText(jsonArray.getJSONObject(i).getJSONObject("today").getJSONObject("car_washing").getString("brief"));
                 tianqi_cx.setText(jsonArray.getJSONObject(i).getJSONObject("today").getJSONObject("travel").getString("brief"));

                 JSONArray jsonArray_future=jsonArray.getJSONObject(i).getJSONArray("future");
                 for(int h=0;h<jsonArray_future.length();h++)
                 {
                     tianday_list.get(h).setText(jsonArray_future.getJSONObject(h).getString("day"));
                     wendu_list.get(h).setText(jsonArray_future.getJSONObject(h).getString("low")+"~"+jsonArray_future.getJSONObject(h).getString("high"));
                     String day_tianqi=jsonArray_future.getJSONObject(h).getString("text");
                     if(day_tianqi=="小雨/多云"||day_tianqi=="小雨")
                     {
                         img_list.get(h).setImageResource(R.drawable.xiaoyu);
                     }
                     if(day_tianqi=="中雨")
                     {
                         img_list.get(h).setImageResource(R.drawable.zhongyu);
                     }
                     if (day_tianqi=="多云")
                     {
                         img_list.get(h).setImageResource(R.drawable.duoyuh);
                     }
                     if(day_tianqi=="阵雨")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.zhengyu);
                     }
                     if(day_tianqi=="阵雪")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.zhengxue);
                     }
                     if (day_tianqi=="大雪")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.daxue);
                     }
                     if(day_tianqi=="小雪")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.xiaoxue);
                     }
                     if(day_tianqi=="大暴雨")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.dabaoyu);
                     }
                     if(day_tianqi=="暴雨")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.baoyu);
                     }
                     if(day_tianqi=="晴")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.qing);
                     }
                     if(day_tianqi=="中雪")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.zhongxue);
                     }
                     if(day_tianqi=="中雨")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.zhongyu);
                     }
                     if (day_tianqi=="大雨")
                     {
                         img_list.get(h).setBackgroundResource(R.drawable.dayu);
                     }


                 }

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
