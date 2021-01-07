package com.example.dgtimer.activities.settings;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.SoundPool;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dgtimer.R;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.example.dgtimer.ApplicationClass.DEFAULT_AMPLITUDE;
import static com.example.dgtimer.ApplicationClass.DEFAULT_VOLUME;
import static com.example.dgtimer.ApplicationClass.mSharedPreferences;
import static com.example.dgtimer.ApplicationClass.vibratePattern;

public class SettingsViewModel extends AndroidViewModel {

    private MutableLiveData<Integer> volume = new MutableLiveData<>(),
            amplitude = new MutableLiveData<>();

    private int lastVolume, lastAmplitude;
    private String appVersion;
    private ObservableField<String> currentAlarm = new ObservableField<>();
    private SoundPool soundPool;

    public SettingsViewModel(@NonNull Application application) throws PackageManager.NameNotFoundException {
        super(application);

        lastVolume = mSharedPreferences.getInt("volume", DEFAULT_VOLUME);
        lastAmplitude = mSharedPreferences.getInt("amplitude", DEFAULT_AMPLITUDE);
        String[] strAlarms = application.getApplicationContext().getResources().getStringArray(R.array.strAlarms);
        currentAlarm.set(strAlarms[mSharedPreferences.getInt("alarm", 0)]);

        soundPool = new SoundPool.Builder().setMaxStreams(1).build();

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

    public ObservableField<String> getCurrentAlarm() {
        return currentAlarm;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void playAlarm(Context context, int alarmId, final float volume){
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                soundPool.play(sampleId, volume, volume, 1, 0, 1);
            }
        });
        soundPool.load(context, alarmId, 1);
    }

    @BindingAdapter({"seekBarVolume", "listeningViewModel"})
    public static void onVolumeChanged(SeekBar seekBar, final MutableLiveData<Integer> volume
            , final SettingsViewModel viewModel){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                volume.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Context mContext = seekBar.getContext();
                int alarmId = mContext.getResources()
                        .getIdentifier("alarm_"+mSharedPreferences.getInt("alarm", 0), "raw", mContext.getPackageName());
                viewModel.playAlarm(mContext, alarmId, seekBar.getProgress() / 100.f);
            }
        });
    }

    @BindingAdapter({"seekBarAmplitude"})
    public static void onAmplitudeChanged(SeekBar seekBar, final MutableLiveData<Integer> amplitude){
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                amplitude.setValue(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Vibrator vibrator = (Vibrator) seekBar.getContext().getSystemService(VIBRATOR_SERVICE);
                vibrator.vibrate(
                        VibrationEffect.createWaveform(vibratePattern, new int[]{0, seekBar.getProgress(), 0, seekBar.getProgress()},
                                -1));
                amplitude.setValue(seekBar.getProgress());
            }
        });
    }
}
