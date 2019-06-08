package com.example.administrator.penapp.api_get_js;





import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class gupiao_getjson {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;

    public static final int DEF_READ_TIMEOUT = 30000;
    public  static  String url="http://web.juhe.cn:8080/finance/stock/";
    public static String userAgent= "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";
    public static String APPKEY="6db9a65106bfa373c5709f9849d433c8";
    public  static  Net_GET net_get=new Net_GET();
    public  String getRequest_hs() {
        String url_hs = url + "hs";
        String result = null;

        Map params = new HashMap();
        params.put("gid", "");
        params.put("key", APPKEY);
        try {
            result = net_get.net(url_hs, params, "GET");
            JSONObject object = JSONObject.fromObject(result);

            if (object.getInt("error_code") == 0) {
                System.out.println(object.get("result"));
                return object.get("result").toString();
            } else {
                System.out.println(object.get("error_code") + ":" + object.get("reason"));
                return object.get("result").toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

        //2.股票型基金
        public  void getRequest2(){
        String result =null;
        String url ="http://web.juhe.cn:8080/fund/netdata/stock";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//APPKEY值

        try {
            result =net_get.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        //3.普通债券型基金
        public  void getRequest3(){
        String result =null;
        String url ="http://web.juhe.cn:8080/fund/netdata/bond";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//APPKEY值

        try {
            result =net_get.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        //4.货币型基金
        public  void getRequest4(){
        String result =null;
        String url ="http://web.juhe.cn:8080/fund/netdata/monet";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//APPKEY值

        try {
            result =net_get.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        //5.封闭型基金
        public  void getRequest5(){
        String result =null;
        String url ="http://web.juhe.cn:8080/fund/netdata/close";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//APPKEY值

        try {
            result =net_get.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        //6.创新封基
        public  void getRequest6(){
        String result =null;
        String url ="http://web.juhe.cn:8080/fund/netdata/innov";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//APPKEY值

        try {
            result =net_get.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        //7.LOF
        public  void getRequest7(){
        String result =null;
        String url ="http://web.juhe.cn:8080/fund/netdata/lof";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//APPKEY值

        try {
            result =net_get.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        //8.ETF
        public  void getRequest8(){
        String result =null;
        String url ="http://web.juhe.cn:8080/fund/netdata/etf";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//APPKEY值

        try {
            result =net_get.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

        //9.QDII
        public  void getRequest9(){
        String result =null;
        String url ="http://web.juhe.cn:8080/fund/netdata/qdii";//请求接口地址
        Map params = new HashMap();//请求参数
        params.put("key",APPKEY);//APPKEY值

        try {
            result =net_get.net(url, params, "GET");
            JSONObject object = JSONObject.fromObject(result);
            if(object.getInt("error_code")==0){
                System.out.println(object.get("result"));
            }else{
                System.out.println(object.get("error_code")+":"+object.get("reason"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    }



