package com.example.dgtimer.activities.main;

import android.content.Context;
import android.content.Intent;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.dgtimer.activities.timer.TimerActivity;
import com.example.dgtimer.db.Capsule;

import static com.example.dgtimer.ApplicationClass.capsuleDatabase;

public class CapsuleItemViewModel extends ViewModel {

    private int id;
    private String name, image;
    private int color;
    private MutableLiveData<Boolean> major = new MutableLiveData<>();

    public CapsuleItemViewModel(Capsule capsule){
        this.id = capsule.getId();
        this.name = capsule.getName();
        this.color = capsule.getColorAsInt();
        this.image = capsule.getImage();
        this.major.setValue(capsule.isMajor());
    }

    public void onStarClicked(){
        major.setValue(!major.getValue());
        capsuleDatabase.getCapsuleDao().updateMajor(id, major.getValue());
    }

    public void onItemClicked(Context context){
        Intent intent = new Intent(context, TimerActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public String getImage() {
        return image;
    }

    public MutableLiveData<Boolean> isMajor(){
        return major;
    }
}
