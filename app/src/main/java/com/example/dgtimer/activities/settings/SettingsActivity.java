package com.example.dgtimer.activities.settings;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.dgtimer.R;
import com.example.dgtimer.databinding.ActivitySettingsBinding;

import static com.example.dgtimer.ApplicationClass.DEFAULT_AMPLITUDE;
import static com.example.dgtimer.ApplicationClass.DEFAULT_VOLUME;
import static com.example.dgtimer.ApplicationClass.GOOGLE_PLAY_LINK;
import static com.example.dgtimer.ApplicationClass.mSharedPreferences;

public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    private SettingsViewModel viewModel;

    private String[] strAlarms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);

        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        final SharedPreferences.Editor editor = mSharedPreferences.edit();

        viewModel.getVolume().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                editor.putInt("volume", integer);
                editor.apply();
            }
        });

        viewModel.getAmplitude().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                editor.putInt("amplitude", integer);
                editor.apply();
            }
        });

        strAlarms = getResources().getStringArray(R.array.strAlarms);
    }

    public void onFinishSettings(View view){
        finish();
    }

    public void onGoReviewsClick(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(GOOGLE_PLAY_LINK));
        startActivity(intent);
    }

    public void onResetBtnClick(View view){
        viewModel.getVolume().setValue(DEFAULT_VOLUME);
        viewModel.getAmplitude().setValue(DEFAULT_AMPLITUDE);
        mSharedPreferences.edit().putInt("alarm", 0).apply();
        viewModel.getCurrentAlarm().set(strAlarms[0]);
    }

    public void onSelectAlarmClick(View view){
        final int[] currentIdx = {0};
        String strCurrentAlarm = viewModel.getCurrentAlarm().get();
        for (int i = 0; i < strAlarms.length; i++) {
            if (strCurrentAlarm.equals(strAlarms[i])){
                currentIdx[0] = i;
                break;
            }
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle(R.string.selectAlarm).setSingleChoiceItems(strAlarms, currentIdx[0],
                new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int alarmId = getResources().getIdentifier("alarm_"+which, "raw", getPackageName());
                float fVolume = mSharedPreferences.getInt("volume", DEFAULT_VOLUME) / 100.f;
                viewModel.playAlarm(SettingsActivity.this, alarmId, fVolume);
                currentIdx[0] = which;
            }
        }).setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.getCurrentAlarm().set(strAlarms[currentIdx[0]]);
                mSharedPreferences.edit()
                        .putInt("alarm", currentIdx[0])
                        .apply();
            }
        }).setNegativeButton(R.string.cancel, null).create().show();
    }
}