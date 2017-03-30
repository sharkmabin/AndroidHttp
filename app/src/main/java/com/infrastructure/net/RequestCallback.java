package com.infrastructure.net;

/**
 * Created by 马彬彬 on 2016/6/25.
 */
public interface RequestCallback {
    public void onSuccess(String content) ;
    public void onFail(String errorMessage) ;
    public void onCookieExpired();
}
