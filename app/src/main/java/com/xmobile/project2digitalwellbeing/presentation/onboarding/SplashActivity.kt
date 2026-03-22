package com.xmobile.project2digitalwellbeing.presentation.onboarding

import android.R.attr.repeatCount
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.DecelerateInterpolator
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.xmobile.project2digitalwellbeing.R
import com.xmobile.project2digitalwellbeing.databinding.ActivitySplashBinding
import com.xmobile.project2digitalwellbeing.presentation.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initControl()
    }

    private fun initControl() {
        startAnimationFlow()
    }

    private fun startAnimationFlow() {
        lifecycleScope.launch {

            delay(100)

            animateLogo()
            animateTitle()
            animateSubtitle()
            animateLoading()

            startDotAnimation(binding.dot1, 0)
            startDotAnimation(binding.dot2, 200)
            startDotAnimation(binding.dot3, 400)

            // navigate sau khi splash xong
            delay(2500)
            goToMain()
        }
    }

    private fun animateLogo() {
        binding.logo.animate()
            .alpha(1f)
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(600)
            .setInterpolator(DecelerateInterpolator())
            .start()
    }

    private fun animateTitle() {
        binding.title.animate()
            .alpha(1f)
            .translationY(0f)
            .setStartDelay(200)
            .setDuration(600)
            .start()
    }

    private fun animateSubtitle() {
        binding.subtitle.animate()
            .alpha(1f)
            .translationY(0f)
            .setStartDelay(300)
            .setDuration(600)
            .start()
    }

    private fun animateLoading() {
        binding.loadingContainer.animate()
            .alpha(1f)
            .setStartDelay(500)
            .setDuration(600)
            .start()
    }

    private fun startDotAnimation(view: View, delay: Long) {

        val scaleX = ObjectAnimator.ofFloat(view, View.SCALE_X, 0.9f, 1f, 0.9f)
        val scaleY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 0.9f, 1f, 0.9f)
        val alpha = ObjectAnimator.ofFloat(view, View.ALPHA, 0.3f, 1f, 0.3f)

        AnimatorSet().apply {
            playTogether(scaleX, scaleY, alpha)
            duration = 1500
            startDelay = delay
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ValueAnimator.INFINITE
            start()
        }
    }

    private fun goToMain() {
        startActivity(Intent(this, IntroActivity::class.java))
        finish()
    }
}

//animation, animatorset, delay