package com.example.dgtimer.activities.settings;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import static com.example.dgtimer.ApplicationClass.DEFAULT_AMPLITUDE;
import static com.example.dgtimer.ApplicationClass.DEFAULT_VOLUME;
import static com.example.dgtimer.ApplicationClass.mSharedPreferences;

public class SettingsViewModel extends ViewModel {

    private MutableLiveData<Integer> volume = new MutableLiveData<>(),
            amplitude = new MutableLiveData<>();

    private int lastVolume, lastAmplitude;

    public SettingsViewModel(){
        lastVolume = mSharedPreferences.getInt("volume", DEFAULT_VOLUME);
        lastAmplitude = mSharedPreferences.getInt("amplitude", DEFAULT_AMPLITUDE);

        volume.setValue(lastVolume);
        amplitude.setValue(lastAmplitude);
    }

    public MutableLiveData<Integer> getVolume() {
        return volume;
    }

    public MutableLiveData<Integer> getAmplitude() {
        return amplitude;
    }

    public void onResetBtnClick(){
        volume.setValue(DEFAULT_VOLUME);
        amplitude.setValue(DEFAULT_AMPLITUDE);
    }
}
