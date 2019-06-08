package com.example.administrator.penapp.MyDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.penapp.R;

public class Dialog_start extends Dialog {
    TextView qd;
    TextView qx;
    TextView health;
    TextView color;
    TextView summary;

    public Dialog_start(@NonNull Context context,String health_text,String color_text,String summary_text) {
        super(context);
        setContentView(R.layout.start_dialog_layout);
        health=(TextView)findViewById(R.id.start_dialog_health);
        color=(TextView)findViewById(R.id.start_dialog_color);
        summary=(TextView)findViewById(R.id.start_dialog_summary);
        qd=(TextView)findViewById(R.id.start_dialog_qd);
        qx=(TextView)findViewById(R.id.start_dialog_close);

        health.setText(health_text);
        color.setText(color_text);
        summary.setText(summary_text);
       qd.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dismiss();
           }
       });
       qx.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               dismiss();
           }
       });
    }

}
