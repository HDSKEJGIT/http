package com.core.util;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

//一个Map，继承HashMap，通用Map
public class AMap extends HashMap {

    public AMap() {

    }

    public static AMap toAMap(Object obj) throws Exception{
        if(obj==null) return null;
        return UtilObject.objToBean(obj,AMap.class);
    }
    public AMap(String keys, Object... value) {
        String[] keyArray = keys.split(",");
        int length = keyArray.length>value.length?keyArray.length:value.length;
        if(length==0) return;
        for (int i=0;i<length;i++) {
            this.put(UtilString.trim(keyArray[i]),value[i]);
        }
    }

    public void put(String keys, Object... value) {
        String[] keyArray = keys.split(",");
        int length = keyArray.length>value.length?keyArray.length:value.length;
        if(length==0) return;
        for (int i=0;i<length;i++) {
            this.put(UtilString.trim(keyArray[i]),value[i]);
        }
    }

    public void reput(String keys, Object... value) {
        this.clear();
        String[] keyArray = keys.split(",");
        int length = keyArray.length>value.length?keyArray.length:value.length;
        if(length==0) return;
        for (int i=0;i<length;i++) {
            this.put(UtilString.trim(keyArray[i]),value[i]);
        }
    }

    public String getString(String key) {
        return UtilString.objToString(this.get(key));
    }

    public Integer getInteger(String key) {
        return UtilNum.objToInteger(this.get(key));
    }

    public Double getDouble(String key) {
        return UtilNum.objToDouble(this.get(key));
    }

//    //获取Date
//    public String getDateString(String key,String format) throws Exception{
//        Object obj = this.get(key);
//        if (obj == null)
//            return null;
//        if (obj.getClass().equals(Date.class))
//            return UtilDateTime.toDateString((Date)this.get(key),format);
//        else if (obj.getClass().equals(String.class)) {
//            if ("".equals(obj)) {
//                return null;
//            }
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//            Date date = df.parse((String)obj);
//            return UtilDateTime.toDateString(date,format);
//        } else
//            throw new Exception("未知的日期格式");
//    }
}
