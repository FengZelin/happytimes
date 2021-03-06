package soexample.umeng.com.happytimes.notwork;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * date:2019/1/3
 * author:冯泽林(asus)
 * function:
 */
public class RetrofitManager {
    private final String BASE_URL = "http://172.17.8.100/small/";

    private static RetrofitManager mRetrofitManager;

    public static synchronized RetrofitManager getInstance() {
        if (mRetrofitManager == null) {
            mRetrofitManager = new RetrofitManager();
        }
        return mRetrofitManager;
    }

    private BaseApis mBaseApis;

    public RetrofitManager() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(15, TimeUnit.SECONDS);
        builder.writeTimeout(15, TimeUnit.SECONDS);
        builder.addInterceptor(interceptor);
        builder.retryOnConnectionFailure(true);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .client(client)
                .build();
        mBaseApis = retrofit.create(BaseApis.class);
    }

    /**
     * 可以这样生成Map<String, RequestBody> requestBodyMap
     * Map<String, String> requestDataMap这里面放置上传数据的键值对。
     */
    public Map<String, RequestBody> generateRequsetBody(Map<String, String> requestDataMap) {
        Map<String, RequestBody> requestBodyMap = new HashMap<>();
        for (String key : requestDataMap.keySet()) {
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),
                    requestDataMap.get(key) == null ? "" : requestDataMap.get(key));
            requestBodyMap.put(key, requestBody);
        }
        return requestBodyMap;
    }
//    get请求
    public RetrofitManager get(String url) {
        mBaseApis.get(url)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
        return mRetrofitManager;
    }
//    Post请求
    public  RetrofitManager post(String url,Map<String ,String > map){
        if(map == null){
            map=new HashMap<>();
        }
        mBaseApis.post(url,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mObserver);
        return mRetrofitManager;
    }
    private Observer mObserver = new Observer<ResponseBody>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (listener!=null) {
                listener.OnFailed(e.getMessage());
            }
        }

        @Override
        public void onNext(ResponseBody responseBody) {
            try {
                String data = responseBody.string();
                if (listener != null) {
                    listener.OnSuccess(data);
                }
            } catch (IOException e) {
                e.printStackTrace();
                if(listener!=null){
                    listener.OnFailed(e.getMessage());
                }
            }
        }
    };

    public interface HttpListener {
        void OnSuccess(String data);

        void OnFailed(String error);
    }

    private HttpListener listener;

    public void result(HttpListener listener) {
        this.listener = listener;
    }
}
