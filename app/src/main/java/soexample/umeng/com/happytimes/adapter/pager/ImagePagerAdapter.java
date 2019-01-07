package soexample.umeng.com.happytimes.adapter.pager;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import soexample.umeng.com.happytimes.R;

/**
 * date:2019/1/5
 * author:冯泽林(asus)
 * function:
 */
public class ImagePagerAdapter extends CarouselPagerAdapter<CarouselViewPager> {

    public ImagePagerAdapter(Context context, CarouselViewPager viewPager) {
        super(viewPager);
    }

    int[] imgRes = {
            R.drawable.one,
            R.drawable.two,
            R.drawable.t,
            R.drawable.f
            /*         R.drawable.img_wallhaven_426244,
                     R.drawable.img_wallhaven_431231,
                     R.drawable.img_wallhaven_432740,
                     R.drawable.img_wallhaven_426244,
                     R.drawable.img_wallhaven_431231,
                     R.drawable.img_wallhaven_432740,*/
    };

    @Override
    public Object instantiateRealItem(ViewGroup container, int position) {
        ImageView view = new ImageView(container.getContext());
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        view.setAdjustViewBounds(true);
        view.setImageResource(imgRes[position]);
        view.setLayoutParams(new LinearLayout.LayoutParams(900, 400));
        container.addView(view);
        return view;
    }

    @Override
    public int getRealDataCount() {
        return imgRes != null ? imgRes.length : 0;
    }
}
