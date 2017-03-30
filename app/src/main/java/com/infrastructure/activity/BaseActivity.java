package com.infrastructure.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import com.infrastructure.net.RequestCallback;
import com.infrastructure.net.RequestManager;

/**
 * Created by 马彬彬 on 2016/6/25.
 */
public abstract class  BaseActivity  extends AppCompatActivity {
    private RequestManager requestManager= null ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestManager = new RequestManager(this);
        super.onCreate(savedInstanceState);

        initVariables();
        initViews(savedInstanceState);
        loadData();
        System.out.print("HELLO");
    }

    protected abstract void initVariables() ;
    protected abstract void initViews(Bundle savedInstanceState);
    protected abstract void loadData();

    private String getAndroidPageName(String key){
        String pageName = null ;
        int pos = key.indexOf(",") ;
        if (pos == -1){
            pageName = key;
        }else{
            pageName=key.substring(0,pos) ;
        }

        return pageName;
    }

    public void gotoAnyWhere(String url){
        if (url == null){
            return;
        }
        String pageName = getAndroidPageName(url) ;

        if (pageName == null ||pageName.trim() == ""){
            return;
        }
        Intent intent = new Intent( ) ;
        int pos = url.indexOf(":");
        if (pos>0){
            String strParams = url.substring(pos) ;
            String[] pairs = strParams.split("&") ;
            for (String strKeyAndValue : pairs){
                String[] arr = strKeyAndValue.split("=") ;
                String key = arr[0] ;
                String value = arr[1] ;
                if (value.startsWith("(int)")){
                    intent.putExtra(key,Integer.valueOf(value.substring(5))) ;
                }else if (value.startsWith("(Double)")){
                    intent.putExtra(key,Double.valueOf(8)) ;
                }else{
                    intent.putExtra(key,value) ;
                }
            }
        }
        try{
            intent.setClass(BaseActivity.this,Class.forName(pageName)) ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        startActivity(intent);
    }



    @Override
    protected void onDestroy() {
        if (requestManager != null){
            requestManager.cancelRequest();
        }
        super.onDestroy();
    }

    protected void onPause() {
        /**
         * 在activity停止的时候同时设置停止请求，停止线程请求回调
         */
        if (requestManager != null) {
            requestManager.cancelRequest();
        }
        super.onPause();
    }

    public RequestManager getRequestManager() {
        return requestManager;
    }
}
