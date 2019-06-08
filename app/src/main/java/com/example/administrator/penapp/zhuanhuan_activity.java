package com.example.administrator.penapp;

import android.widget.TextView;

import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class zhuanhuan_activity extends BaseActivity {
    String lishiurl="http://api.juheapi.com/japi/toh";
    String appkey="f55dc2505a25ed93f3c6dba2864d4bdd";
    String v="1.0";
    String mouth=null;
    String day=null;
    HashMap<String,String>pmap;
    Calendar calendar=null;
    TextView title;
    TextView neirong;

    @Override
    protected int intlayout() {
        return R.layout.zh_layout;
    }

    @Override
    protected void initlabel() {
        pmap=new HashMap<>();
        calendar=Calendar.getInstance();
        mouth=calendar.get(Calendar.MONTH)+"";
        day=calendar.get(Calendar.DAY_OF_MONTH)+"";
        pmap.put("key",appkey);
        pmap.put("v",v);
        pmap.put("month",mouth);
        pmap.put("day",day);
        lishiurl = lishiurl+"?"+urlencode(pmap);
        title=(TextView)findViewById(R.id.lishi_text_title);
        neirong=(TextView)findViewById(R.id.lishi_neirong);
        Net(lishiurl,netConn);



    }
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            try {
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONArray("result");
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    title.setText(jsonObject1.getString("title"));
                    neirong.setText(jsonObject1.getString("des"));



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
