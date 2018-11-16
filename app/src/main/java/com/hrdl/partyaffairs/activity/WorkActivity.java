package com.hrdl.partyaffairs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gyf.barlibrary.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.adapter.CityEnvironAdapter;
import com.hrdl.partyaffairs.base.BaseActivity;
import com.hrdl.partyaffairs.entity.ZWInfo;
import com.hrdl.partyaffairs.view.SimpleToolbar;

public class WorkActivity extends BaseActivity {

    @BindView(R.id.left_image_id)
    ImageButton leftImageId;
    @BindView(R.id.txt_main_title)
    TextView txtMainTitle;
    @BindView(R.id.txt_right_title)
    TextView txtRightTitle;
    @BindView(R.id.simple_toolbar)
    SimpleToolbar simpleToolbar;
    @BindView(R.id.title_1)
    ImageView title1;
    @BindView(R.id.title_2)
    ImageView title2;
    @BindView(R.id.title_3)
    ImageView title3;
    @BindView(R.id.list_id)
    ListView listId;

    private CityEnvironAdapter cityEnvironAdapter;
    private List<ZWInfo> center = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.activity_work;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        ImmersionBar.setTitleBar(this,simpleToolbar);
        setToolBar("党务工作", R.mipmap.ic_jiantou_white_left);
    }

    /**
     * 设置Title，方法内的参数可自己定义，如左边文字，颜色，图片
     *
     * @param title：中间标题
     * @param LeftImages：左边图标
     */
    private void setToolBar(String title, int LeftImages) {
        simpleToolbar.setMainTitle(title);
        simpleToolbar.setLeftTitleDrawable(LeftImages);
        //返回键
        simpleToolbar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();

        center.clear();

        ZWInfo zwInfo = new ZWInfo();
        zwInfo.setWorkPhoto(R.mipmap.item_1);
        zwInfo.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo.setWorkTime("2018-08-16");

        ZWInfo zwInfo1 = new ZWInfo();
        zwInfo1.setWorkPhoto(R.mipmap.item_2);
        zwInfo1.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo1.setWorkTime("2018-08-17");

        ZWInfo zwInfo2 = new ZWInfo();
        zwInfo2.setWorkPhoto(R.mipmap.item_3);
        zwInfo2.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo2.setWorkTime("2018-08-18");

        ZWInfo zwInfo3 = new ZWInfo();
        zwInfo3.setWorkPhoto(R.mipmap.item_4);
        zwInfo3.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo3.setWorkTime("2018-08-19");

        ZWInfo zwInfo4 = new ZWInfo();
        zwInfo4.setWorkPhoto(R.mipmap.item_5);
        zwInfo4.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo4.setWorkTime("2018-08-20");

        center.add(zwInfo);
        center.add(zwInfo1);
        center.add(zwInfo2);
        center.add(zwInfo3);
        center.add(zwInfo4);

        //更新列表
        if (listId.getAdapter() == null) {
            cityEnvironAdapter = new CityEnvironAdapter(WorkActivity.this, center);
            listId.setAdapter(cityEnvironAdapter);
        } else {
            cityEnvironAdapter.notifyDataSetChanged();
        }

        listId.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(WorkActivity.this, WorkDataActivity.class));
            }
        });
    }
}
