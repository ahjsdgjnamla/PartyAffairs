package com.hrdl.partyaffairs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.base.BaseActivity;
import com.lzy.okgo.OkGo;
import com.zzhoujay.richtext.RichText;
import com.zzhoujay.richtext.callback.OnImageClickListener;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：王健 on 2018/9/4
 * 邮箱：845040970@qq.com
 * 处理端-系统消息详情
 */
public class SystemMessageDetailActivity extends BaseActivity {
    @BindView(R.id.id_ivMenu)
    ImageView idIvMenu;
    @BindView(R.id.id_tvMessageDetail)
    TextView idTvMessageDetail;
    static final String test = "<h1>Test</h1><div class=\"topic_content\" itemprop=\"articleBody\">" +
            "<img alt=\"\" src=\"http://img10.360buyimg.com/imgzone/jfs/t2719/53/693438809/405912/957c1efa/5721e109N8ad86029.jpg\">" +
            "<img alt=\"\" src=\"http://img10.360buyimg.com/imgzone/jfs/t2836/30/707249522/270588/840d428a/5721e108Ne667230f.jpg\">" +
            "<img alt=\"\" src=\"http://img10.360buyimg.com/imgzone/jfs/t2305/211/1222246162/89571/4ce4f9a1/56496ac7N982aa001.jpg\">" +
            "<img alt=\"\" src=\"http://img10.360buyimg.com/imgzone/jfs/t2695/76/715579111/331050/cf2ae9f9/5721e10aNd690b026.jpg\">" +
            "<img alt=\"\" src=\"http://img10.360buyimg.com/imgzone/jfs/t2341/288/2958575364/740490/9678e90f/5721e10bNf923ebaa.jpg\">" +
            "<img alt=\"\" src=\"http://img10.360buyimg.com/imgzone/jfs/t2776/164/715581717/852142/2fa4714f/5721e10bN04e38f08.jpg\">" +
            "<img alt=\"\" src=\"http://img10.360buyimg.com/imgzone/jfs/t2104/197/2936780208/316761/f3051b63/5721e10cN1b74089c.jpg\">" +
            "<br>" +
            "</div>";
    static final String test1 = "<p>\n" +
            "    京东第三方配送服务说明\n" +
            "</p>\n" +
            "<p>\n" +
            "    <img style=\"margin: 10px; padding: 0px; vertical-align: middle;\" alt=\"\" src=\"//img30.360buyimg.com/pophelp/g14/M03/02/1E/rBEhVlHL180IAAAAAADl8Di_wVUAAAlEgP9C6EAAOYI289.jpg \n" +
            "\n" +
            " \n" +
            "\n" +
            "\"/>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <span style=\"color: rgb(255, 0, 0);\">什么是第三方配送？</span>\n" +
            "</p>\n" +
            "<p>\n" +
            "    为了保证全国各地的客户都能在京东购物，对于京东自营配送无法送达的订单会委托专业的第三方快递公司进行配送。<a href=\"//help.jd.com/user/issue/103-983.html \n" +
            "\n" +
            " \n" +
            "\n" +
            "\" target=\"_blank\">查看您是否享受该服务&gt;&gt;</a>\n" +
            "</p>\n" +
            "<p>\n" +
            "    <span style=\"color: rgb(255, 0, 0);\">服务说明：</span>\n" +
            "</p>\n" +
            "<p>\n" +
            "    1.京东委托第三方快递公司为客户提供送货上门服务，客户无须向快递公司支付额外的费用。\n" +
            "</p>\n" +
            "<p>\n" +
            "    2.客户可以在京东网站“我的订单”查询货物配送信息，也可以登录第三方快递网站查询快递信息。\n" +
            "</p>\n" +
            "<p>\n" +
            "    3.目前在山东、河北、河南、广东、黑龙江、吉林、四川、重庆、云南等省份的部分城市支持第三方货到付款业务，具体情况以提交订单时所显示为准；在广州、成都、武汉支持POS机刷卡业务；暂时不支持支票业务。\n" +
            "</p>\n" +
            "<p>\n" +
            "    4.第三方快递支持客户打开运输包装验货，商品包装完好，客户可以先签收，如果发现商品有质量问题可以致电京东客服进行售后处理。\n" +
            "</p>\n" +
            "<p>\n" +
            "    注意事项：\n" +
            "</p>\n" +
            "<p>\n" +
            "    1.请在提交订单时准确选择地址信息，否则有可能导致货到付款订单无法送达和配送超时。\n" +
            "</p>\n" +
            "<p>\n" +
            "    2.签收时请注意检查外包装是否完好，发票和配件是否齐全，签收后如果发现异常可以致电京东客服。\n" +
            "</p>\n" +
            "<p>\n" +
            "    &nbsp;\n" +
            "</p>\n";
    private RichText richText;

    private Intent intent;

    private String content,msgId;

    /**
     * TAG
     */
    private static final String TAG = "SystemMessageDetailActivity";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_system_message_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        //设置图片缓存目录
//        RichText.initCacheDir();
        assert idTvMessageDetail != null;

        intent = getIntent();

        content = intent.getStringExtra("content");
        msgId = intent.getStringExtra("msgId");

        //getNewsData();

        richText = RichText.from(content)
                .imageClick(new OnImageClickListener() {
                    @Override
                    public void imageClicked(List<String> imageUrls, int position) {
                        Calendar calendar = Calendar.getInstance();
                        int m = calendar.get(Calendar.MINUTE);
                        int s = calendar.get(Calendar.SECOND);
                        Toast.makeText(SystemMessageDetailActivity.this, "M:" + m + ",S:" + s, Toast.LENGTH_SHORT).show();
                    }
                }).into(idTvMessageDetail);
    }

    /**
     * 消息阅读状态变更
     * */
    /*private void getNewsData() {
        OkGo.<String>get(HTTPJSONConstant.Message_Read_URL)
                .tag(TAG)
                .params("sessionKey", UserManager.getSessionKey(this))
                .params("appId",HTTPJSONConstant.APP_ID)
                .params("msgId",msgId)
                .execute(new MStringCallback() {
                    @Override
                    public void onSuccess(String s) {
                        LogUtils.e("消息阅读状态变更数据："+s);
                        try {
                            JSONObject object = new JSONObject(s);
                            if(object.getString("code").equals("0")){
                                JSONObject jsonObject=object.getJSONObject("data");
                                if (jsonObject.getString("result").equals("success")){
                                    LogUtils.e("消息阅读状态变更成功！");
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
    protected void onDestroy() {
        super.onDestroy();
        richText.clear();
        richText = null;
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    protected void setListener() {
        super.setListener();
        idIvMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initImmersionBar() {
        super.initImmersionBar();
        mImmersionBar.titleBar(R.id.id_toolbar).init();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
