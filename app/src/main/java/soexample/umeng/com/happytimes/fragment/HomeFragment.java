package soexample.umeng.com.happytimes.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import soexample.umeng.com.happytimes.R;
import soexample.umeng.com.happytimes.adapter.showadapter.MagicShopAdapter;
import soexample.umeng.com.happytimes.adapter.showadapter.QualityShopAdapter;
import soexample.umeng.com.happytimes.adapter.showadapter.ShopAdapter;
import soexample.umeng.com.happytimes.app.Apis;
import soexample.umeng.com.happytimes.bean.Bean;
import soexample.umeng.com.happytimes.bean.CircleBean;
import soexample.umeng.com.happytimes.bean.HomeBean;
import soexample.umeng.com.happytimes.bean.RegisterBean;
import soexample.umeng.com.happytimes.bean.ShowBean;
import soexample.umeng.com.happytimes.presenter.IPresenterImpl;
import soexample.umeng.com.happytimes.view.IView;

public class HomeFragment extends Fragment implements IView {
    public static final String BUNDLE_TITLE = "title";

    Unbinder unbinder;
    @BindView(R.id.imageview_fenlei)
    ImageView imageviewFenlei;
    @BindView(R.id.imageview_sousuo)
    ImageView imageviewSousuo;
    @BindView(R.id.imageview_resoushangpin)
    ImageView imageviewResoushangpin;
    @BindView(R.id.recyclerview_rexiaoshangpin)
    RecyclerView recyclerviewRexiaoshangpin;
    @BindView(R.id.imageview_molishishang)
    ImageView imageviewMolishishang;
    @BindView(R.id.recyclerview_molishishang)
    RecyclerView recyclerviewMolishishang;
    @BindView(R.id.imageview_pinzhishenghuo)
    ImageView imageviewPinzhishenghuo;
    @BindView(R.id.recyclerview_pinzhishenghuo)
    RecyclerView recyclerviewPinzhishenghuo;
    @BindView(R.id.banner)
    XBanner banner;


    private IPresenterImpl presenter;
    private List<ShowBean.ResultBean> showlist = new ArrayList<>();

    private ShopAdapter adapter;
    private MagicShopAdapter magicShopAdapter;
    private QualityShopAdapter qualityShopAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_home_fragment, null);

        unbinder = ButterKnife.bind(this, view);
        presenter = new IPresenterImpl(this);
        inivView();
        initPager();

        return view;
    }

    private void initPager() {


    }


    private void inivView() {

        HashMap<String, String> map = new HashMap<>();
        presenter.gets(Apis.SHOW_URL, map, ShowBean.class);
//      热销商品
        adapter = new ShopAdapter(getActivity());
        recyclerviewRexiaoshangpin.setAdapter(adapter);
        System.out.print("============" + showlist);
        recyclerviewRexiaoshangpin.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//      魔力时尚
        magicShopAdapter = new MagicShopAdapter(getActivity());
        recyclerviewMolishishang.setAdapter(magicShopAdapter);
        recyclerviewMolishishang.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//      品质生活
        qualityShopAdapter = new QualityShopAdapter(getActivity());
        recyclerviewPinzhishenghuo.setAdapter(qualityShopAdapter);
        recyclerviewPinzhishenghuo.setLayoutManager(new GridLayoutManager(getActivity(), 2));
    }

    public static HomeFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        HomeFragment fragment = new HomeFragment();
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
        HashMap<String, String> map = new HashMap<>();
        presenter.gets(Apis.BANNER_URL, map, HomeBean.ResultBean.class);
//        if(bean instanceof HomeBean){
//            HomeBean bean1=bean;
//            bean1.setData(banner1.getResult(),null);
//            bean1.loadImage(new XBanner.XBannerAdapter() {
//                @Override
//                public void loadBanner(XBanner banner, View view, int position) {
//                    HomeBean.ResultBean bean= (HomeBean.ResultBean) model;
//                    Glide.with(getActivity()).load(bean.getImageUrl()).into((ImageView) view);
//                    banner.setPageChangeDuration(1000);
//                }
//
//            }
//    }

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
        ShowBean.ResultBean result = bean.getResult();
        System.out.print("" + result);
        if (result != null) {
            adapter.setData(result.getRxxp().get(0).getCommodityList());
            magicShopAdapter.setData(result.getMlss().get(0).getCommodityList());
            qualityShopAdapter.setData(result.getPzsh().get(0).getCommodityList());
        }


    }


    @Override
    public void getFailedRecyclerShop(String error) {

    }

    @Override
    public void getCircleRecycler(CircleBean bean) {

    }

    @Override
    public void getCirleFailed(String error) {

    }


}


