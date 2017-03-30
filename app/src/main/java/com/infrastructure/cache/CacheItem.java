package com.infrastructure.cache;

import java.io.Serializable;

/**

 */
public class CacheItem implements Serializable {
    /** 存储的key */
    private final String key;

    /** JSON字符串 */
    private String data;

    /** 过期时间的时间戳 */
    private long timeStamp = 0L;

    public CacheItem(String key, String data, long expiredTime) {
        this.key = key;
        this.data = data;
        this.timeStamp = System.currentTimeMillis() + expiredTime * 1000;
    }

    public String getKey() {
        return key;
    }

    public String getData() {
        return data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }
}
