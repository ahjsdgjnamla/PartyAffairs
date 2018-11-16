package com.hrdl.partyaffairs.fragment;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.adapter.PageMyListAdapter;
import com.hrdl.partyaffairs.base.BaseLazyFragment;
import com.hrdl.partyaffairs.entity.ZWInfo;
import com.hrdl.partyaffairs.view.MyListView;

/**
 * 作者：王健 on 2018/9/30
 * 邮箱：845040970@qq.com
 */
public class TestBottomFragment1 extends BaseLazyFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.mone_id)
    TextView moneId;
    @BindView(R.id.day_id)
    TextView dayId;
    @BindView(R.id.title_content)
    TextView titleContent;
    @BindView(R.id.item_card)
    CardView itemCard;
    @BindView(R.id.news_id)
    LinearLayout newsId;
    @BindView(R.id.party_id)
    LinearLayout partyId;
    @BindView(R.id.poverty_id)
    LinearLayout povertyId;
    @BindView(R.id.charitable_id)
    LinearLayout charitableId;
    @BindView(R.id.title_1)
    ImageView title1;
    @BindView(R.id.title_2)
    ImageView title2;
    @BindView(R.id.title_3)
    ImageView title3;
    @BindView(R.id.more_id)
    TextView moreId;
    @BindView(R.id.page_list)
    MyListView pageList;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder;

    private PageMyListAdapter pageMyListAdapter;
    private List<ZWInfo> center = new ArrayList<>();

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_testbottom1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        isLazyLoad();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();
        center.clear();

        ZWInfo zwInfo = new ZWInfo();
        zwInfo.setWorkPhoto(R.mipmap.item_1);
        zwInfo.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo.setWorkTime("2018-08-16");
        zwInfo.setWorkContent("时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢");
        zwInfo.setWorkNum("1652");

        ZWInfo zwInfo1 = new ZWInfo();
        zwInfo1.setWorkPhoto(R.mipmap.item_2);
        zwInfo1.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo1.setWorkTime("2018-08-17");
        zwInfo1.setWorkContent("时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢");
        zwInfo1.setWorkNum("1651");

        ZWInfo zwInfo2 = new ZWInfo();
        zwInfo2.setWorkPhoto(R.mipmap.item_3);
        zwInfo2.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo2.setWorkTime("2018-08-18");
        zwInfo2.setWorkContent("时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢");
        zwInfo2.setWorkNum("1008");

        ZWInfo zwInfo3 = new ZWInfo();
        zwInfo3.setWorkPhoto(R.mipmap.item_4);
        zwInfo3.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo3.setWorkTime("2018-08-19");
        zwInfo3.setWorkContent("时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢");
        zwInfo3.setWorkNum("1972");

        ZWInfo zwInfo4 = new ZWInfo();
        zwInfo4.setWorkPhoto(R.mipmap.item_5);
        zwInfo4.setWorkTitle("开启智慧党建新领域，基层党组织入驻平台");
        zwInfo4.setWorkTime("2018-08-20");
        zwInfo4.setWorkContent("时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢时刻记得发挥空间撒打发你空间撒地方呢");
        zwInfo4.setWorkNum("1291");

        center.add(zwInfo);
        center.add(zwInfo1);
        center.add(zwInfo2);
        center.add(zwInfo3);
        center.add(zwInfo4);

        //更新列表
        if (pageList.getAdapter() == null) {
            pageMyListAdapter = new PageMyListAdapter(getActivity(), center);
            pageList.setAdapter(pageMyListAdapter);
        } else {
            pageMyListAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
