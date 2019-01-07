package soexample.umeng.com.happytimes.model;

import com.google.gson.Gson;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Retrofit;
import soexample.umeng.com.happytimes.callback.ICallBack;
import soexample.umeng.com.happytimes.notwork.RetrofitManager;

/**
 * date:2019/1/3
 * author:冯泽林(asus)
 * function:
 */
public class IModeImpl implements IModel {
    @Override
    public void requestData(String url, Map<String, String> params, final Class clazz, final ICallBack callBack) {
        RetrofitManager.getInstance().post(url, params).result(new RetrofitManager.HttpListener() {
            @Override
            public void OnSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                if (callBack != null) {
                    callBack.success(o);
                }
            }

            @Override
            public void OnFailed(String error) {
                if (callBack != null) {
                    callBack.failed(error);
                }
            }
        });
    }

    public void get(String url, Map<String, String> params, final Class clazz, final ICallBack callBack) {
        RetrofitManager.getInstance().get(url).result(new RetrofitManager.HttpListener() {
            @Override
            public void OnSuccess(String data) {
                Object o = new Gson().fromJson(data, clazz);
                if(callBack!=null){
                    callBack.success(o);
                }
            }

            @Override
            public void OnFailed(String error) {
                    if(callBack!=null){
                        callBack.failed(error);
                    }
            }
        });
    }
}
