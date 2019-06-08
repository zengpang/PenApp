package com.example.administrator.penapp.MyDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.penapp.R;

public class Dialog_kuaidi extends Dialog {
    TextView kuaidi_starttime;
    TextView kuaidi_endtime;
    TextView kuaidi_zt;
    ImageView img_close;
    public Dialog_kuaidi(@NonNull Context context,String startime,String endtime,String zt) {
        super(context);
        setContentView(R.layout.kuaidi_dialog_layout);
        kuaidi_endtime=(TextView)findViewById(R.id.kuaidi_endtime);
        kuaidi_starttime=(TextView)findViewById(R.id.kuaidi_starttime);
        img_close=(ImageView)findViewById(R.id.kuaidi_close);
        kuaidi_zt=(TextView)findViewById(R.id.kuaidi_zt);
        kuaidi_starttime.setText("起送时间："+startime);
        kuaidi_endtime.setText("预计到达时间:"+endtime);
        kuaidi_zt.setText("目前状态:"+zt);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }


}
