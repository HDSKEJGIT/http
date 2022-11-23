package com.hds.core.http.easy;

import android.app.Application;

import com.hds.core.http.PersistenceCookieJar;
import com.hjq.http.EasyConfig;

import java.util.Map;

import okhttp3.OkHttpClient;

public class EasySetting<T> {
    private EasyConfig config;
    private boolean isStart;


    private static EasySetting instance;
    private EasySetting(){}


    public static synchronized EasySetting getInstance() {
        if (instance == null) {
            instance = new EasySetting();
        }
        return instance;
    }



    public EasySetting<T> start() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(new PersistenceCookieJar())
                .build();

        config = EasyConfig.with(okHttpClient);
        isStart = true;
        return this;
    }

    // 是否打印日志
    public EasySetting<T> setLogEnabled(boolean isLogEnable) {
        checkStart();
        config.setLogEnabled(isLogEnable);
        return this;
    }

    //测试地址
    private DebugServer debugServer;
    public EasySetting<T> setDebugService(String server) {
        checkStart();
        debugServer = new DebugServer(server);
        return this;
    }

    //正式地址
    private ReleaseServer releaseServer;
    public EasySetting<T> setReleaseServer(String server) {
        checkStart();
        releaseServer = new ReleaseServer(server);
        return this;
    }

    //设置项目buildConfig
    private Boolean isDebug;

    public EasySetting<T> setBuildConfig(Boolean isDebug) {
        checkStart();
        this.isDebug = isDebug;
        return this;
    }


    // 设置请求处理策略
    private Class<T> tClass;
    public EasySetting<T> setBaseResult(Application application, Class<T> tClass, ExceptionListener listener) {
        this.tClass = tClass;
        checkStart();
        config.setHandler(new RequestHandler(application,tClass, listener));
        return this;
    }

    // 设置请求重试次数
    public EasySetting<T> setRetryCount(int count) {
        checkStart();
        config.setRetryCount(count);
        return this;
    }

    //设置请求重试时间
    public EasySetting<T> setRetryTime(long time) {
        checkStart();
        config.setRetryTime(time);
        return this;
    }

    // 添加全局请求参数
    public EasySetting<T> addParam(Map<String, String> params) {
        checkStart();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            config.addParam(entry.getKey(), entry.getValue());
        }
        return this;
    }

    //添加请求头
    public EasySetting<T> addHeader(Map<String, String> params) {
        checkStart();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            config.addHeader(entry.getKey(), entry.getValue());
        }
        return this;
    }

    public void end() {
        // 设置服务器配置
        if (null != debugServer && null != releaseServer) {
            if (null == isDebug) throw new NullPointerException("setBuildConfig() not implements");

            if (isDebug) {
                config.setServer(debugServer);
            } else {
                config.setServer(releaseServer);
            }
        } else {
            if (null == debugServer && null == releaseServer) {
                throw new NumberFormatException("你的请求地址呢！");
            } else {
                if (null == debugServer) {
                    config.setServer(releaseServer);
                } else {
                    config.setServer(debugServer);
                }
            }
        }

        config.into();
    }

    private void checkStart() {
        if (!isStart) {
            throw new NullPointerException("请先调用start()进行初始化");
        }
    }

    public Class<T> getBaseClass(){
        return this.tClass;
    }

    public interface ExceptionListener {
        void tokenExceptionListener();
    }
}
