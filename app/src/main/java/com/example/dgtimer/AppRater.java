package com.example.dgtimer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.dgtimer.activities.main.MainActivity;

import static com.example.dgtimer.ApplicationClass.GOOGLE_PLAY_LINK;
import static com.example.dgtimer.ApplicationClass.mSharedPreferences;

public class AppRater {

    private final static int DAYS_UNTIL_PROMPT = 3;
    private final static int LAUNCHES_UNTIL_PROMPT = 3;

    public static void set(Context mContext) {

        if (mSharedPreferences.getBoolean("dontshowagain", false)) {
            if (mContext instanceof MainActivity)
                ((Activity)mContext).finish();
            return ;
        }

        SharedPreferences.Editor editor = mSharedPreferences.edit();

        // 접속 횟수
        long launch_count = mSharedPreferences.getLong("launch_count", 0) + 1;
        editor.putLong("launch_count", launch_count);

        // 처음 접속 시간
        Long date_firstLaunch = mSharedPreferences.getLong("date_firstlaunch", 0);
        if (date_firstLaunch == 0) {
            date_firstLaunch = System.currentTimeMillis();
            editor.putLong("date_firstlaunch", date_firstLaunch);
        }

        // 처음 접속 시간으로부터 n일 후
        if (launch_count >= LAUNCHES_UNTIL_PROMPT && System.currentTimeMillis() >= date_firstLaunch +
                (DAYS_UNTIL_PROMPT * 24 * 60 * 60 * 1000)) {
            showRateDialog(mContext, editor);
        }else{
            if (mContext instanceof MainActivity)
                ((Activity)mContext).finish();
        }

        editor.apply();
    }

    public static void showRateDialog(final Context mContext, final SharedPreferences.Editor editor) {

        final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.ratingDialogTitle).setMessage(R.string.induceRate);
        builder.setNegativeButton(R.string.rate, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(GOOGLE_PLAY_LINK)));
                        if (editor != null) {
                            editor.putBoolean("dontshowagain", true);
                            editor.apply();
                        }
                    }
                })
                .setPositiveButton(R.string.later, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editor.putLong("date_firstlaunch", System.currentTimeMillis());
                        editor.apply();
                    }
                });

        AlertDialog ratingDialog = builder.create();

        ratingDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialogInterface) {
                if (mContext instanceof MainActivity)
                    ((Activity)mContext).finish();
            }
        });

        ratingDialog.show();
    }
}
