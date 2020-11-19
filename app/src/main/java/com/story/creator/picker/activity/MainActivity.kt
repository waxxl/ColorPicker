package com.story.creator.picker.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleRegistry
import com.story.creator.picker.R
import com.story.creator.picker.activity.main.MainFragment
import com.story.creator.picker.observer.MainObserver

class MainActivity : AppCompatActivity() {
    var lifecycleRegistry : LifecycleRegistry ? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        lifecycleRegistry = LifecycleRegistry(this)
        lifecycleRegistry!!.currentState = Lifecycle.State.CREATED
        lifecycle.addObserver(MainObserver())

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }
    }

    override fun onResume() {
        super.onResume()
    }
}