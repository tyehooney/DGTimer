package com.example.dgtimer.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.dgtimer.R
import com.example.dgtimer.activities.main.MainActivity
import com.example.dgtimer.databinding.ActivitySplashBinding

class SplashActivity: AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private var splashHandler: Handler? = null
    private val runnableMoveToMainActivity = Runnable {
        startActivity(MainActivity.createMainActivityIntent(this))
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startSplashAnimation()
    }

    private fun startSplashAnimation() {
        val ivAnim = AnimationUtils.loadAnimation(this, R.anim.splash_image_view)
        val tvAnim = AnimationUtils.loadAnimation(this, R.anim.splash_text_view)

        binding.ivLogo.startAnimation(ivAnim)
        binding.tvTitle.startAnimation(tvAnim)

        splashHandler = Handler(Looper.getMainLooper()).apply {
            postDelayed(runnableMoveToMainActivity, 1500L)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        splashHandler?.removeCallbacks(runnableMoveToMainActivity)
    }
}