package com.example.dgtimer.activities.timer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.dgtimer.R;
import com.example.dgtimer.db.Capsule;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.VIBRATOR_SERVICE;
import static com.example.dgtimer.ApplicationClass.DEFAULT_AMPLITUDE;
import static com.example.dgtimer.ApplicationClass.DEFAULT_VOLUME;
import static com.example.dgtimer.ApplicationClass.capsuleDatabase;
import static com.example.dgtimer.ApplicationClass.mSharedPreferences;
import static com.example.dgtimer.ApplicationClass.vibratePattern;
import static com.example.dgtimer.utils.TimeUtils.stageToSecond;

public class TimerViewModel extends AndroidViewModel implements CounterViewModel.AlertListener {

    private Application mApplication;

    private LiveData<Capsule> liveData;
    private MutableLiveData<List<CounterViewModel>> times = new MutableLiveData<>();
    private int activeIndex;
    private MutableLiveData<Boolean> timerOn = new MutableLiveData<>();
    private MutableLiveData<Boolean> alarmBell = new MutableLiveData<>();
    private MutableLiveData<String> tips = new MutableLiveData<>();

    private Vibrator vibrator;
    private MediaPlayer mediaPlayer;

    public TimerViewModel(Application application, int id){
        super(application);
        mApplication = application;
        liveData = getCapsuleInfo(id);
        timerOn.setValue(timerOn.getValue() != null && timerOn.getValue());
        alarmBell.setValue(mSharedPreferences.getBoolean("alarmBell", true));
        vibrator = (Vibrator) application.getSystemService(VIBRATOR_SERVICE);

        tips.setValue(application.getString(R.string.tip1));
    }

    private LiveData<Capsule> getCapsuleInfo(int capsuleId){
        return capsuleDatabase.getCapsuleDao().searchById(capsuleId);
    }

    public LiveData<Capsule> getLiveData() {
        return liveData;
    }

    public void setTimes(String type, List<Integer> stageList){
        List<CounterViewModel> tempList = new ArrayList<>();
        for (int i = 0; i < stageList.size(); i++) {
            CounterViewModel viewModel =
                    new CounterViewModel(mApplication, type, stageList.size(), i, stageToSecond(stageList.get(i)), i == 0);
            viewModel.setAlertListener(this);
            tempList.add(viewModel);
        }
        if (stageList.size() > 1)
            tips.setValue(tips.getValue()+"\n"+mApplication.getString(R.string.tip2));

        times.setValue(tempList);
    }

    public LiveData<List<CounterViewModel>> getTimes(){
        return times;
    }

    public int getActiveIndex() {
        return activeIndex;
    }

    public LiveData<Boolean> getTimerOn(){
        return timerOn;
    }

    public LiveData<Boolean> getAlarmBell(){
        return alarmBell;
    }

    private CounterViewModel getActiveCounterViewModel(){
        return times.getValue().get(activeIndex);
    }

    public void onResume(Context context){
        mediaPlayer = MediaPlayer.create(context, context.getResources()
                .getIdentifier("alarm_"+mSharedPreferences.getInt("alarm", 0), "raw", context.getPackageName())
        );
    }

    public LiveData<String> getTips() {
        return tips;
    }

    //    ButtonClickListeners
    public void onCounterViewClick(int index){
        if (index != activeIndex){
            for (int i = 0; i < times.getValue().size(); i++) {
                times.getValue().get(i).setActive(i == index);
            }

            if (timerOn.getValue()){
                times.getValue().get(activeIndex).reset();
                timerOn.setValue(false);
            }

            activeIndex = index;
        }
    }

    public void onResetBtnClick(){
        getActiveCounterViewModel().reset();
        timerOn.setValue(false);
    }

    public void onPlayBtnClick(){
        if (timerOn.getValue() != null){
            CounterViewModel counterViewModel = getActiveCounterViewModel();

            if (!timerOn.getValue()){
                //카운터 재생
                counterViewModel.countTask();
                timerOn.setValue(true);
            }else{
                //카운터 정지
                counterViewModel.pauseTask();
                timerOn.setValue(false);
            }
        }
    }

    public void onAlarmOptionClick(){
        boolean option = !alarmBell.getValue();
        alarmBell.setValue(option);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean("alarmBell", option);
        editor.apply();
    }

    //0초 될 때 알림
    @Override
    public void onFinished() {
        if (!alarmBell.getValue()){
            int amplitude = mSharedPreferences.getInt("amplitude", DEFAULT_AMPLITUDE);
            vibrator.vibrate(
                    VibrationEffect.createWaveform(vibratePattern, new int[]{0, amplitude, 0, amplitude},
                            -1));
        }else{
            float volume = mSharedPreferences.getInt("volume", DEFAULT_VOLUME) / 100.f;
            mediaPlayer.setVolume(volume, volume);
            mediaPlayer.start();
        }
        timerOn.setValue(false);
    }

    //onStop될 때 타이머 정지
    public void onStop(){
        onResetBtnClick();
    }

    //ViewModelFactory
    static class Factory extends ViewModelProvider.AndroidViewModelFactory {
        private Application mApplication;
        private int mId;

        public Factory(@NonNull Application application, int id) {
            super(application);
            mApplication = application;
            mId = id;
        }

        @NonNull
        @Override
        @SuppressWarnings("unchecked")
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new TimerViewModel(mApplication, mId);
        }
    }
}
