package soexample.umeng.com.happytimes.model;

import java.util.Map;

import soexample.umeng.com.happytimes.callback.ICallBack;

/**
 * date:2019/1/3
 * author:冯泽林(asus)
 * function:
 */
public interface IModel {
    void requestData(String url, Map<String, String> params, Class clazz, ICallBack callBack);
}