package com.example.administrator.penapp.DIY_Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.penapp.DIY_List.Sounds;
import com.example.administrator.penapp.R;
import com.example.administrator.penapp.SqlSever.CreateSoundsSql;
import com.example.administrator.penapp.SqlSever.sqlsever_sounds;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class sounds_dialog extends AlertDialog {
    EditText et_title;
    EditText et_text;
    EditText et_name;

    RadioGroup rad_sounds;
    RadioButton xuanze;
    Button qd_ly;
    String title;
    String text="无";
    String biaoqian;
    String mpath;
    String sounds_name;
    List<Sounds> list_sounds;
    SQLiteDatabase db=null;
    String str;
    private int granted;    //用于保存权限是否被授予的凭证

    private MediaRecorder recorder;    //录音
    private MediaPlayer player;      //播放声音
    private File voiceFile;
    CreateSoundsSql sounds_sql;
    public sounds_dialog(Context context) {

        super(context);
    }


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soundsdialog_layout);
        et_text=(EditText)findViewById(R.id.sounds_text);
        et_title=(EditText)findViewById(R.id.sounds_dialog_title);
        rad_sounds=(RadioGroup)findViewById(R.id.xuanze_radio_all);
        et_name=(EditText)findViewById(R.id.wenjian_name_sounds_dialog);
        list_sounds=new ArrayList<>();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        SimpleDateFormat formatter   =   new   SimpleDateFormat   ("yyyy年MM月dd日 HH:mm:ss");
        Date curDate =  new Date(System.currentTimeMillis());
        str   =   formatter.format(curDate);
        sounds_sql=new CreateSoundsSql(getContext());
        final sqlsever_sounds luyin_sql=new sqlsever_sounds(sounds_sql);

        qd_ly=(Button)findViewById(R.id.xuanze_right);
        qd_ly.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                    try {
                        //录音
                        recorder = new MediaRecorder();
                        //设置声音来源
                        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                        //设置声音格式,MPEG_4:音频、视频的标准格式
                        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                        //设置声音编码
                        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                        //准备文件用于承载录制的声音
                        voiceFile = File.createTempFile(sounds_name, "mp3");
                        mpath=voiceFile.getAbsolutePath();
                        //设置声音的输出录音为刚刚准备好的文件所在的位置
                        recorder.setOutputFile(mpath);

                        //准备
                        recorder.prepare();
                        //开始录制
                        recorder.start();

                        qd_ly.setText("请大声说话...");
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                return false;
            }
        });

        qd_ly.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //手指按下
                    if(granted != PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(getContext(),"您不具备录制音频的权限",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    title=et_title.getText().toString();
                    text=et_text.getText().toString();
                    xuanze=(RadioButton)findViewById(rad_sounds.getCheckedRadioButtonId());
                    biaoqian=xuanze.getText().toString();
                    sounds_name=et_name.getText().toString();

                    if(title=="")
                    {
                        Toast.makeText(getContext(),"标题不能为空",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    else if (biaoqian=="")
                    {
                        Toast.makeText(getContext(),"请选择标签",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                    else if (sounds_name=="")
                    {
                        Toast.makeText(getContext(),"请输入音频名称",Toast.LENGTH_SHORT).show();
                        return true;
                    }
                }
                if(granted == PackageManager.PERMISSION_GRANTED &&motionEvent.getAction() == MotionEvent.ACTION_UP){
                    //手指松开，停止录音并播放
                    recorder.stop();        //停止
                    recorder.release();     //释放资源
                    luyin_sql.sql_ly(sounds_name,mpath,str,text,title);


                    qd_ly.setText("录音完成");
                    Toast.makeText(getContext(),"录音完成",Toast.LENGTH_SHORT).show();
                  dismiss();

                }
                return false;
            }
        });






    }
}
