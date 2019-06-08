package com.example.administrator.penapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.administrator.penapp.DIY_listener.SensorManagerHelper;
import com.example.administrator.penapp.MyDialog.Dialog_QQ;
import com.example.administrator.penapp.MyDialog.Dialog_QQ_close;
import com.example.administrator.penapp.api_get_js.AppConfig_url;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.skyfishjy.library.RippleBackground;
import com.tomer.fadingtextview.FadingTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

public class qq_activity extends BaseActivity {
     String qq_number_activity;
    HashMap<String,String> pmap;
   SharedPreferences sp;
    String strurl;
    String s="服务器已经跟不上你的手速了，请再次尝试";
    View view1;
    private KProgressHUD hud;


    @Override
    protected int intlayout() {
        return R.layout.qq_layout;
    }

    @Override
    protected void initlabel() {

        View view=View.inflate(qq_activity.this,R.layout.mydialog_qqlayout,null);
        Dialog_QQ mydialog=new Dialog_QQ(qq_activity.this,view);
        pmap=new HashMap<>();

        mydialog.setCancelable(true);

        mydialog.show();
        sp=getSharedPreferences("number",0);
        strurl="http://japi.juhe.cn/qqevaluate/qq";
        qq_number_activity=sp.getString("qq_number","");
        Log.e("qq_number_activity: ", qq_number_activity);
        pmap.put("key","0f113651e7256d231d5ddfdca5ba9e4c");
        pmap.put("qq",qq_number_activity);
        strurl = strurl+"?"+urlencode(pmap);
//        String[]texts=new String[]{"摇一摇","测QQ号码吉凶"};
//        FadingTextView fad_text=(FadingTextView)findViewById(R.id.qq_fandview);
//        fad_text.setTexts(texts);
        final RippleBackground rippleBackground=(RippleBackground)findViewById(R.id.QQ_content);

        new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                rippleBackground.startRippleAnimation();
                return false;
            }
        }).sendEmptyMessageDelayed(0,2000);
        SensorManagerHelper sensorManagerHelper=new SensorManagerHelper(this);
        sensorManagerHelper.setOnShakeListener(new SensorManagerHelper.OnShakeListener() {
            @Override
            public void onShake() {
              Net(strurl,netConn);
//                hud = KProgressHUD.create(qq_activity.this)
//                          .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
//                 scheduleDismiss();
                view1=View.inflate(qq_activity.this,R.layout.qq_layout_jieguo,null);

                Dialog_QQ_close dialog_qq_close=new Dialog_QQ_close(qq_activity.this,view1,s);
                dialog_qq_close.show();
            }
        });
    }
    private void scheduleDismiss() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
           @Override
           public void run() {
               hud.dismiss();
           }
        }, 2000);
    }
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            Log.e("json字符串" ,json);
            String ressult_date = null;
            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(json);
                ressult_date=jsonObject.getJSONObject("result").getJSONObject("data") .getString( "conclusion");







                Log.e("qq", ressult_date);
               s=ressult_date;


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void netErroe(String errorMap) {

        }
    };


}
