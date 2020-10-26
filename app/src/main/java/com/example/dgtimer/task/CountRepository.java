package com.example.dgtimer.task;

import android.os.Handler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountRepository {
    private ExecutorService executorService;
    private final Handler resultHandler;

    public CountRepository(Handler resultHandler) {
        this.resultHandler = resultHandler;
    }

    public void countTask(final int time, final RepositoryCallback<Integer> callback){
        executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                for (int i = time; !executorService.isShutdown() && i >= 0; i--) {
                    try{
                        Result<Integer> result = new Result.Success<>(i);
                        notifyResult(result, callback);

                        Thread.sleep(1000);
                    } catch (InterruptedException ie){
                        break;
                    } catch (Exception e){
                        Result<Integer> result = new Result.Error<>(e);
                        notifyResult(result, callback);
                    }
                }
            }
        });
    }

    public void pauseTask(){
        if (executorService != null && !executorService.isShutdown())
            executorService.shutdown();
    }

    private void notifyResult(final Result<Integer> result,
                              final RepositoryCallback<Integer> callback){
        resultHandler.post(new Runnable() {
            @Override
            public void run() {
                callback.onComplete(result);
            }
        });
    }

    public interface RepositoryCallback<T>{
        void onComplete(Result<T> result);
    }
}
