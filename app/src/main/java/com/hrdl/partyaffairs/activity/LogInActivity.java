package com.hrdl.partyaffairs.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.gyf.barlibrary.ImmersionBar;
import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.activity.BottomTab.BottomTabActivity1;
import com.hrdl.partyaffairs.app.UserManager;
import com.hrdl.partyaffairs.base.BaseActivity;
import com.hrdl.partyaffairs.callback.MStringCallback;
import com.hrdl.partyaffairs.constant.Const;
import com.hrdl.partyaffairs.constant.HTTPJSONConstant;
import com.hrdl.partyaffairs.entity.User;
import com.hrdl.partyaffairs.utils.ExceptionUtil;
import com.hrdl.partyaffairs.utils.LogUtils;
import com.hrdl.partyaffairs.utils.SharedPrefsUtil;
import com.hrdl.partyaffairs.utils.SoftHideKeyBoardUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhangwei on 2018/11/13.
 * 登录页面
 */
public class LogInActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.etUser)
    EditText etUser;
    @BindView(R.id.etPwd)
    EditText etPwd;
    @BindView(R.id.masses_id)
    Button massesId;
    @BindView(R.id.member_id)
    Button memberId;

    /**
     * TAG
     */
    private static final String TAG = "LogInActivity";

    @Override
    protected int setLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initImmersionBar() {
        mImmersionBar = ImmersionBar.with(this).keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        mImmersionBar.init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        SoftHideKeyBoardUtil.assistActivity(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        super.initView();
        massesId.setOnClickListener(this);
        memberId.setOnClickListener(this);
    }

    /**
     * 登录操作
     */
    private void UserLogin() {
        String name = etUser.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("请输入党员代码");
            return;
        }
        //保存输入用户名
        SharedPrefsUtil.putValue(this, Const.SP_KEY, Const.SP_USER_NAME, name);
        String pwd = etPwd.getText().toString();
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        showLoading("登录中...", new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                OkGo.getInstance().cancelTag(TAG);
                dismissLoading();
            }
        });
        OkGo.<String>post(HTTPJSONConstant.USERS_LogIn_URL)
                .tag(TAG)
                .params("token",SharedPrefsUtil.getValue(LogInActivity.this, Const.SP_KEY,Const.SP_ToKen,""))
                .params("user_login",name)
                .params("user_password",pwd)
                .execute(new MStringCallback() {
                    @Override
                    public void onSuccess(String response) {
                        LogUtils.e("登录数据："+response);
                        try {
                            JSONObject object = new JSONObject(response);
                            // 解析数据
                            if (HTTPJSONConstant.CODE_RESULT_OK.equals(object.getString("success"))) {

                                JSONObject object1 = object.getJSONObject("data");
                                JSONObject object2 = object1.getJSONObject("user");

                                //用户信息
                                User user = new User();
                                //用户ID
                                user.setId(object1.getString("user_id"));
                                //用户名称
                                user.setName(object2.getString("user_name"));
                                //用户性别
                                user.setSex(object2.getString("user_sex"));
                                //登录用户名
                                user.setLoginName(object2.getString("user_login"));
                                //所属党支部id
                                user.setCompany(object2.getString("department_id"));
                                //手机号
                                user.setMobile(object2.getString("user_cellphone"));
                                //登录密码（单向加密后的）
                                user.setPassWord(object2.getString("user_password"));
                                //用户类型;0:超级管理员;1:单位超级管理员;2:领导;3:党员;4:群众
                                user.setUserType(object2.getString("type"));
                                //职务
                                user.setUserDuty(object2.getString("user_duty"));
                                //人员简介
                                user.setUserExcerpt(object2.getString("user_excerpt"));
                                //头像地址（缩略图）
                                JSONObject object3 = object2.getJSONObject("more");
                                user.setHeadUrl(object3.getString("thumbnail"));
                                /**
                                 * 以下信息可能为Null
                                 */
                                //用户籍贯
                                user.setUserBirthplace(Const.getContent(object2,"user_birthplace"));
                                //民族
                                user.setUserNation(Const.getContent(object2,"user_nation"));
                                //教育程度
                                user.setUserEducationLevel(Const.getContent(object2,"user_education_level"));
                                //邮箱
                                user.setEmail(Const.getContent(object2,"user_email"));

                                //保存用户信息到本地
                                UserManager.saveUserInfoToSP(LogInActivity.this, user);
                                //保存access_token
                                UserManager.saveSessionKey(LogInActivity.this, object1.getString("access_token"));
                                //保存登录状态
                                SharedPrefsUtil.putValue(context, Const.SP_KEY, Const.SP_LOGIN, true);
                                UserManager.login();
                                showToast("登录成功");
                            } else {
                                showToast(object.getString("message"));
                            }
                        } catch (Exception e) {
                            showToast("无法连接服务器");
                            ExceptionUtil.handleException(e);
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        showToast("无法连接到网络");
                    }

                    @Override
                    public void onFinish() {
                        dismissLoading();
                        startActivity(new Intent(LogInActivity.this, BottomTabActivity1.class));
                    }
                });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.masses_id:
                startActivity(new Intent(LogInActivity.this, BottomTabActivity1.class));
                finish();
                break;
            case R.id.member_id:
                UserLogin();
                break;
        }
    }

}
