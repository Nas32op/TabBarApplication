package com.wsj.tabbarapplication.controller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wsj.tabbarapplication.R;
import com.wsj.tabbarapplication.pojo.Commodity;
import java.util.List;

/**
 * @author: 绫_N
 * @date: 2025/11/20
 * @description: TabBarApplication
 */
public class CommodityAdapterR extends RecyclerView.Adapter<CommodityAdapterR.ViewHolderR>{
    private Commodity commodity;
    private List<Commodity> commodities;
    //定义接口
    public interface OnItemClickListener{
        void onItemClick(Commodity commodity);
    }
    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = listener;
    }
    public CommodityAdapterR(List<Commodity> commodities) {
        this.commodities = commodities;
    }
    //onCreateViewHolder方法用于引入子项布局创建的ViewHolderR实例，并返回
    @NonNull
    @Override
    public CommodityAdapterR.ViewHolderR onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View commodityView = LayoutInflater.from(parent.getContext()).inflate(R.layout.commodity_itme,parent,false);
        ViewHolderR viewHolderR = new ViewHolderR(commodityView);
        //点击事件
        viewHolderR.commodityView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击后弹窗
//                Toast.makeText(v.getContext(), "你点击了："+commodities.get(viewHolderR.getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                int position = viewHolderR.getAdapterPosition();
                Toast.makeText(v.getContext(), "你点击了："+commodities.get(position).getName(), Toast.LENGTH_SHORT).show();
                // 检查点击位置是否有效且点击监听器不为空，如果是则触发点击事件回调
                // position: 被点击项在RecyclerView中的位置
                // commodities: 商品列表数据源
                // mOnItemClickListener: 外部设置的点击事件监听器
                if (position != RecyclerView.NO_POSITION && mOnItemClickListener != null){
                    mOnItemClickListener.onItemClick(commodities.get(position));
                }
            }
        });
        return viewHolderR;
    }
    //onBindViewHolder方法用于将数据列表中的数据绑定到ViewHolderR实例中
    @Override
    public void onBindViewHolder(@NonNull CommodityAdapterR.ViewHolderR holder, int position) {
        commodity = commodities.get(position);
        holder.commodityName.setText(commodity.getName());
        holder.commodityPrice.setText(commodity.getPrice());
        holder.commodityDesc.setText(commodity.getDesc());
        holder.commodityImg.setImageResource(commodity.getImgId());
    }

    @Override
    public int getItemCount() {
        return commodities.size();
    }
    //1.定义内部类ViewHolderR，实现子项布局的资源映射关系

    static class ViewHolderR extends RecyclerView.ViewHolder{
        View commodityView;
        TextView commodityName,commodityPrice,commodityDesc;
        ImageView commodityImg;

        public ViewHolderR(@NonNull View itemView) {
            super(itemView);
            commodityView = itemView;
            commodityName = itemView.findViewById(R.id.tv_name);
            commodityPrice = itemView.findViewById(R.id.tv_price);
            commodityDesc = itemView.findViewById(R.id.tv_desc);
            commodityImg = itemView.findViewById(R.id.iv_img);
        }
    }
}
