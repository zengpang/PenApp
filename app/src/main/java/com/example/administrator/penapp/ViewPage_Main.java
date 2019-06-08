package com.example.administrator.penapp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.etsy.android.grid.StaggeredGridView;
import com.example.administrator.penapp.DIY_Dialog.sounds_dialog;
import com.example.administrator.penapp.DIY_View.DiyAdapter;
import com.example.administrator.penapp.DIY_View.Diylistdate;
import com.example.administrator.penapp.Entityclass.Money;
import com.example.administrator.penapp.ListAdapter.money_list;
import com.example.administrator.penapp.MyAdpterPage.Main_Adapter;
import com.example.administrator.penapp.SqlSever.CreateNumberSql;
import com.example.administrator.penapp.api_get_js.BaseActivity;
import com.example.administrator.penapp.api_get_js.NetConn;
import com.gigamole.navigationtabstrip.NavigationTabStrip;
import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class ViewPage_Main extends BaseActivity {
    ViewPager vp;
    ListView lv_time;
   FrameLayout sounds_re;
    HashMap<String,String> pmap;
    String pid;
    TextView textView1;
    ImageView gupiao_pictore;
    Button ceshi;
    String geturl=null;
    String result=null;
    PullToRefreshView pull_refsh;
    PullToRefreshView pull_shiyan;
    View headtitle;
    TextView golden_latestpri;
//    SharedPreferences sp;
    TextView golden_openpri;
    TextView golden_limit;
    TextView golden_yespri;
    ImageView bt_genxin_golden;
    String golden_url;
    HashMap<String,String>money_map;
    HashMap<String,String>golden_map;
    WaveSwipeRefreshLayout mwaveSwipeRefreshLayout;
    TextView head_text;
    List<Money>money_quick;
    String money_url;
    ListView list_golden;
    ImageView bt_qq_tz;
    ImageView bt_tianqi_tz;
    ImageView bt_selfcard_tz;
    ImageView bt_kuaidi_tz;
    ImageView bt_cidian_tz;
    ImageView bt_news_tz;
    CreateNumberSql number_sql;
    boolean sounds_kaiguan=true;
    private StaggeredGridView mGridView;
    private boolean mHasRequestedMore;
    private DiyAdapter mAdapter;
    Context mContext;
    sounds_dialog s_dialog;
    private ArrayList<String> mData;
    public static final String SAVED_DATA_KEY = "SAVED_DATA";
    Bundle state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        state=savedInstanceState;

        setContentView(intlayout());
        initlabel();
    }



    @Override
    protected int intlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initlabel() {

//        sp=getSharedPreferences("sounds_rember",0);
//       sounds_kaiguan=sp.getBoolean("sounds_key", Boolean.parseBoolean(""));
         s_dialog=new sounds_dialog(ViewPage_Main.this);
        ArrayList<View> Views=new ArrayList<>();
        money_quick=new ArrayList<>();
        mContext=ViewPage_Main.this;
        vp=(ViewPager)findViewById(R.id.main_mainpage);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        sounds_re=(FrameLayout)findViewById(R.id.re_sounds);
        NavigationTabStrip navigationTabStrip = (NavigationTabStrip) findViewById(R.id.title_main);
        navigationTabStrip.setTitles("备忘","生活","金融");
        number_sql=new CreateNumberSql(ViewPage_Main.this);

        golden_map=new HashMap<>();
        money_map=new HashMap<>();
        money_map.put("key","a709242301796dcb249f87733c8e8e35");
        money_map.put("page","1");
        money_map.put("pagesize","10");
        money_map.put("type","zhaiquan");
        golden_map.put("key","615e1e5b4407aba43b9b7d55ad01ce0d");

         golden_url="http://web.juhe.cn:8080/finance/gold/shgold"+"?"+urlencode(golden_map);
         money_url="http://v.juhe.cn/jingzhi/query.php"+"?"+urlencode(money_map);



        View V1=getLayoutInflater().inflate(R.layout.sounds_list_layout,null);
        View V2=getLayoutInflater().inflate(R.layout.live_layout,null);
        View V3=getLayoutInflater().inflate(R.layout.golden_layout,null);
        mGridView = (StaggeredGridView) V1.findViewById(R.id.sounds_list_grid_view);
        mwaveSwipeRefreshLayout=(WaveSwipeRefreshLayout)V1.findViewById(R.id.sounds_list_swape);

        golden_latestpri=(TextView)V3.findViewById(R.id.golden_new_data);
        golden_openpri=(TextView) V3.findViewById(R.id.openpri_golden);
        golden_yespri=(TextView)V3.findViewById(R.id.yespri_golden);
        golden_limit=(TextView)V3.findViewById(R.id.limit_golden);
        list_golden=(ListView)V3.findViewById(R.id.golden_date_listview);

        bt_qq_tz=(ImageView)V2.findViewById(R.id.QQ_icon);
        bt_cidian_tz=(ImageView)V2.findViewById(R.id.youdao_icon);
        bt_kuaidi_tz=(ImageView)V2.findViewById(R.id.songdekuai_ison);
        bt_tianqi_tz=(ImageView)V2.findViewById(R.id.tianqi_icon);
        bt_news_tz=(ImageView)V2.findViewById(R.id.news_icon);
        bt_selfcard_tz=(ImageView)V2.findViewById(R.id.card_icon);


//        lv_time=(ListView)V1.findViewById(R.id.list_time);
//        lv_time.setAdapter(new SoundrecordingList(this));


        Views.add(V1);
        Views.add(V2);
        Views.add(V3);
       mwaveSwipeRefreshLayout.setColorSchemeColors(R.color.white,R.color.white);
        mwaveSwipeRefreshLayout.setOnRefreshListener(new WaveSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<String> zData=new ArrayList<>();
                        DiyAdapter zAdapter = new DiyAdapter(mContext, R.id.txt_line1);


                            zData = Diylistdate.generateSampleData(ViewPage_Main.this);

                        for (String data : zData) {
                            zAdapter.add(data);
                        }


                        mGridView.setAdapter(zAdapter);
                        new diy_task().execute();
                    }
                },2000);

            }
        });

        vp.setAdapter(new Main_Adapter(Views));
        navigationTabStrip.setViewPager(vp,0);
        mAdapter = new DiyAdapter(mContext, R.id.txt_line1);

        if ( state!= null) {
            mData = state.getStringArrayList(SAVED_DATA_KEY);
        }

        if (mData == null) {
//            if(sounds_kaiguan) {
//                mData = Diylistdate.generateSampleData();
//                SharedPreferences.Editor editor=sp.edit();
//                editor.putBoolean("sounds_key",false);
//                editor.commit();
//            }
//            else
//            {
            mData = Diylistdate.generateSampleData(ViewPage_Main.this);
    //    }
        }

        for (String data : mData) {
            mAdapter.add(data);
        }


        mGridView.setAdapter(mAdapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             s_dialog.show();

            }

        });

        bt_genxin_golden=(ImageView)V3.findViewById(R.id.golden_genxing);
        bt_genxin_golden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Net(golden_url,netConn);
                Net(money_url,netConn1);

            }
        });
        Intent intent=getIntent();
        String head_text_huoqu=intent.getStringExtra("username");

        NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        headtitle=navigationView.getHeaderView(0);
        head_text=(TextView)headtitle.findViewById(R.id.headtitle_username);
        head_text.setText(head_text_huoqu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if(id==R.id.nav_gupiao)
                {
                    startActivity(new Intent(ViewPage_Main.this,money_Activity.class));
                    overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
                }
                else if(id==R.id.nav_map)
                {
                    startActivity(new Intent(ViewPage_Main.this,mapActivity.class));
                    overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
                }
                else if(id==R.id.nav_money)
                {
                    startActivity(new Intent(ViewPage_Main.this,zhuanhuan_activity.class));
                    overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
                }
                else if(id==R.id.nav_send)
                {
                    startActivity(new Intent(ViewPage_Main.this,fengxiang_activity.class));
                    overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
                }
                else if(id==R.id.nav_guanyu)
                {
                    startActivity(new Intent(ViewPage_Main.this,guanyu_Activity.class));
                    overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
                }
                else if(id==R.id.nav_setbt)
                {
                    startActivity(new Intent(ViewPage_Main.this,selfActivity.class));
                    overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
                }
                return true;
            }
        });
      bt_qq_tz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(ViewPage_Main.this,qq_activity.class));
              overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
          }
      });
      bt_kuaidi_tz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(ViewPage_Main.this,kuaidi_Activity.class));
              overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
              Log.e("点击", "已经点击: ");
          }
      });
      bt_selfcard_tz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(ViewPage_Main.this,card_Activity.class));
              overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);

          }
      });
      bt_news_tz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(ViewPage_Main.this,loading_news_Activity.class));
              overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
          }
      });

      bt_tianqi_tz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(ViewPage_Main.this,start_activity.class));
              overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
          }
      });
      bt_cidian_tz.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              startActivity(new Intent(ViewPage_Main.this,cidian_Activity.class));
              overridePendingTransition(R.anim.anim_activity_in,R.anim.anim_activity_out);
          }
      });

    }
    public void refrsh()
    {
        finish();
        startActivity(new Intent(ViewPage_Main.this,ViewPage_Main.class));
    }
    NetConn netConn=new NetConn() {
        @Override
        public void netok(String json) {
            Log.e("json字符串" ,json);
             String latestpri=null;
             String limit=null;
             String yespri=null;
             String openpri=null;



            try {
                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONArray("result");
              for(int i=0;i<jsonArray.length();i++)
              {
                  JSONObject jsonObject1=jsonArray.getJSONObject(i);

                  latestpri=jsonObject1.getJSONObject("1").getString("latestpri");
                  limit=jsonObject1.getJSONObject("1").getString("limit");
                  yespri=jsonObject1.getJSONObject("1").getString("yespri");
                  openpri=jsonObject1.getJSONObject("1").getString("openpri");

              }
              golden_latestpri.setText(latestpri);
              golden_limit.setText("涨跌幅"+limit);
              golden_openpri.setText("开盘价:"+openpri+"元");
              golden_yespri.setText("昨收价"+yespri+"元");




            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void netErroe(String errorMap) {


        }
    };
    private class diy_task extends AsyncTask<Void,Void,String[]>
    {


        @Override
        protected String[] doInBackground(Void... voids) {
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] strings) {
            mwaveSwipeRefreshLayout.setRefreshing(false);
            super.onPostExecute(strings);
        }
    }
    NetConn netConn1=new NetConn() {
        @Override
        public void netok(String json) {
            Log.e("净值数据: ", json);
            try {
                JSONObject jsonObject=new JSONObject(json);
                JSONArray jsonArray=jsonObject.getJSONArray("result");
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject jsonObject1=jsonArray.getJSONObject(i);
                    Money money=new Money();
                    money.setJjlx(jsonObject1.getString("jjlx"));
                    money.setNav_a(jsonObject1.getString("nav_a"));
                    money.setNav_date(jsonObject1.getString("nav_date"));
                    money.setPer_nav(jsonObject1.getString("per_nav"));
                    money.setSg_states(jsonObject1.getString("sg_states"));
                    money.setSname(jsonObject1.getString("sname"));
                    money.setTotal_nav(jsonObject1.getString("total_nav"));
                    money.setNav_rate(jsonObject1.getString("nav_rate"));
                    money.setYesterday_nav(jsonObject1.getString("yesterday_nav"));
                    money.setSymbol(jsonObject1.getString("symbol"));

                    money_quick.add(money);




                }
                list_golden.setAdapter(new money_list(money_quick,ViewPage_Main.this));





            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void netErroe(String errorMap) {

        }
    };


    }



