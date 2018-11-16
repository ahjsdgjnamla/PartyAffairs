package com.hrdl.partyaffairs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.entity.ZWInfo;

import java.util.List;

/**
 * Created by zhangwei on 2018/9/1.
 * 党务工作列表适配器
 */
public class CityEnvironAdapter extends BaseAdapter {

    private Context context;
    private List<ZWInfo> poorList;
    private ViewHolder myVH;
    private LayoutInflater inflater;

    public CityEnvironAdapter(Context context, List<ZWInfo> poorList){
        this.context=context;
        this.poorList=poorList;
    }

    @Override
    public int getCount() {
        return poorList.size();
    }

    @Override
    public Object getItem(int i) {
        return poorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            myVH = new ViewHolder();
            inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_cityenv_list, null, false);
            myVH.mTitle = view.findViewById(R.id.title_center);
            myVH.cityPhoto = view.findViewById(R.id.city_photo);
            myVH.mTime = view.findViewById(R.id.surplus3_id);
            view.setTag(myVH);
        } else {
            myVH = (ViewHolder) view.getTag();
        }

        //ShadowHelperUtil.setshadow(context,myVH.cardView, Color.GREEN,context.getResources().getColor(R.color.theme_color),10,2,2,null);

        displayImage8(poorList.get(i).getWorkPhoto(),myVH.cityPhoto,0);
        myVH.mTitle.setText(poorList.get(i).getWorkTitle());
        myVH.mTime.setText(poorList.get(i).getWorkTime());

        return view;
    }

    public void displayImage8(Object imageURL, ImageView imageView, int radius) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_loading)
                .error(R.mipmap.ic_load_failure)
                .priority(Priority.IMMEDIATE)
                .diskCacheStrategy(DiskCacheStrategy.ALL);
                //.transform(new GlideRoundTransform(radius));
        Glide.with(context).load(imageURL).apply(options).into(imageView);
    }

    static class ViewHolder {
        TextView mTitle, mTime;
        ImageView cityPhoto;
    }

}
