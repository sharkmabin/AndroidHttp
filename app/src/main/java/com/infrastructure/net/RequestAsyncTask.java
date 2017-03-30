package com.infrastructure.net;

import android.os.AsyncTask;

import com.alibaba.fastjson.JSON;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

/**
 * Created by 马彬彬 on 2016/6/25.
 */
public abstract class RequestAsyncTask extends AsyncTask<String,Void,Response> {

    public abstract void onSuccess(String content) ;
    public abstract void onFail(String errorMessage) ;
    private static final int READ_TIMEOUT = 5000;
    private static final int CONNECT_TIMEOUT = 5000;
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        if (response.hasError()){
            onFail(response.getErrorMessage());
        }else
        {
            onSuccess(response.getResult());
        }
    }

    @Override
    protected Response doInBackground(String... params) {
        return getResponseFromUrl(params[0]);
    }

    private Response getResponseFromUrl(String url){
        Response response = new Response();
        HttpGet get = new HttpGet(url);
        String strResponse = null;
        try {
            HttpParams httpParameters = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParameters, 8000);
            HttpClient httpClient = new DefaultHttpClient(httpParameters);

            HttpResponse httpResponse = httpClient.execute(get);
            if (httpResponse.getStatusLine().getStatusCode()
                    == HttpStatus.SC_OK) {
                strResponse = EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (Exception e) {
            response.setErrorType(-1);
            response.setError(true);
            response.setErrorMessage(e.getMessage());
        }

        if (strResponse == null) {
            response.setErrorType(-1);
            response.setError(true);
            response.setErrorMessage("网络异常，返回空值");
        } else {
            response = JSON.parseObject(strResponse, Response.class);
        }
        return  response ;
    }


}
