package com.example.dgtimer.activities.timer;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dgtimer.ApplicationClass;
import com.example.dgtimer.task.CountRepository;
import com.example.dgtimer.task.Result;

public class CounterViewModel extends AndroidViewModel {
    private final CountRepository repository;

    private String type;
    private int total;
    private int index;
    private int time;
    private MutableLiveData<Integer> currentTime = new MutableLiveData<>();
    private MutableLiveData<Boolean> active = new MutableLiveData<>();
    private AlertListener listener;

    public CounterViewModel(Application application, String type, int total, int index, int time, boolean active){
        super(application);
        repository = new CountRepository(((ApplicationClass)application).mainThreadHandler);
        this.type = type;
        this.total = total;
        this.index = index;
        this.time = time;
        this.currentTime.setValue(time);
        this.active.setValue(active);
    }

    public String getType() {
        return type;
    }

    public int getTotal() {
        return total;
    }

    public int getIndex() {
        return index;
    }

    public int getTime() {
        return time;
    }

    public LiveData<Integer> getCurrentTime() {
        return currentTime;
    }

    public void setTime(int time){
        currentTime.setValue(time);
    }

    public LiveData<Boolean> isActive() {
        return active;
    }

    public void setActive(boolean b){
        if (!b)
            reset();
        active.setValue(b);
    }

    public void countTask(){
        if (currentTime.getValue() != null && active.getValue() != null){
            if (active.getValue()){
                repository.countTask(currentTime.getValue(), new CountRepository.RepositoryCallback<Integer>() {
                    @Override
                    public void onComplete(Result<Integer> result) {
                        if (result instanceof Result.Success){
                            int resultTime = ((Result.Success<Integer>) result).data;

                            if (resultTime == 0)
                                listener.onFinished();

                            currentTime.setValue(resultTime);
                        }else if(result instanceof Result.Error){
                            Toast.makeText(getApplication().getApplicationContext(),
                                    ((Result.Error<Integer>) result).exception.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
    }

    public void pauseTask(){
        repository.pauseTask();
    }

    public void reset(){
        pauseTask();
        currentTime.setValue(time);
    }

    public void setAlertListener(AlertListener listener){
        this.listener = listener;
    }

    interface AlertListener{
        void onFinished();
    }
}
