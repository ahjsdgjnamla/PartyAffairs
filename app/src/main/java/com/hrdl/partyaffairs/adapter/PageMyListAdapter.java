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

import java.util.List;

import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.entity.ZWInfo;

/**
 * Created by zhangwei on 2018/11/12.
 * 首页热点新闻列表适配器
 */
public class PageMyListAdapter extends BaseAdapter {

    private Context context;
    private List<ZWInfo> poorList;
    private ViewHolder myVH;
    private LayoutInflater inflater;

    public PageMyListAdapter(Context context, List<ZWInfo> poorList){
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
            view = inflater.inflate(R.layout.item_page_list, null, false);
            myVH.mTitle = view.findViewById(R.id.page_item_title);
            myVH.cityPhoto = view.findViewById(R.id.page_item_photo);
            myVH.mTime = view.findViewById(R.id.page_item_time);
            myVH.mContent = view.findViewById(R.id.page_item_center);
            myVH.mNum = view.findViewById(R.id.page_item_num);
            view.setTag(myVH);
        } else {
            myVH = (ViewHolder) view.getTag();
        }

        displayImage8(poorList.get(i).getWorkPhoto(),myVH.cityPhoto,0);
        myVH.mTitle.setText(poorList.get(i).getWorkTitle());
        myVH.mTime.setText(poorList.get(i).getWorkTime());
        myVH.mContent.setText(poorList.get(i).getWorkContent());
        myVH.mNum.setText(poorList.get(i).getWorkNum());

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
        TextView mTitle, mTime, mContent, mNum;
        ImageView cityPhoto;
    }

}
