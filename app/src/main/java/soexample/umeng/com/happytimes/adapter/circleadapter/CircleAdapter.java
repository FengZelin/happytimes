package soexample.umeng.com.happytimes.adapter.circleadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import soexample.umeng.com.happytimes.R;
import soexample.umeng.com.happytimes.bean.CircleBean;

/**
 * date:2019/1/5
 * author:冯泽林(asus)
 * function:
 */
public class CircleAdapter extends RecyclerView.Adapter<CircleAdapter.ViewHolder> {
    private Context context;
    private List<CircleBean.ResultBean> list;

    public CircleAdapter(Context context, List<CircleBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.circl_item, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_name.setText(list.get(position).getNickName());
//        holder.item_data.setText(list.get(position).getCreateTime());
        holder.item_content.setText(list.get(position).getContent());
        holder.item_handnum.setText(list.get(position).getHeadPic());
        Glide.with(context).load(list.get(position).getHeadPic()).into(holder.item_image_name);
        Glide.with(context).load(list.get(position).getImage()).into(holder.item_image_text);
        Glide.with(context).load(list.get(position).getImage()).into(holder.item_image_text);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView item_image_name, item_image_text, item_whitehand;
        TextView item_name, item_data, item_content, item_handnum;

        public ViewHolder(View itemView) {
            super(itemView);
            item_image_name = itemView.findViewById(R.id.item_image_name);
            item_image_text = itemView.findViewById(R.id.item_image_text);
            item_whitehand = itemView.findViewById(R.id.item_whitehand);
            item_name = itemView.findViewById(R.id.item_name);
            item_data = itemView.findViewById(R.id.item_date);
            item_content = itemView.findViewById(R.id.item_content);
            item_handnum = itemView.findViewById(R.id.item_handnum);

        }
    }
}
