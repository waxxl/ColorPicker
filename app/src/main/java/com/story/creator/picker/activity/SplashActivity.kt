package com.story.creator.picker.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import com.story.creator.picker.R

class SplashActivity : AppCompatActivity() {
    var handler : Handler = Handler(Looper.getMainLooper())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(kotlinx.coroutines.Runnable {
            var intent : Intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }, 2000)
    }
}