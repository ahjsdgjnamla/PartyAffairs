package com.hrdl.partyaffairs.fragment.usersMessageFragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.qmuiteam.qmui.util.QMUIViewHelper;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.activity.SystemMessageDetailActivity;
import com.hrdl.partyaffairs.adapter.SystemMessageAdapter;
import com.hrdl.partyaffairs.base.BaseLazyFragment;
import com.hrdl.partyaffairs.entity.SystemMessagInfo;
import com.hrdl.partyaffairs.utils.ShadowHelperUtil;
import com.hrdl.partyaffairs.view.emptyview.LoadingLayout;

/**
 * Created by Administrator on 2018/9/12.
 * 用户活动消息
 */
public class ActivityFragment extends BaseLazyFragment implements View.OnClickListener, SystemMessageAdapter.OnItemClickListener {


    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_select_num)
    TextView tvSelectNum;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.select_all)
    TextView selectAll;
    @BindView(R.id.ll_mycollection_bottom_dialog)
    LinearLayout llMycollectionBottomDialog;

    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    public List<SystemMessagInfo> mData = new ArrayList<>();
    private static final int MYLIVE_MODE_CHECK = 0;
    private static final int MYLIVE_MODE_EDIT = 1;
    private int mEditMode = MYLIVE_MODE_CHECK;
    private boolean isSelectAll = false;
    private boolean editorStatus = false;
    private int index = 0;
    private SystemMessageAdapter systemMessageAdapter;

    public int fragment2 = 0;

    /**
     * TAG
     */
    private static final String TAG = "ActivityFragment";
    /**
     * 多状态布局
     */
    private LoadingLayout mLoadingLayout;

    private List<SystemMessagInfo> mListNewsId = new ArrayList<SystemMessagInfo>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        isLazyLoad();
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int setLayoutId() {
        return R.layout.fragment_activity_message;
    }

    @Override
    protected void initData() {
        super.initView();
        mLoadingLayout = LoadingLayout.wrap(refreshLayout);
        //失败点击监听
        mLoadingLayout.setRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取数据
                //getNewsData();
            }
        });

        //下拉刷新监听
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                //获取数据
                //getNewsData();
            }
        });
        //是否禁用上拉加载，true为不禁用，false为禁用。
        refreshLayout.setEnableLoadMore(false);

        initListView();
        initListener();
    }

    private void initListView() {
        systemMessageAdapter = new SystemMessageAdapter(getActivity());
        listview.setAdapter(systemMessageAdapter);

        //获取数据
        //getNewsData();

        /*for (int i = 0; i < 30; i++) {
            SystemMessagInfo myLiveList = new SystemMessagInfo();
            myLiveList.setTitle("这是第" + i + "个条目");
            mData.add(myLiveList);
            systemMessageAdapter.notifyAdapter(mData, false);
        }*/

        int[] color = new int[]{Color.parseColor("#FEB06A"), Color.parseColor("#F06400")};
        int[] color1 = new int[]{Color.parseColor("#7BB1FF"), Color.parseColor("#3282FF")};
        //删除按钮
        ShadowHelperUtil.setshadow(getActivity(), btnDelete,
                Color.parseColor("#FEB06A"),
                Color.parseColor("#F88C38"),
                15,
                1,
                1, color);
        //全选|取消全选按钮
        ShadowHelperUtil.setshadow(getActivity(), selectAll,
                Color.parseColor("#7BB1FF"),
                Color.parseColor("#3282FF"),
                15,
                1,
                1, color1);

    }

    /**
     * 获取消息列表
     */
    /*public void getNewsData() {
        mLoadingLayout.showLoading();
        OkGo.<String>get(HTTPJSONConstant.News_list)
                .tag(TAG)
                .params("messageType", Const.ACTIVIT_MESSAGE_TYPE)
                .params("sessionKey", UserManager.getSessionKey(getActivity()))
                .params("appId", HTTPJSONConstant.APP_ID)
                .execute(new MStringCallback() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e("活动消息列表：" + s);
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getString("code").equals("0")) {
                                mData.clear();
                                systemMessageAdapter.notifyAdapter(mData, false);
                                JSONArray jsonArray = object.getJSONArray("data");
                                if (jsonArray.length() == 0) {
                                    mLoadingLayout.setEmptyImage(R.mipmap.ic_unnews);
                                    mLoadingLayout.showEmpty();
                                } else {
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object1 = jsonArray.getJSONObject(i);
                                        SystemMessagInfo sm = new SystemMessagInfo();
                                        sm.setTitle(object1.getString("title"));
                                        sm.setId(object1.getString("id"));
                                        sm.setContent(object1.getString("content"));
                                        sm.setSendTimeLongNum(object1.getString("sendTimeLongNum"));
                                        //0是未读1已读
                                        if (object1.getString("status").equals("0")) {
                                            sm.setUnRead(true);
                                        } else {
                                            sm.setUnRead(false);
                                        }
                                        mData.add(sm);
                                        systemMessageAdapter.notifyAdapter(mData, false);
                                    }
                                    mLoadingLayout.showContent();
                                    refreshLayout.finishRefresh(1000,true);
                                }
                            } else if (object.getString("code").equals("-1")) {
                                mLoadingLayout.setErrorImage(R.mipmap.ic_unentiy);
                                mLoadingLayout.setErrorText("请求失败");
                                mLoadingLayout.showError();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            mLoadingLayout.setErrorImage(R.mipmap.ic_unentiy);
                            mLoadingLayout.setErrorText("请求失败");
                            mLoadingLayout.showError();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        mLoadingLayout.setErrorImage(R.mipmap.ic_unentiy);
                        mLoadingLayout.setErrorText("请求失败");
                        mLoadingLayout.showError();
                    }

                    @Override
                    public void onFinish() {
                        //更新全选和取消或是隐藏状态
                        CallBackUtils.doCallBackMethod("twoUpData");
                    }
                });
    }*/

    /**
     * 根据选择的数量是否为0来判断按钮的是否可点击.
     *
     * @param size
     */
    private void setBtnBackground(int size) {
        if (size != 0) {
//            btnDelete.setBackgroundResource(R.drawable.button_shape);
            btnDelete.setEnabled(true);
            btnDelete.setTextColor(Color.WHITE);
        } else {
//            btnDelete.setBackgroundResource(R.drawable.button_noclickable_shape);
            btnDelete.setEnabled(false);
            btnDelete.setTextColor(Color.parseColor("#b7b8bd"));
        }
    }

    private void initListener() {
        systemMessageAdapter.setOnItemClickListener(this);
        btnDelete.setOnClickListener(this);
        selectAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_delete:
                deleteVideo();
                break;
            case R.id.select_all:
                selectAllMain();
                break;
            /*case R.id.id_tvBtn_del:
                if (mData.size() != 0) {
                    updataEditMode();

                } else {
                    showToast("暂无可选数据");
                }
                break;*/
        }
    }

    /**
     * 全选和反选
     */
    private void selectAllMain() {
        if (systemMessageAdapter == null) return;
        if (!isSelectAll) {
            for (int i = 0, j = systemMessageAdapter.getMyLiveList().size(); i < j; i++) {
                systemMessageAdapter.getMyLiveList().get(i).setBo(true);
            }
            index = systemMessageAdapter.getMyLiveList().size();
            btnDelete.setEnabled(true);
            selectAll.setText("取消全选");
            isSelectAll = true;
        } else {
            for (int i = 0, j = systemMessageAdapter.getMyLiveList().size(); i < j; i++) {
                systemMessageAdapter.getMyLiveList().get(i).setBo(false);
            }
            index = 0;
            btnDelete.setEnabled(false);
            selectAll.setText("全选");
            isSelectAll = false;
        }
        systemMessageAdapter.notifyDataSetChanged();
        setBtnBackground(index);
        tvSelectNum.setText(String.valueOf(index));
    }

    /**
     * 删除逻辑
     */
    private void deleteVideo() {
        if (index == 0) {
            btnDelete.setEnabled(false);
            return;
        }


        final AlertDialog builder = new AlertDialog.Builder(getActivity(), R.style.NormalDialogStyle1).create();

        builder.show();
        if (builder.getWindow() == null) return;
        builder.getWindow().setContentView(R.layout.pop_user);//设置弹出框加载的布局
        builder.setCanceledOnTouchOutside(false);//点击对话框外部不消失
        TextView msg = builder.findViewById(R.id.tv_msg);
        Button cancle = builder.findViewById(R.id.btn_cancle);
        Button sure = builder.findViewById(R.id.btn_sure);
        ImageView ivdismiss = builder.findViewById(R.id.id_ivDismiss);
        int[] color = new int[]{Color.parseColor("#FEB06A"), Color.parseColor("#F06400")};
        int[] color1 = new int[]{Color.parseColor("#7BB1FF"), Color.parseColor("#3282FF")};
        //取消按钮
        ShadowHelperUtil.setshadow(getActivity(), cancle,
                Color.parseColor("#FEB06A"),
                Color.parseColor("#F88C38"),
                15,
                1,
                1, color);
        //确定按钮
        ShadowHelperUtil.setshadow(getActivity(), sure,
                Color.parseColor("#7BB1FF"),
                Color.parseColor("#3282FF"),
                15,
                1,
                1, color1);

        if (msg == null || cancle == null || sure == null) return;

        if (index == 1) {
            msg.setText("删除后不可恢复，是否删除该条目？");
        } else {
            msg.setText("删除后不可恢复，是否删除这" + index + "个条目？");
        }
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        ivdismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*for (int i = systemMessageAdapter.getMyLiveList().size(), j = 0; i > j; i--) {
                    SystemMessagInfo myLive = systemMessageAdapter.getMyLiveList().get(i - 1);
                    if (myLive.isBo()) {

                        systemMessageAdapter.getMyLiveList().remove(myLive);
                        index--;
                    }
                }
                index = 0;
                tvSelectNum.setText(String.valueOf(0));
                setBtnBackground(index);
                if (systemMessageAdapter.getMyLiveList().size() == 0) {
//                    llMycollectionBottomDialog.setVisibility(View.GONE);
//                    setfadeIn();
                }
//                llMycollectionBottomDialog.setVisibility(View.GONE);
                QMUIViewHelper.fadeOut(llMycollectionBottomDialog, 500, null, true);

                updataEditMode();
                systemMessageAdapter.notifyDataSetChanged();
                builder.dismiss();*/

                for (int i = systemMessageAdapter.getMyLiveList().size(), j = 0; i > j; i--) {
                    SystemMessagInfo myLive = systemMessageAdapter.getMyLiveList().get(i - 1);
                    if (myLive.isBo()) {

                        //在这里执行删除消息接口请求
                        SystemMessagInfo sm = new SystemMessagInfo();
                        sm.setId(myLive.getId());
                        mListNewsId.add(sm);
//                        deleteNews(myLive.getId());
//                        systemMessageAdapter.getMyLiveList().remove(myLive);
                        index--;
                    }
                }
                //deleteNews(builder, mListNewsId);
            }
        });
    }

    /**
     * 删除消息接口请求
     *
     * @param builder
     * @param mListNewsId
     */
    /*private void deleteNews(final AlertDialog builder, List<SystemMessagInfo> mListNewsId) {
        final List<String> mlist = new ArrayList<>();
        for (int i = 0; i < mListNewsId.size(); i++) {
            mlist.add(mListNewsId.get(i).getId());
        }
        OkGo.<String>get(HTTPJSONConstant.News_Delet)
                .tag(TAG)
                .addUrlParams("msgIds", mlist)
                .params("sessionKey", UserManager.getSessionKey(getActivity()))
                .params("appId", HTTPJSONConstant.APP_ID)
                .execute(new MStringCallback() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.d(s);
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getString("code").equals("0")) {

                                scussec(builder, mlist);

                            } else if (object.getString("code").equals("-1")) {
                                showToast("删除失败");
                                builder.dismiss();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            showToast("删除失败");
                            builder.dismiss();
                        }
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }*/

    private void scussec(AlertDialog builder, List<String> mlist) {
        for (int i = 0; i < mlist.size(); i++) {
            String strA = mlist.get(i);
            for (int j = 0; j < systemMessageAdapter.getMyLiveList().size(); j++) {
                if (strA.equals(systemMessageAdapter.getMyLiveList().get(j).getId())) {
                    systemMessageAdapter.getMyLiveList().remove(j);
                }
            }
        }
        index = 0;
        tvSelectNum.setText(String.valueOf(0));
        setBtnBackground(index);
        if (systemMessageAdapter.getMyLiveList().size() == 0) {
//                    llMycollectionBottomDialog.setVisibility(View.GONE);
//                    setfadeIn();
            mLoadingLayout.setEmptyImage(R.mipmap.ic_unnews);
            mLoadingLayout.showEmpty();

        }
//                llMycollectionBottomDialog.setVisibility(View.GONE);
        QMUIViewHelper.fadeOut(llMycollectionBottomDialog, 500, null, true);

        updataEditMode();
        systemMessageAdapter.notifyDataSetChanged();
        builder.dismiss();
    }

    /**
     * 更新操作按钮
     */
    public String updataEditMode() {

        String text = "";

        mEditMode = mEditMode == MYLIVE_MODE_CHECK ? MYLIVE_MODE_EDIT : MYLIVE_MODE_CHECK;
        if (mEditMode == MYLIVE_MODE_EDIT) {
            text = "取消";
            fragment2 = 1;
            //idTvBtnDel.setText("取消");
//            llMycollectionBottomDialog.setVisibility(View.VISIBLE);
            QMUIViewHelper.fadeIn(llMycollectionBottomDialog, 500, null, true);

            editorStatus = true;
            selectAllMain();
        } else {
            text = "全选";
            fragment2 = 0;
            //idTvBtnDel.setText("全选");
//            llMycollectionBottomDialog.setVisibility(View.GONE);
            QMUIViewHelper.fadeOut(llMycollectionBottomDialog, 500, null, true);

            editorStatus = false;
            clearAll();
        }
        systemMessageAdapter.setEditMode(mEditMode);
        return text;
    }

    /**
     * 底部弹出选择按钮的进退场动画
     *
     * @param visible
     */
    private void setfadeIn(int visible) {
        if (llMycollectionBottomDialog.getVisibility() == visible) {
            QMUIViewHelper.fadeIn(llMycollectionBottomDialog, 500, null, true);
        } else {
            QMUIViewHelper.fadeOut(llMycollectionBottomDialog, 500, null, true);

        }
    }


    private void clearAll() {
        tvSelectNum.setText(String.valueOf(0));
        isSelectAll = false;
        selectAll.setText("全选");
        setBtnBackground(0);
        index = 0;
        for (int i = 0, j = systemMessageAdapter.getMyLiveList().size(); i < j; i++) {
            systemMessageAdapter.getMyLiveList().get(i).setBo(false);
        }
    }

    @Override
    public void onItemClickListener(int pos, List<SystemMessagInfo> myLiveList) {
        if (editorStatus) {
            SystemMessagInfo myLive = myLiveList.get(pos);
            boolean isSelect = myLive.isBo();
            if (!isSelect) {
                index++;
                myLive.setBo(true);
                if (index == myLiveList.size()) {
                    isSelectAll = true;
                    selectAll.setText("取消全选");
                }

            } else {
                myLive.setBo(false);
                index--;
                isSelectAll = false;
                selectAll.setText("全选");
            }
            setBtnBackground(index);
            tvSelectNum.setText(String.valueOf(index));
            systemMessageAdapter.notifyDataSetChanged();
        } else {
            startActivity(new Intent(getActivity(), SystemMessageDetailActivity.class)
                    .putExtra("content", mData.get(pos).getContent())
                    .putExtra("msgId", mData.get(pos).getId()));

            //getNewsData(mData.get(pos).getId(), pos);

        }
    }

    /**
     * 消息阅读状态变更
     */
    /*private void getNewsData(String msgId, final int pos) {
        OkGo.<String>get(HTTPJSONConstant.Message_Read_URL)
                .tag(TAG)
                .params("sessionKey", UserManager.getSessionKey(getActivity()))
                .params("appId", HTTPJSONConstant.APP_ID)
                .params("msgId", msgId)
                .execute(new MStringCallback() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e("消息阅读状态变更数据：" + s);
                        try {
                            JSONObject object = new JSONObject(s);
                            if (object.getString("code").equals("0")) {
                                JSONObject jsonObject = object.getJSONObject("data");
                                if (jsonObject.getString("result").equals("success")) {
                                    LogUtils.e("消息阅读状态变更成功！");
                                    mData.get(pos).setUnRead(false);
                                    systemMessageAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }

                    @Override
                    public void onFinish() {

                    }
                });
    }*/

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.id_toolbar).init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
