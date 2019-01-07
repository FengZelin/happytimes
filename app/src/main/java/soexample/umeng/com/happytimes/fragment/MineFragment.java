package soexample.umeng.com.happytimes.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import soexample.umeng.com.happytimes.R;
import soexample.umeng.com.happytimes.activity.AddressActivity;
import soexample.umeng.com.happytimes.activity.CircleActivity;
import soexample.umeng.com.happytimes.activity.FootmarkActivity;
import soexample.umeng.com.happytimes.activity.PersonaldataActivity;
import soexample.umeng.com.happytimes.activity.PurseActivity;

public class MineFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";
    @BindView(R.id.text_name)
    TextView textname;

    @BindView(R.id.image_head)
    ImageView imageHead;
    Unbinder unbinder;
    @BindView(R.id.text_Personaldata)
    LinearLayout textPersonaldata;
    @BindView(R.id.text_circle)
    LinearLayout textCircle;
    @BindView(R.id.text_footmark)
    LinearLayout textFootmark;
    @BindView(R.id.text_purse)
    LinearLayout textPurse;
    @BindView(R.id.text_address)
    LinearLayout textAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.activity_mine_fragment, null);

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    public static MineFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        MineFragment fragment = new MineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.text_name, R.id.text_Personaldata, R.id.text_circle, R.id.text_footmark, R.id.text_purse, R.id.text_address, R.id.image_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_name:
                break;
            case R.id.text_Personaldata:
                Intent it = new Intent(getActivity(), PersonaldataActivity.class);
                startActivity(it);
                break;
            case R.id.text_circle:
                Intent circleit = new Intent(getActivity(), CircleActivity.class);
                startActivity(circleit);
                break;
            case R.id.text_footmark:
                Intent footmarkit = new Intent(getActivity(), FootmarkActivity.class);
                startActivity(footmarkit);
                break;
            case R.id.text_purse:
                Intent purseit = new Intent(getActivity(), PurseActivity.class);
                startActivity(purseit);
                break;
            case R.id.text_address:
                Intent addressit = new Intent(getActivity(), AddressActivity.class);
                startActivity(addressit);
                break;
            case R.id.image_head:
                break;
        }
    }

}
