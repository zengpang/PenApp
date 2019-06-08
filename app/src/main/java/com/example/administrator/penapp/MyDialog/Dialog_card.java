package com.example.administrator.penapp.MyDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.penapp.R;

public class Dialog_card extends Dialog {
    String card_sex;
    String card_day;
    TextView sex_text;
    TextView day_text;
    TextView dizhi_text;
    TextView qd;
    TextView close;

    String card_dizhi;

    public Dialog_card(@NonNull Context context, String sex, String day, String dizhi) {
        super(context);
        card_sex=sex;
        card_day=day;
        card_dizhi=dizhi;
        setContentView(R.layout.card_dialog_layout);
        dialog_intival();
    }
    public void dialog_intival()
    {
        sex_text=(TextView)findViewById(R.id.card_sex_text);
        day_text=(TextView)findViewById(R.id.card_brithday_text);
        dizhi_text=(TextView)findViewById(R.id.card_dizhi_text);
        sex_text.setText(card_sex);
        day_text.setText(card_day);
        dizhi_text.setText(card_dizhi);
        qd=(TextView)findViewById(R.id.dialog_qd);
        close=(TextView)findViewById(R.id.dialog_close);
        qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
