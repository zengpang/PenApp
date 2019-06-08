package com.example.administrator.penapp.DIY_View;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.etsy.android.grid.util.DynamicHeightTextView;
import com.example.administrator.penapp.R;
import com.example.administrator.penapp.SqlSever.CreateSoundsSql;
import com.example.administrator.penapp.SqlSever.sqlsever_sounds;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class DiyAdapter extends ArrayAdapter<String> {
    private static final String TAG="DiyAdapter";
    private  LayoutInflater mLayoutInflater;
    private  Random mRandom;
    private  ArrayList<Integer> mBackgroundColors;
    CreateSoundsSql sql;
    sqlsever_sounds sounds_sql;
    String path;
    String name;
    String title_text;
    private MediaRecorder recorder;    //录音
    private MediaPlayer player;      //播放声音
    private File voiceFile;


    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();


    public DiyAdapter(@NonNull Context context, int resource) {

        super(context, resource);

        mLayoutInflater = LayoutInflater.from(context);
        mRandom = new Random();

        sql=new CreateSoundsSql(getContext());

         sounds_sql=new sqlsever_sounds(sql);

        mBackgroundColors = new ArrayList<Integer>();
        mBackgroundColors.add(R.color.orange_list_item);
        mBackgroundColors.add(R.color.green_list_item);
        mBackgroundColors.add(R.color.blue_list_item);
        mBackgroundColors.add(R.color.yellow_list_item);
        mBackgroundColors.add(R.color.grey_list_item);

    }

    static  class ViewHolder{
        DynamicHeightTextView txtLineOne;
        Button btnGo;
        TextView title;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder vh;
        name=sounds_sql.sounds_name_select((position+1)+"");
        path=sounds_sql.sounds_path_select((position+1)+"");
        title_text=sounds_sql.title_select((position+1)+"");


        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.sounds_list_itemlayout, parent, false);

            vh = new ViewHolder();
            vh.txtLineOne = (DynamicHeightTextView) convertView.findViewById(R.id.txt_line1);
            vh.btnGo = (Button) convertView.findViewById(R.id.btn_go);
            vh.title=(TextView)convertView.findViewById(R.id.title);

            convertView.setTag(vh);
        }
        else {
            vh = (ViewHolder) convertView.getTag();
        }

        double positionHeight = getPositionRatio(position);
        int backgroundIndex = position >= mBackgroundColors.size() ?
                position % mBackgroundColors.size() : position;

        convertView.setBackgroundResource(mBackgroundColors.get(backgroundIndex));

        Log.d(TAG, "getView position:" + position + " h:" + positionHeight);

        vh.txtLineOne.setHeightRatio(positionHeight);
        vh.txtLineOne.setText(getItem(position) + (name));
         vh.title.setText(title_text);
        vh.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {


                try {
                    //初始化
                    player = new MediaPlayer();
                    //设置播放源
                    player.setDataSource(path);
                    //准备
                    player.prepare();
                    //开始播放
                    player.start();

                    player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mediaPlayer) {
                            //停止播放，释放资源，删除录音文件
                            player.stop();
                            player.release();

                        }
                    });
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        });

        return convertView;
    }
    private double getPositionRatio(final int position) {
        double ratio = sPositionHeightRatios.get(position, 0.0);
        // if not yet done generate and stash the columns height
        // in our real world scenario this will be determined by
        // some match based on the known height and width of the image
        // and maybe a helpful way to get the column height!
        if (ratio == 0) {
            ratio = getRandomHeightRatio();
            sPositionHeightRatios.append(position, ratio);
            Log.d(TAG, "getPositionRatio:" + position + " ratio:" + ratio);
        }
        return ratio;
    }

    private double getRandomHeightRatio() {
        return 1; // height will be 1.0 - 1.5 the width
    }
}
