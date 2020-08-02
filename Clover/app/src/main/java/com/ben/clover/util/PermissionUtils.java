package com.ben.clover.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;

public class PermissionUtils {

    public static boolean checkPermission(Context mContext) {
        @SuppressLint("InlinedApi") int READ_EXTERNAL_STORAGE = mContext.checkCallingOrSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        int WRITE_EXTERNAL_STORAGE = mContext.checkCallingOrSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        return READ_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED;
    }

}
