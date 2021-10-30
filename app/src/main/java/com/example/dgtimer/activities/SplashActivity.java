package com.example.dgtimer.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.dgtimer.R;
import com.example.dgtimer.activities.main.MainActivity;
import com.example.dgtimer.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    private Handler splashHandler;
    private final Runnable runnableMoveToMainActivity = new Runnable() {
        @Override
        public void run() {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivitySplashBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        Animation ivAnim = AnimationUtils.loadAnimation(this, R.anim.splash_image_view);
        Animation tvAnim = AnimationUtils.loadAnimation(this, R.anim.splash_text_view);

        binding.ivLogo.startAnimation(ivAnim);
        binding.tvTitle.startAnimation(tvAnim);

        splashHandler = new Handler(Looper.getMainLooper());
        splashHandler.postDelayed(runnableMoveToMainActivity, 1500);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (splashHandler != null) {
            splashHandler.removeCallbacks(runnableMoveToMainActivity);
        }
    }
}