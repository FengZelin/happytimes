package soexample.umeng.com.happytimes.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import soexample.umeng.com.happytimes.R;
import soexample.umeng.com.happytimes.adapter.circleadapter.CircleAdapter;

public class CartFragment extends Fragment {
    public static final String BUNDLE_TITLE = "title";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.activity_cart_fragment,null);

        return view;
    }
    public static CartFragment newInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(BUNDLE_TITLE, title);
        CartFragment fragment = new CartFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
