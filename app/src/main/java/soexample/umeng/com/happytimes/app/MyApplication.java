package soexample.umeng.com.happytimes.app;

import android.app.Application;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.WindowManager;

/**
 * date:2018/12/29
 * author:冯泽林(asus)
 * function:
 */
public class MyApplication extends Application {
    //绘制页面时参照的设计图宽度
    public final static float DESIGN_WIDTH = 750;

    @Override
    public void onCreate() {
        super.onCreate();
        resetDensity();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        resetDensity();
    }

    private void resetDensity() {
        Point size = new Point();
        ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getSize(size);
        getResources().getDisplayMetrics().xdpi = size.x / DESIGN_WIDTH * 72f;
    }
}
