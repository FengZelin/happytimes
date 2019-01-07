package soexample.umeng.com.happytimes.view;

import soexample.umeng.com.happytimes.bean.Bean;
import soexample.umeng.com.happytimes.bean.CircleBean;
import soexample.umeng.com.happytimes.bean.HomeBean;
import soexample.umeng.com.happytimes.bean.RegisterBean;
import soexample.umeng.com.happytimes.bean.ShowBean;

/**
 * date:2019/1/3
 * author:冯泽林(asus)
 * function:
 */
public interface IView {
    void getBannersuccess(HomeBean bean);

    void getBannerFailed(String error);

    void getDataSuccess(Bean bean);

    void getDataFail(String error);

    void getAddSuccess(RegisterBean bean);

    void getAddFailed(String error);

    void getRecyclerShop(ShowBean bean);

    void getFailedRecyclerShop(String error);

    void getCircleRecycler(CircleBean bean);

    void getCirleFailed(String error);
}
