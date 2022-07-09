package com.example.dgtimer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
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

import java.io.IOException;
import java.io.InputStream;

public class ApplicationClass extends Application {

    public static CapsuleDatabase capsuleDatabase;

    public Handler mainThreadHandler = HandlerCompat.createAsync(Looper.getMainLooper());

    public static SharedPreferences mSharedPreferences;
    public static final int DEFAULT_VOLUME = 75;
    public static final int DEFAULT_AMPLITUDE = 180;

    public static long[] vibratePattern = {0, 500, 500, 500};

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

    }

    public static String readUpdateNote(Context context, String versionName){
        int rawId = context.getResources()
                .getIdentifier("update_note_"+versionName.replace(".","_"), "raw", context.getPackageName());
        InputStream in = context.getResources().openRawResource(rawId);
        byte[] b = new byte[0];
        try {
            b = new byte[in.available()];
            in.read(b);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return new String(b);
    }

    public static void refreshData(){
        FirebaseFirestore.getInstance().collection("capsules")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful() && task.getResult() != null){
                    for (QueryDocumentSnapshot document : task.getResult()){
                        Capsule capsule = document.toObject(Capsule.class);
                        if(capsuleDatabase.capsuleDao().getByName(capsule.getName()).isEmpty())
                            capsuleDatabase.capsuleDao().insert(capsule);

                        Capsule lastCapsule = capsuleDatabase.capsuleDao().getCapsuleById(capsule.getId());
                        if (lastCapsule != null &&
                                (!lastCapsule.getName().equals(capsule.getName()) ||
                                        !lastCapsule.getStage().toString().equals(capsule.getStage().toString()))){
                            capsuleDatabase.capsuleDao().updateCapsule(capsule);
                        }
                    }
                }
            }
        });
    }
}
