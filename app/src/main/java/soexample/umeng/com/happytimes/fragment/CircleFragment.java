package soexample.umeng.com.happytimes.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.happytimes.R;
import soexample.umeng.com.happytimes.adapter.circleadapter.CircleAdapter;
import soexample.umeng.com.happytimes.app.Apis;
import soexample.umeng.com.happytimes.bean.Bean;
import soexample.umeng.com.happytimes.bean.CircleBean;
import soexample.umeng.com.happytimes.bean.HomeBean;
import soexample.umeng.com.happytimes.bean.RegisterBean;
import soexample.umeng.com.happytimes.bean.ShowBean;
import soexample.umeng.com.happytimes.presenter.IPresenterImpl;
import soexample.umeng.com.happytimes.view.IView;

public class CircleFragment extends Fragment implements IView {
    public static final String BUNDLE_TITLE = "title";
    @BindView(R.id.recycler_cicrle)
    RecyclerView recyclerCicrle;
    Unbinder unbinder;
    private CircleAdapter adapter;
    private IPresenterImpl presenter;
    private List<CircleBean.ResultBean> list=new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_circle_fragment, null);
        unbinder = ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        presenter = new IPresenterImpl(this);
        HashMap<String,String> map = new HashMap<>();
        presenter.gets(Apis.CIRCLE_URL,map,CircleBean.class);
        adapter = new CircleAdapter(getActivity(), list);
        recyclerCicrle.setAdapter(adapter);
        recyclerCicrle.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    public static CircleFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        CircleFragment fragment = new CircleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getBannersuccess(HomeBean bean) {

    }

    @Override
    public void getBannerFailed(String error) {

    }

    @Override
    public void getDataSuccess(Bean bean) {

    }

    @Override
    public void getDataFail(String error) {

    }

    @Override
    public void getAddSuccess(RegisterBean bean) {

    }

    @Override
    public void getAddFailed(String error) {

    }

    @Override
    public void getRecyclerShop(ShowBean bean) {

    }

    @Override
    public void getFailedRecyclerShop(String error) {

    }

    @Override
    public void getCircleRecycler(CircleBean bean) {
        List<CircleBean.ResultBean> result = bean.getResult();
        if(result!=null){
            list.clear();
            list.addAll(result);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getCirleFailed(String error) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
