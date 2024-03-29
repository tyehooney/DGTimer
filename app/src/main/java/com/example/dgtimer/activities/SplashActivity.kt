package com.example.dgtimer.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.dgtimer.R
import com.example.dgtimer.activities.main.MainActivity
import com.example.dgtimer.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

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
    }

    override fun onResume() {
        super.onResume()
        if (splashHandler == null) {
            startSplashAnimation()
        }
    }

    override fun onStop() {
        super.onStop()
        splashHandler?.removeCallbacks(runnableMoveToMainActivity)
        splashHandler = null
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
}