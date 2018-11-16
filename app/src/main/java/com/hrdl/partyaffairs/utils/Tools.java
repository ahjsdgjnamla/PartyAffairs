package com.hrdl.partyaffairs.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.hrdl.partyaffairs.R;
import com.hrdl.partyaffairs.callback.OnRxPermissionsListener;
import com.hrdl.partyaffairs.constant.Const;
import com.hrdl.partyaffairs.entity.AccidentType;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 工具类
 *
 * @author dzb
 */
public class Tools {

    private static final String TAG = "Tools";

    /**
     * Toast信息
     */
    public static void showInfo(Context context, String msg) {
        Toast.makeText(context.getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取当前软件版本Name
     */
    public static String getCurrentVersionName(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
        return packageInfo.versionName;
    }

    /**
     * 获取当前软件版本code
     */
    public static int getCurrentVersionCode(Context context) throws PackageManager.NameNotFoundException {
        PackageManager packageManager = context.getPackageManager();
        String packageName = context.getPackageName();
        PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
        return packageInfo.versionCode;
    }

    /**
     * 清除指定id通知
     *
     * @param nId 通知 id
     */
    public static void clearNotification(Context context, int nId) {
        try {
            //1> 获取NotificationManager对象
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (manager == null) {
                Log.e("清除通知", "清除通知失败 id=" + nId);
                return;
            }
            manager.cancel(nId);
        } catch (Exception e) {
            ExceptionUtil.handleException(e);
        }
    }

    /**
     * 清除所有通知
     */
    public static void clearNotificationAll(Context context) {
        //1> 获取NotificationManager对象
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) {
            Log.e("清除通知", "清除全部通知失败");
            return;
        }
        manager.cancelAll();
    }

    /**
     * 保存案件举报类型到sp中
     */
    public static boolean saveAccidentTypeToSP(Context context, ArrayList<AccidentType> types) {
        if (types == null) {
            return false;
        }
        Set<String> listId = new HashSet<>();
        for (AccidentType type : types) {
            listId.add(type.getId());
        }
        //替换缓存数据
        SharedPreferences preferences = context.getApplicationContext().getSharedPreferences(Const.SP_ACCIDENT_TYPE_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        //先删除旧数据
        editor.clear();
        /*Set<String> set = preferences.getStringSet("listId", null);
        if (set != null) {
            for (Object aSet : set) {
                try {
                    String id = (String) aSet;
                    editor.remove("id" + id);
                    editor.remove("name" + id);
                } catch (Exception e) {
                    ExceptionUtil.handleException(e);
                }
            }
        }*/
        editor.putStringSet("listId", listId);
        for (int i = 0; i < listId.size(); i++) {
            editor.putString("id" + types.get(i).getId(), types.get(i).getId());
            editor.putString("name" + types.get(i).getId(), types.get(i).getName());
        }
        return editor.commit();
    }

    /**
     * 获取当前用户本地案件举报类型数据
     */
    public static ArrayList<AccidentType> getSPAccidentType(Context context) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(Const.SP_ACCIDENT_TYPE_KEY, Context.MODE_PRIVATE);
        Set<String> listId = sp.getStringSet("listId", null);
        if (listId == null) {
            /*types.add(new AccidentType("", "民事案件"));
            types.add(new AccidentType("", "环境案件"));
            types.add(new AccidentType("", "刑事案件"));*/
            return null;
        } else {
            ArrayList<AccidentType> types = new ArrayList<>();
            for (String id : listId) {
                AccidentType type = new AccidentType();
                type.setId(sp.getString("id" + id, ""));
                type.setName(sp.getString("name" + id, ""));
                types.add(type);
            }
            ArrayList<AccidentType> list = new ArrayList<>();
            for (int i = types.size() - 1; i >= 0; i--) {
                list.add(types.get(i));
            }
            return list;
        }
    }

    /**
     * 获取缓存图片目录
     *
     * @return 缓存目录path
     */
    public static String getImgPath() {
        File file = new File(Const.IMG_PATH);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getPath();
            } else {
                return null;
            }
        } else {
            return file.getPath();
        }
    }

    /**
     * 获取缓存目录
     *
     * @return 缓存目录path
     */
    public static String getCachePath() {
        File file = new File(Const.CACHE_PATH);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getPath();
            } else {
                return null;
            }
        } else {
            return file.getPath();
        }
    }

    /**
     * 清除缓存文件夹文件
     */
    public static void clearCacheFile() {
        String cachePath = getCachePath();
        if (cachePath == null) {
            return;
        }
        File cacheDir = new File(getCachePath());
        File[] files = cacheDir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    if (file.delete()) {
                        Log.i(TAG, "删除图片 " + file.getPath());
                    } else {
                        Log.i(TAG, "删除图片失败");
                    }
                }
            }
        }
    }

    /**
     * 获取缓存目录
     *
     * @return 缓存目录path
     */
    public static String getDowloadPath() {
        File file = new File(Const.DOWNLOAD_PATH);
        if (!file.exists()) {
            if (file.mkdirs()) {
                return file.getPath();
            } else {
                return null;
            }
        } else {
            return file.getPath();
        }
    }

    /**
     * 校验某个服务是否还存在
     */
    public static boolean isServiceRunning(Context context, Class<? extends Service> service) {
        // 校验服务是否还存在
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return false;
        }
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(100);
        for (ActivityManager.RunningServiceInfo info : services) {
            // 得到所有正在运行的服务的名称
            String name = info.service.getClassName();
            if (service.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 关闭键盘
     */
    public static void closeInputMethod(Activity activity, MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = activity.getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(activity, view.getWindowToken());
            }
        }
    }

    /**
     * 判定是否需要隐藏
     */
    private static boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            return (ev.getX() <= left) || (ev.getX() >= right) || (ev.getY() <= top) || (ev.getY() >= bottom);
        }
        return false;
    }

    // 隐藏软键盘
    private static void HideSoftInput(Activity activity, IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (manager == null) {
                Log.e("隐藏键盘", "隐藏键盘失败");
                return;
            }
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * @param listener    权限请求外部接口调用
     * @param permissions 要请求的权限，可以是多个
     */
    public static void setOneOrMorePermissions(Activity activity, final OnRxPermissionsListener listener, final String... permissions) {
        RxPermissions rxPermissions = new RxPermissions(activity);
        rxPermissions.request(permissions)
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        listener.onSubscribe(d);
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        listener.onNext(aBoolean);
                        /*if (aBoolean) {
                          //同意权限
                        } else {
                            showToast("拒绝请求权限");
                        }*/
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e);
                    }

                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }
                });
    }

    /**
     * 显示确认对话框
     *
     * @param context       context
     * @param message       对话框提示内容
     * @param enterListener 点击确认操作监听
     */
    public static void showEnterDialog(Context context, String message, final View.OnClickListener enterListener) {
        final AlertDialog dialog = new AlertDialog.Builder(context, R.style.NormalDialogStyle1).create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        if (dialog.getWindow() == null) {
            return;
        }
        @SuppressLint("InflateParams")
        View vDialog = LayoutInflater.from(context).inflate(R.layout.dialog_enter, null, false);
        dialog.getWindow().setContentView(vDialog);
        ((TextView) vDialog.findViewById(R.id.tvHint)).setText(message);
        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (v.getId() == R.id.tvEnter) {
                    enterListener.onClick(v);
                }
            }
        };
        vDialog.findViewById(R.id.ibClose).setOnClickListener(listener);
        vDialog.findViewById(R.id.tvCancel).setOnClickListener(listener);
        vDialog.findViewById(R.id.tvEnter).setOnClickListener(listener);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        // 获取屏幕宽、高用
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        // 宽度设置为屏幕的0.8
        params.width = (int) (d.widthPixels * 0.8);
        dialog.getWindow().setAttributes(params);
    }

    /**
     * 显示确认对话框
     *
     * @param context           context
     * @param message           对话框提示内容
     * @param onDismissListener 对话框取消监听
     */
    public static void showOkDialog(Context context, String message, DialogInterface.OnDismissListener onDismissListener) {
        final AlertDialog dialog = new AlertDialog.Builder(context, R.style.NormalDialogStyle1).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        if (dialog.getWindow() == null) {
            return;
        }
        @SuppressLint("InflateParams") final View vDialog = LayoutInflater.from(context).inflate(R.layout.dialog_enter_ok, null, false);
        dialog.getWindow().setContentView(vDialog);
        ((TextView) vDialog.findViewById(R.id.tvHint)).setText(message);
        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
        vDialog.findViewById(R.id.ibClose).setOnClickListener(listener);
        vDialog.findViewById(R.id.tvEnter).setOnClickListener(listener);
        if (onDismissListener != null) {
            dialog.setOnDismissListener(onDismissListener);
        }
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        // 获取屏幕宽、高用
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        // 宽度设置为屏幕的0.8
        params.width = (int) (d.widthPixels * 0.8);
        dialog.getWindow().setAttributes(params);
    }

    /**
     * 显示登录过期对话框
     *
     * @param context           context
     * @param onDismissListener 对话框取消监听
     */
    public static void showLoginInvalidDialog(Context context, DialogInterface.OnDismissListener onDismissListener) {
        final AlertDialog dialog = new AlertDialog.Builder(context, R.style.NormalDialogStyle1).create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        if (dialog.getWindow() == null) {
            return;
        }
        @SuppressLint("InflateParams") final View vDialog = LayoutInflater.from(context).inflate(R.layout.dialog_login_invalid, null, false);
        dialog.getWindow().setContentView(vDialog);
        final View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        };
        vDialog.findViewById(R.id.ibClose).setOnClickListener(listener);
        vDialog.findViewById(R.id.tvEnter).setOnClickListener(listener);
        if (onDismissListener != null) {
            dialog.setOnDismissListener(onDismissListener);
        }

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        // 获取屏幕宽、高用
        DisplayMetrics d = context.getResources().getDisplayMetrics();
        // 宽度设置为屏幕的0.8
        params.width = (int) (d.widthPixels * 0.8);
        dialog.getWindow().setAttributes(params);
    }
}