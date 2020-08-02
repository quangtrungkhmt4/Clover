package com.ben.clover.util;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.ben.clover.R;
import com.google.android.material.snackbar.Snackbar;

public class DialogUtil {

    @SuppressLint("NewApi")
    public static void initDialogNoti(Context context, String message){
        Dialog dialog = new Dialog(context,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        dialog.setContentView(R.layout.dialog_connection);
        dialog.setCancelable(true);

        TextView tvnoti = dialog.findViewById(R.id.tvNoti);
        tvnoti.setText(message);

        dialog.show();
    }

    public static void snackBarNoti(View view, String message){
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_LONG);
        snackbar.show();
    }

    public static Dialog initDialogProcess(Context context){
        Dialog dialogProcess = new Dialog(context,android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            dialogProcess.getWindow().setType(WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY);
        else {
            dialogProcess.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        dialogProcess.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
//        dialogProcess.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogProcess.setContentView(R.layout.dialog_progress);
        dialogProcess.setCancelable(false);


        RotateAnimation anim = new RotateAnimation(0f, 360f, 90f, 90f);
        anim.setInterpolator(new LinearInterpolator());
        anim.setRepeatCount(Animation.INFINITE);
        anim.setDuration(900);

        final ImageView splash = dialogProcess.findViewById(R.id.imgClover);
        splash.startAnimation(anim);
//        splash.setAnimation(null);

        return dialogProcess;
    }

}
