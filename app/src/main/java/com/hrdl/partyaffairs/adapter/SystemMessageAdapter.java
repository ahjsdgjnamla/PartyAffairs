package com.hrdl.partyaffairs.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.entity.SystemMessagInfo;

/**
 * 作者：王健 on 2018/8/31
 * 邮箱：845040970@qq.com
 */
public class SystemMessageAdapter extends BaseAdapter {
    List<SystemMessagInfo> list;
    Context context;
    private static final int MYLIVE_MODE_CHECK = 0;
    int mEditMode = MYLIVE_MODE_CHECK;
    private int secret = 0;
    private OnItemClickListener mOnItemClickListener;
    public SystemMessageAdapter(List<SystemMessagInfo> list, Context context) {
        // TODO Auto-generated constructor stub
        this.list=list;
        this.context=context;
    }
    public SystemMessageAdapter(Context context) {
        // TODO Auto-generated constructor stub
        this.context=context;
    }

    public void notifyAdapter(List<SystemMessagInfo> myLiveList, boolean isAdd){
        if (!isAdd){
            this.list=myLiveList;
        }else {
            this.list.addAll(myLiveList);
        }
        notifyDataSetChanged();
    }

    public List<SystemMessagInfo> getMyLiveList(){
        if (list == null)  {
            list =new ArrayList<>();
        }
        return  list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
    public interface OnItemClickListener {
        void onItemClickListener(int pos, List<SystemMessagInfo> myLiveList);
    }
    public void setEditMode(int editMode) {
        mEditMode = editMode;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return getMyLiveList().size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        final ViewHolder viewHolder;
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item_system_message, null);
            viewHolder=new ViewHolder();
            viewHolder.vMessage = convertView.findViewById(R.id.vMessage);
            viewHolder.textView=convertView.findViewById(R.id.text);
            viewHolder.checkBox=convertView.findViewById(R.id.check_box);
            viewHolder.btn=convertView.findViewById(R.id.id_btn);
            viewHolder.sendTime = convertView.findViewById(R.id.send_time);
            convertView.setTag(viewHolder);
        }else{
            viewHolder=(ViewHolder) convertView.getTag();
        }

        //未读判定
        if (list.get(position).isUnRead()){
            viewHolder.vMessage.setVisibility(View.VISIBLE);
        }else {
            viewHolder.vMessage.setVisibility(View.INVISIBLE);
        }

        viewHolder.textView.setText(list.get(position).getTitle());

        if (list.get(position).getSendTimeLongNum() != null){
            viewHolder.sendTime.setText(stampToDate(Long.valueOf(list.get(position).getSendTimeLongNum())));
        }else{
            viewHolder.sendTime.setText("暂无");
        }

        if (mEditMode == MYLIVE_MODE_CHECK) {
            viewHolder.checkBox.setVisibility(View.GONE);
        } else {
            viewHolder.checkBox.setVisibility(View.VISIBLE);

            if (list.get(position).isBo()) {
                viewHolder.checkBox.setImageResource(R.mipmap.ic_checked);
            } else {
                viewHolder.checkBox.setImageResource(R.mipmap.ic_uncheck);
            }
        }


        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClickListener.onItemClickListener(position, list);
            }
        });
        return convertView;

    }

    /**
     * 将时间戳转换为时间（单位：毫秒）
     */
    public String stampToDate(Long timeMillis){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        Date date = new Date(timeMillis);
        return simpleDateFormat.format(date);
    }

    class ViewHolder{
        TextView textView,sendTime;
        ImageView checkBox;
        RelativeLayout btn;
        //消息红点
        View vMessage;
    }

}
