package com.shokry.nearby.Tools;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;


public class PermissionTool {

    public static final String PERMISSION_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION ;
    public static final String PERMISSION_location_COARSE = Manifest.permission.ACCESS_FINE_LOCATION;
    public static final String ACCESS_NETWORK = Manifest.permission.ACCESS_NETWORK_STATE ;
    public static final String PERMISSON_INTERNET = Manifest.permission.INTERNET;



    public static boolean checkAllPermission(Activity context, String[] permissions){
        boolean allPermissionGranted = true ;
        for(int i= 0; i<permissions.length ;i++){
            if(!isPermissionGranted(context, permissions[i])){
                if (ActivityCompat.shouldShowRequestPermissionRationale(context, permissions[i])) {
//                    Toast.makeText(context, "I need this permission:"+permissions[i], Toast.LENGTH_SHORT).show();
                }
                allPermissionGranted = false ;
            }
        }
        if (!allPermissionGranted) {
            ActivityCompat.requestPermissions(context, permissions, 1);
            return false;
        }else{
            return true;
        }
    }


    private static boolean isPermissionGranted(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }
}
