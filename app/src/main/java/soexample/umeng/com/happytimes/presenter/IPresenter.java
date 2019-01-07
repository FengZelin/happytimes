package soexample.umeng.com.happytimes.presenter;

import java.util.Map;

/**
 * date:2019/1/3
 * author:冯泽林(asus)
 * function:
 */
public interface IPresenter {
    void startRequqst(String url, Map<String, String> parmas, Class clazz);
}
