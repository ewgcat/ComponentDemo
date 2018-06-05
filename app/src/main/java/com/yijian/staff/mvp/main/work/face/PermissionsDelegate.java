package com.yijian.staff.mvp.main.work.face;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.yijian.staff.R;

class PermissionsDelegate {

    private static final int REQUEST_CODE = 10;
    private final Activity activity;

    String[] permissionArray = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS};


    PermissionsDelegate(Activity activity) {
        this.activity = activity;
    }

    boolean hasCameraPermission() {


        for (String permissionStr : permissionArray) {
            int permissionCheckResult = ContextCompat.checkSelfPermission(
                    activity,
                    permissionStr
            );
            if (!(permissionCheckResult == PackageManager.PERMISSION_GRANTED)) return false;
        }
//        return permissionCheckResult == PackageManager.PERMISSION_GRANTED;
        return true;
    }

    void requestCameraPermission() {
        ActivityCompat.requestPermissions(
                activity,
                new String[]{Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS},
                REQUEST_CODE
        );
    }

    boolean resultGranted(int requestCode,
                          String[] permissions,
                          int[] grantResults) {

        if (requestCode != REQUEST_CODE) {
            return false;
        }

        if (grantResults.length < 1) {
            return false;
        }
        if (!(permissions[0].equals(Manifest.permission.CAMERA))) {
            return false;
        }

//        View noPermissionView = activity.findViewById(R.id.no_permission);

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//            noPermissionView.setVisibility(View.GONE);
            return true;
        }

        requestCameraPermission();
//        noPermissionView.setVisibility(View.VISIBLE);
        return false;
    }
}
