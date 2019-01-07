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
public class MagicShopAdapter extends RecyclerView.Adapter<MagicShopAdapter.Viewholder> {
    private Context context;
    private List<ShowBean.ResultBean.MlssBean.CommodityListBeanXX> list;

    public MagicShopAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public void setData(List<ShowBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList) {
        list.addAll(commodityList);
        notifyDataSetChanged();

    }
    @NonNull
    @Override
    public MagicShopAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.magic_item, null);
        Viewholder holder = new Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        int price = list.get(position).getPrice();
        holder.text_view.setText("￥"+price+"元");
        holder.textview.setText(list.get(position).getCommodityName());
        Glide.with(context).load(list.get(position).getMasterPic()).into(holder.image);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }



    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView textview, text_view;

        public Viewholder(View itemView) {
            super(itemView);
            textview = itemView.findViewById(R.id.text_shopname);
            text_view = itemView.findViewById(R.id.text_shopprice);
            image=itemView.findViewById(R.id.image_view);
        }
    }
}