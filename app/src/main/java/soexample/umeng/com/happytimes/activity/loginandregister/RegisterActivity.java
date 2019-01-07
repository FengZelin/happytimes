package soexample.umeng.com.happytimes.activity.loginandregister;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import soexample.umeng.com.happytimes.MainActivity;
import soexample.umeng.com.happytimes.R;
import soexample.umeng.com.happytimes.activity.HomeActivity;
import soexample.umeng.com.happytimes.app.Apis;
import soexample.umeng.com.happytimes.bean.Bean;
import soexample.umeng.com.happytimes.bean.CircleBean;
import soexample.umeng.com.happytimes.bean.HeaderBean;
import soexample.umeng.com.happytimes.bean.RegisterBean;
import soexample.umeng.com.happytimes.bean.ShowBean;
import soexample.umeng.com.happytimes.presenter.IPresenterImpl;
import soexample.umeng.com.happytimes.view.IView;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, IView {

    private EditText edit_cell;
    private EditText edit_password;
    private ImageView image_eye;
    private TextView text_login;
    private Button but_login;
    int a=0;
    private IPresenterImpl presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        presenter = new IPresenterImpl(this);

    }

    private void initView() {
        edit_cell = (EditText) findViewById(R.id.edit_cell);
        edit_password = (EditText) findViewById(R.id.edit_password);
        image_eye = (ImageView) findViewById(R.id.image_eye);
        text_login = (TextView) findViewById(R.id.text_login);
        but_login = (Button) findViewById(R.id.but_login);
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
        text_login.setOnClickListener(this);
        but_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_login:
                String name = edit_cell.getText().toString().trim();
                String password = edit_password.getText().toString().trim();
                HashMap<String, String> map = new HashMap<>();
                map.put("phone",name);
                map.put("pwd",password);
                presenter.ReisterAdd(Apis.REGISTER_URL,map,RegisterBean.class);
                //遍历map中的键
                for (String key : map.keySet()) {
                    System.out.println("Key = " + key);
                }
//遍历map中的值
                for (String value : map.values()) {
                    System.out.println("Value = " + value);
                }
                System.out.print("");
                break;
            case R.id.text_login:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }


    @Override
    public void getDataSuccess(Bean bean) {

    }

    @Override
    public void getDataFail(String error) {

    }

    @Override
    public void getAddSuccess(RegisterBean bean) {
        System.out.print("==========="+bean.getStatus());
        String status = bean.getStatus();
        if (status.equals("0000")) {
                a = 1;
            Log.i("TAG",a+"");
            }
        if (a == 1) {
            Intent it = new Intent(this, MainActivity.class);
            startActivity(it);
        }else{
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }
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
