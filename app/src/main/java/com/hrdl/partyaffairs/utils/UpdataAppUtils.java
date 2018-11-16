package com.hrdl.partyaffairs.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;
import com.vector.update_app.utils.AppUpdateUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.hrdl.partyaffairs.app.OkGoUpdateHttpUtil;
import com.hrdl.partyaffairs.callback.OnRxPermissionsListener;
import com.hrdl.partyaffairs.constant.PermissionConstant;
import io.reactivex.disposables.Disposable;

/**
 * 作者：王健 on 2018/10/12
 * 邮箱：845040970@qq.com
 * 版本更新工具类
 * 出现进度为负数的时候是因为计算类型溢出，原因是后台没有在输出流上加上文件的长度
 *
 */
public class UpdataAppUtils {
    /**
     * Toast提示，true，false，不提示
     * */
    private  boolean isToast=false;
    private String mUpdateUrl = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json.txt";

    public  void showUpdataApp(Context context, boolean isToast1){
       isToast=isToast1;
        showPermission(context);
    }
    /**
     * 权限请求判断
     * */
    private  void showPermission(final Context context) {
        PermissionUtil.setOneOrMorePermissions((Activity) context, new OnRxPermissionsListener() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Boolean b) {
                if(b){
                    showUpdataAppDialog(context);
                }
            }
            @Override
            public void onError(Throwable e) {
                ToastUtils.showToast(context,"权限请求被拒绝");
            }

            @Override
            public void onComplete() {

            }
        }, PermissionConstant.WRITE_EXTERNAL_STORAGE, PermissionConstant.READ_EXTERNAL_STORAGE);
    }

    /**
     * 调起对话框
     * */
    private  void showUpdataAppDialog(final Context context) {
        String path = Environment.getExternalStorageDirectory().getAbsolutePath();
        Map<String, String> params = new HashMap<String, String>();
        params.put("appKey", "ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f");
        params.put("appVersion", AppUpdateUtils.getVersionName(context));
        params.put("key1", "value2");
        params.put("key2", "value3");
        new UpdateAppManager
                .Builder()
//                .setTopPic(R.mipmap.top_6)
//                .setThemeColor(context.getResources().getColor(R.color.colorPrimary))
                //必须设置，当前Activity
                .setActivity((Activity) context)
                //必须设置，实现httpManager接口的对象
                .setHttpManager(new OkGoUpdateHttpUtil())
                //必须设置，更新地址
                .setUpdateUrl(mUpdateUrl)
                //以下设置，都是可选
                //设置请求方式，默认get
                .setPost(false)
                //添加自定义参数，默认version=1.0.0（app的versionName）；apkKey=唯一表示（在AndroidManifest.xml配置）
                .setParams(params)
                //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
                .setTargetPath(path)
                //设置appKey，默认从AndroidManifest.xml获取，如果，使用自定义参数，则此项无效
                .setAppKey("ab55ce55Ac4bcP408cPb8c1Aaeac179c5f6f")
                .build()
                //检测是否有新版本
                .checkNewApp(new UpdateCallback() {
                    /**
                     * 解析json,自定义协议
                     *
                     * @param json 服务器返回的json
                     * @return UpdateAppBean
                     */
                    @Override
                    protected UpdateAppBean parseJson(String json) {
                        LogUtils.e("获取版本更新="+json);
                        UpdateAppBean updateAppBean = new UpdateAppBean();
                        try {
                            JSONObject object = new JSONObject(json);
                                String updata;
                                String new_version;
                                String apk_file_url;
                                String update_log;
                                String target_size;
                                boolean updataboolean;

                                updata=object.getString("update");
                                new_version=object.getString("new_version");
                                apk_file_url=object.getString("apk_file_url");
                                update_log=object.getString("update_log");
                                target_size=object.getString("target_size");
                                updataboolean=object.getBoolean("constraint");
                                updateAppBean
                                        //（必须）是否更新Yes,No
                                        .setUpdate(updata)
                                        //（必须）新版本号，
                                        .setNewVersion(new_version)
                                        //（必须）下载地址
                                        .setApkFileUrl(apk_file_url)
                                        //（必须）更新内容
                                        .setUpdateLog(update_log)
                                        //大小，不设置不显示大小，可以不设置
                                        .setTargetSize(target_size+"M")
                                        //是否强制更新，可以不设置
                                        .setConstraint(updataboolean)
                                        //设置md5，可以不设置
                                        .setNewMd5("");
                                if(isToast){
                                    ToastUtils.showToast(context,"请求失败");
                                }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        return updateAppBean;
                    }
                    /**
                     * 有新版本
                     *
                     * @param updateApp        新版本信息
                     * @param updateAppManager app更新管理器
                     */
                    @Override
                    public void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                        updateAppManager.showDialogFragment();
                        //自定义对话框
//                        showDiyDialog(updateApp, updateAppManager);
                    }
                    /**
                     * 网络请求之前
                     */
                    @Override
                    public void onBefore() {
//                        CProgressDialogUtils.showProgressDialog(context);
                    }
                    /**
                     * 网路请求之后
                     */
                    @Override
                    public void onAfter() {
//                        CProgressDialogUtils.cancelProgressDialog(context);
                    }
                    /**
                     * 没有新版本
                     */
                    @Override
                    public void noNewApp(String error) {
                        if(isToast){
                            ToastUtils.showToast(context,"没有新版本");
                        }

                    }
                });
    }
}
