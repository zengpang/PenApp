package com.example.administrator.penapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

public class mapActivity extends AppCompatActivity {
    MapView mapView_dingwei;
    BaiduMap mBaidumap;
    private boolean  isFirstLocate = true;
    private LocationClient mLocationClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.map_layout);
        mapView_dingwei=(MapView)findViewById(R.id.map_baidumap);
        mBaidumap=mapView_dingwei.getMap();
        mBaidumap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);

        initLocation();
        requestLocation();




    }
    private void requestLocation() {

        LocationClientOption  option = new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }
    private void initLocation() {
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                StringBuilder currentPosition = new StringBuilder();


                if(bdLocation.getLocType() == BDLocation.TypeGpsLocation ||bdLocation.getLocType() == BDLocation.TypeNetWorkLocation){

                    if(isFirstLocate){
                        LatLng ll = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                        MapStatus.Builder builder = new MapStatus.Builder();
                        builder.target(ll).zoom(18.0f);
                        mBaidumap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(builder.build()));
                        isFirstLocate = false;
                    }
                    MyLocationData.Builder  locationBuilder = new MyLocationData.Builder();
                    locationBuilder.latitude(bdLocation.getLatitude());
                    locationBuilder.longitude(bdLocation.getLongitude());
                    MyLocationData locationData = locationBuilder.build();
                    mBaidumap.setMyLocationData(locationData);

            }
            }
        });
//        mLocationClient.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mapView_dingwei.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView_dingwei.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        mapView_dingwei.onDestroy();
        mBaidumap.setMyLocationEnabled(false);
    }
}
