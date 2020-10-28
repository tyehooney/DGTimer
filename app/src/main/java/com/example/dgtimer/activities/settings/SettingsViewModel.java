package com.example.dgtimer.activities.settings;

import android.app.Application;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import static com.example.dgtimer.ApplicationClass.DEFAULT_AMPLITUDE;
import static com.example.dgtimer.ApplicationClass.DEFAULT_VOLUME;
import static com.example.dgtimer.ApplicationClass.mSharedPreferences;

public class SettingsViewModel extends AndroidViewModel {

    private MutableLiveData<Integer> volume = new MutableLiveData<>(),
            amplitude = new MutableLiveData<>();

    private int lastVolume, lastAmplitude;
    private String appVersion;

    public SettingsViewModel(@NonNull Application application) throws PackageManager.NameNotFoundException {
        super(application);

        lastVolume = mSharedPreferences.getInt("volume", DEFAULT_VOLUME);
        lastAmplitude = mSharedPreferences.getInt("amplitude", DEFAULT_AMPLITUDE);

        volume.setValue(lastVolume);
        amplitude.setValue(lastAmplitude);

        appVersion = application.getPackageManager()
                .getPackageInfo(application.getPackageName(), 0)
                .versionName;
    }

    public MutableLiveData<Integer> getVolume() {
        return volume;
    }

    public MutableLiveData<Integer> getAmplitude() {
        return amplitude;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void onResetBtnClick(){
        volume.setValue(DEFAULT_VOLUME);
        amplitude.setValue(DEFAULT_AMPLITUDE);
    }
}
