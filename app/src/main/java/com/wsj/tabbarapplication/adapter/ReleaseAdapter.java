package com.wsj.tabbarapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wsj.tabbarapplication.R;
import com.wsj.tabbarapplication.pojo.Release;

import java.util.List;

/**
 * @author: 绫_N
 * @date: 2025/12/8
 * @description: myAppDemo_wsj
 */
public class ReleaseAdapter extends RecyclerView.Adapter<ReleaseAdapter.ViewHolder>{
    private List<Release> releases;
    private Context context;
    private static final String BASE_URL = "http://172.20.10.8/";
    //    private static final String BASE_URL = "http://192.168.111.5/";
    public ReleaseAdapter(List<Release> releases, Context context) {
        this.releases = releases;
        this.context = context;
    }

    @NonNull
    @Override
    public ReleaseAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.release_itme, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReleaseAdapter.ViewHolder holder, int position) {
        Release release = releases.get(position);

        // 设置文本
        holder.tvName.setText(release.getTitle());
        holder.tvPrice.setText("¥ " + release.getPrice());
        holder.tvDesc.setText(release.getDescription());

        // 关键：图片加载，拼接完整的 URL
        String fullImageUrl = BASE_URL + release.getImagePath();

        Glide.with(context)
                .load(fullImageUrl) // 加载完整的 HTTP URL
                .into(holder.ivImage);
    }

    @Override
    public int getItemCount() {
        return releases == null ? 0 : releases.size();
    }
//缓存控件
    public static class ViewHolder extends RecyclerView.ViewHolder{
    ImageView ivImage;
    TextView tvName, tvPrice, tvDesc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivImage = itemView.findViewById(R.id.img);
            tvName = itemView.findViewById(R.id.release_tv_name);
            tvPrice = itemView.findViewById(R.id.release_tv_price);
            tvDesc = itemView.findViewById(R.id.release_tv_desc);
        }
    }
}
