package cn.bluseli.android.data.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

public class PermisionUtils {
    /**
     * 如果APP没有存储权限则动态申请
     */
    public static void verifyStoragePermissions(Activity activity) {
        // 检查是否拥有写权限
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // 没有权限则询问用户授权
            ActivityCompat.requestPermissions(activity,new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            },1);
        }
    }
}

