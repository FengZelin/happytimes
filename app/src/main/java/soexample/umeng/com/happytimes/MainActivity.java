package soexample.umeng.com.happytimes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import soexample.umeng.com.happytimes.activity.HomeActivity;
import soexample.umeng.com.happytimes.activity.loginandregister.RegisterActivity;
import soexample.umeng.com.happytimes.app.Apis;
import soexample.umeng.com.happytimes.bean.Bean;
import soexample.umeng.com.happytimes.bean.CircleBean;
import soexample.umeng.com.happytimes.bean.HeaderBean;
import soexample.umeng.com.happytimes.bean.HomeBean;
import soexample.umeng.com.happytimes.bean.RegisterBean;
import soexample.umeng.com.happytimes.bean.ShowBean;
import soexample.umeng.com.happytimes.presenter.IPresenterImpl;
import soexample.umeng.com.happytimes.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView {

    private EditText edit_cell;
    private EditText edit_password;
    private ImageView image_eye;
    private CheckBox Check_Box;
    private TextView text_Reg;
    private Button but_login;
    private IPresenterImpl presenter;
    int o = 0;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new IPresenterImpl(MainActivity.this);

        initView();
        initData();
    }

    private void initData() {
        sp = getSharedPreferences("into", MODE_PRIVATE);
        String editcell = sp.getString("name", "");
        String editpassword = sp.getString("password", "");
        boolean ischecked = sp.getBoolean("ischecked", false);
        if (ischecked) {
            edit_cell.setText(editcell);
            edit_password.setText(editpassword);
            Check_Box.setChecked(ischecked);
        }
    }


    private void initView() {
        edit_cell = (EditText) findViewById(R.id.edit_cell);
        edit_password = (EditText) findViewById(R.id.edit_password);
        image_eye = (ImageView) findViewById(R.id.image_eye);
        Check_Box = (CheckBox) findViewById(R.id.Check_Box);
        text_Reg = (TextView) findViewById(R.id.text_Reg);
        but_login = (Button) findViewById(R.id.but_login);
        text_Reg.setOnClickListener(this);
        but_login.setOnClickListener(this);
        image_eye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    //显示
                    edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //隐藏
                    edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.but_login:
                String name = edit_cell.getText().toString().trim();
                String password = edit_password.getText().toString().trim();
                HashMap<String, String> map = new HashMap<>();
                map.put("phone", name);
                map.put("pwd", password);
                presenter.startRequqst(Apis.LOGIN_URL, map, Bean.class);

                if (Check_Box.isChecked()) {
                    sp = getSharedPreferences("into", MODE_PRIVATE);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("name", name);
                    edit.putString("password", password);
                    edit.putBoolean("ischecked",true);
                    edit.commit();
                }

                break;
            case R.id.text_Reg:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


    @Override
    public void getBannersuccess(HomeBean bean) {

    }

    @Override
    public void getBannerFailed(String error) {

    }

    @Override
    public void getDataSuccess(Bean bean) {
        String s = bean.getResult().getSessionId();
        int userId = bean.getResult().getUserId();
        HeaderBean bean1 = new HeaderBean(s, userId);
        EventBus.getDefault().postSticky(bean1);
        if (bean.getStatus().equals("0000")) {
            o = 2;
        }
        if (o == 2) {
            Intent it = new Intent(this, HomeActivity.class);
            startActivity(it);
        }else{
            Toast.makeText(this,"账号密码错误！登录失败",Toast.LENGTH_SHORT).show();
        }
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

    }

    @Override
    public void getCirleFailed(String error) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
}
