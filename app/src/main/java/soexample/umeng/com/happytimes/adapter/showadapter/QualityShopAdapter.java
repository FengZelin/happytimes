package soexample.umeng.com.happytimes.adapter.showadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import soexample.umeng.com.happytimes.R;
import soexample.umeng.com.happytimes.bean.ShowBean;

/**
 * date:2019/1/4
 * author:冯泽林(asus)
 * function:
 */
public class QualityShopAdapter extends RecyclerView.Adapter<QualityShopAdapter.Viewhodle> {
    private Context context;
    private List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> list;

    public QualityShopAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public void setData(List<ShowBean.ResultBean.PzshBean.CommodityListBeanX> commodityList) {
        list.addAll(commodityList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public QualityShopAdapter.Viewhodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.quality_item,null);
        Viewhodle hodle = new Viewhodle(view);
        return hodle;
    }

    @Override
    public void onBindViewHolder(@NonNull QualityShopAdapter.Viewhodle holder, int position) {
        holder.text_view.setText(list.get(position).getCommodityName());
        int price = list.get(position).getPrice();
        holder.textview.setText("￥"+price+"元");
        String masterPic = list.get(position).getMasterPic();
       // String[] split = masterPic.split("\\|");;
        Glide.with(context).load(masterPic).into(holder.image_view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class Viewhodle extends RecyclerView.ViewHolder {
        private ImageView image_view;
        private TextView text_view,textview;
        public Viewhodle(View itemView) {
            super(itemView);
            text_view = itemView.findViewById(R.id.quality_name);
            textview=itemView.findViewById(R.id.quality_price);
            image_view=itemView.findViewById(R.id.quality_image);
        }
    }
}
