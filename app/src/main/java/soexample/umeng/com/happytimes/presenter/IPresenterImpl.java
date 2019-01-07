package soexample.umeng.com.happytimes.presenter;

import java.util.Map;

import soexample.umeng.com.happytimes.bean.Bean;
import soexample.umeng.com.happytimes.bean.CircleBean;
import soexample.umeng.com.happytimes.bean.HomeBean;
import soexample.umeng.com.happytimes.bean.RegisterBean;
import soexample.umeng.com.happytimes.bean.ShowBean;
import soexample.umeng.com.happytimes.callback.ICallBack;
import soexample.umeng.com.happytimes.model.IModeImpl;
import soexample.umeng.com.happytimes.view.IView;
/**
 * date:2019/1/3
 * author:冯泽林(asus)
 * function:
 */
public class IPresenterImpl implements IPresenter {
    private IModeImpl model;
    private IView iv;

    public IPresenterImpl(IView iv) {
        model = new IModeImpl();
        this.iv = iv;
    }
    public void ReisterAdd(String url,Map<String,String> parmas,Class clazz){
        model.requestData(url, parmas, clazz, new ICallBack() {
            @Override
            public void success(Object o) {
                RegisterBean bean= (RegisterBean) o;
                if(bean!=null){
                iv.getAddSuccess(bean);
                }
            }

            @Override
            public void failed(String e) {
                iv.getAddFailed(e);
            }
        });
    }
    @Override
    public void startRequqst(String url, Map<String, String> parmas, Class clazz) {

        model.requestData(url, parmas, clazz, new ICallBack() {
            @Override
            public void success(Object o) {

                Bean bean = (Bean) o;
                if (bean != null) {
                    iv.getDataSuccess(bean);
                }
            }

            @Override
            public void failed(String e) {
                iv.getDataFail(e);
            }
        });
    }
        public void gets(String url, Map<String, String> parmas, Class clazz){
        model.get(url, parmas, clazz, new ICallBack() {
            @Override
            public void success(Object o) {
                ShowBean bean= (ShowBean) o;
                if(bean!=null){
                    iv.getRecyclerShop(bean);
                }
            }

            @Override
            public void failed(String e) {
                    iv.getFailedRecyclerShop(e);
            }
        });
        }
    public void getBanner(String url, Map<String, String> parmas, Class clazz){
        model.get(url, parmas, clazz, new ICallBack() {
            @Override
            public void success(Object o) {
                HomeBean bean= (HomeBean) o;
                if(bean!=null){
                    iv.getBannersuccess(bean);
                }
            }

            @Override
            public void failed(String e) {
                iv.getBannerFailed(e);
            }
        });
    }
    public void onDetach() {
        if (model != null) {
            model = null;
        }
        if (iv != null) {
            iv = null;
        }
    }
}
