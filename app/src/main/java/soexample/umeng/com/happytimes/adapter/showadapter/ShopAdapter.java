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
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.Viewhodle> {
    private Context context;
    private List<ShowBean.ResultBean.RxxpBean.CommodityListBean> list;

    public ShopAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();

    }
    public void setData(List<ShowBean.ResultBean.RxxpBean.CommodityListBean> commodityList) {
        list.addAll(commodityList);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Viewhodle onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context,R.layout.shop_item,null);
        Viewhodle hodel = new Viewhodle(view);
        return hodel;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhodle holder, int position) {
        int price = list.get(position).getPrice();
        String masterPic = list.get(position).getMasterPic();
        Glide.with(context).load(masterPic).into(holder.image_view);
        holder.text_shopname.setText(list.get(position).getCommodityName());
        holder.text_shopprice.setText("$"+price+"元");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class Viewhodle extends RecyclerView.ViewHolder {
        private ImageView image_view;
        private TextView text_shopname,text_shopprice;
        public Viewhodle(View itemView) {
            super(itemView);
            image_view=itemView.findViewById(R.id.iamge_view);
            text_shopname=itemView.findViewById(R.id.text_shopname);
            text_shopprice=itemView.findViewById(R.id.text_shopprice);
        }
    }

}
