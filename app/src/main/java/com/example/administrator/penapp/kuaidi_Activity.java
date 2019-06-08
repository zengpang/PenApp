package com.example.administrator.penapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.penapp.MyDialog.Dialog_kuaidi;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;
import com.google.gson.JsonObject;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class kuaidi_Activity extends BaseActivity {
    EditText wuliu_number;
    EditText dan_number;
    Button bt_cx;
    String number;
    String dan;
    String zt;
    ImageView back;
    String starttime;
    String endtime;
    Dialog_kuaidi dialogKuaidi;
    String kuaidi_url="https://www.kuaidi100.com/query";
    HashMap<String,String> pmap;
    private KProgressHUD hud;


    @Override
    protected int intlayout() {
        return R.layout.kuaiti_layout;
    }

    @Override
    protected void initlabel() {
          wuliu_number=(EditText)findViewById(R.id.kuaidi_number);
          dan_number=(EditText)findViewById(R.id.kuaidi_dan_number);
          back=(ImageView)findViewById(R.id.kuaidi_back);
          bt_cx=(Button)findViewById(R.id.bt_chaxun);

          pmap=new HashMap<>();
          back.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  startActivity(new Intent(kuaidi_Activity.this,ViewPage_Main.class));
                  overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
              }
          });
         bt_cx.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 number=wuliu_number.getText().toString();
                 dan=dan_number.getText().toString();
                 if(number=="")
                 {
                     number="huitongkuaidi";
                 }
                 if(dan=="")
                 {
                     Toast.makeText(kuaidi_Activity.this,"请输入查询单号",Toast.LENGTH_SHORT).show();
                 }
                 else
                 {
                     pmap.put("type",number);
                     pmap.put("postid",dan);
                     kuaidi_url = kuaidi_url+"?"+urlencode(pmap);
                     Net(kuaidi_url,netConn);
//                     hud = KProgressHUD.create(kuaidi_Activity.this)
//                             .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
//                     scheduleDismiss();
                     dialogKuaidi=new Dialog_kuaidi(kuaidi_Activity.this,starttime,endtime,zt);
                     dialogKuaidi.show();


                 }

             }
         });

    }
//    private void scheduleDismiss() {
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                hud.dismiss();
//            }
//        }, 2000);
//    }
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            Log.e("json字符串" ,json);
            String ressult_date = null;

            try {
              JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONArray("data");
                for (int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    starttime=jsonObject1.getString("time");
                    endtime=jsonObject1.getString("ftime");
                    zt=jsonObject1.getString("context");



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
