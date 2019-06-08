package com.example.administrator.penapp.MyDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.penapp.R;

public class Dialog_QQ_close extends Dialog {
    String qq_jieguo;
    TextView jieguo;
    TextView close;
    TextView qd;


    public Dialog_QQ_close(@NonNull Context context,View layout,String s) {
        super(context);
        setContentView(layout);
        Window window=getWindow();
        WindowManager.LayoutParams params=window.getAttributes();
        qq_jieguo=s;
        jieguo=(TextView)findViewById(R.id.qq_dialog_jieguo);
        close=(TextView)findViewById(R.id.qq_dialog_close);
        qd=(TextView)findViewById(R.id.qq_dialog_qd);
        jieguo.setText(qq_jieguo);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }
}
