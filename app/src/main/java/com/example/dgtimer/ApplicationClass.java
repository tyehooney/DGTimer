package com.example.dgtimer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;

import androidx.annotation.NonNull;
import androidx.core.os.HandlerCompat;
import androidx.room.Room;

import com.example.dgtimer.db.Capsule;
import com.example.dgtimer.db.CapsuleDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class ApplicationClass extends Application {

    public static CapsuleDatabase capsuleDatabase;

    public Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    public static SharedPreferences mSharedPreferences;
    public static final int DEFAULT_VOLUME = 75;
    public static final int DEFAULT_AMPLITUDE = 180;

    public static final String GOOGLE_PLAY_LINK = "http://play.google.com/store/apps/details?id=com.tyehooney.dgtimer";

    @Override
    public void onCreate() {
        super.onCreate();

        if (mSharedPreferences == null){
            mSharedPreferences = getSharedPreferences("userOption", MODE_PRIVATE);
        }

        if (capsuleDatabase == null){
            capsuleDatabase = Room.databaseBuilder(this, CapsuleDatabase.class, "capsules")
                    .allowMainThreadQueries().build();
        }

//        인터넷 있을 시 firestore data를 로컬 db에 업데이트
        refreshData(this);
    }

//    인터넷 연결 여부 확인
    public static boolean hasInternet(Context context){
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    public static void refreshData(Context context){
        if (hasInternet(context)){
            FirebaseFirestore firestore = FirebaseFirestore.getInstance();
            firestore.collection("capsules")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful() && task.getResult() != null){
                        for (QueryDocumentSnapshot document : task.getResult()){
                            Capsule capsule = document.toObject(Capsule.class);
                            if(capsuleDatabase.getCapsuleDao().getByName(capsule.getName()).isEmpty())
                                capsuleDatabase.getCapsuleDao().insert(capsule);

                            Capsule lastCapsule = capsuleDatabase.getCapsuleDao().getCapsuleById(capsule.getId());
                            if (lastCapsule != null &&
                                    (!lastCapsule.getName().equals(capsule.getName()) ||
                                            !lastCapsule.getStage().toString().equals(capsule.getStage().toString()))){
                                capsuleDatabase.getCapsuleDao().updateCapsule(capsule);
                            }
                        }
                    }
                }
            });
        }
    }
}
