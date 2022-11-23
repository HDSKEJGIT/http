package com.hds.core.http.easy;

import androidx.lifecycle.LifecycleOwner;

import com.hjq.http.EasyHttp;
import com.hjq.http.config.IRequestApi;
import com.hjq.http.listener.HttpCallback;
import com.hjq.http.request.HttpRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class EasyUtil {

//    private static EasyUtil instance;
//    private EasyUtil (){}
//
//
//    public static synchronized  EasyUtil getInstance() {
//        if (instance == null) {
//            instance = new EasyUtil();
//        }
//        return instance;
//    }

    private boolean isGetMethod;
    private boolean isExecute;

    private LifecycleOwner lifecycleOwner;
    public EasyUtil get(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        isGetMethod = true;
        return this;
    }

    public EasyUtil post(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        isGetMethod = false;
        return this;
    }

    private IRequestApi requestApi;
    public EasyUtil api(IRequestApi requestApi){
        this.requestApi = requestApi;
        return this;
    }

    private Class<?> tClass;
    public EasyUtil request(Class<?> tClass){
        this.tClass = tClass;
        isExecute = false;
        return this;
    }

    public void execute(Class<?> tClass){
        this.tClass = tClass;
        isExecute = true;
        startRequest();
    }



    private void startRequest(){
        HttpRequest httpRequest;
        if (isGetMethod){
            httpRequest = EasyHttp.get(lifecycleOwner);
        }else {
            httpRequest = EasyHttp.post(lifecycleOwner);
        }

        httpRequest = httpRequest.api(requestApi);
        if (isExecute){

        }else {
            Class baseClass = EasySetting.getInstance().getBaseClass();

            //httpRequest.request(new HttpCallback<type<?>>());
        }

    }
}
