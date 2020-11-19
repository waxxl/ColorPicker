package com.story.creator.picker.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleRegistry
import com.story.creator.picker.R
import com.story.creator.picker.observer.MainObserver
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity(), View.OnClickListener {
    var customPalette : TextView ? = null
    var harmonicPalette : TextView ? = null
    var imagePalette : TextView ? = null

    var lifecycleRegistry : LifecycleRegistry ? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        lifecycleRegistry = LifecycleRegistry(this)
//        lifecycleRegistry!!.currentState = Lifecycle.State.CREATED
        lifecycle.addObserver(MainObserver())

        customPalette = findViewById(R.id.custom_palette)
        harmonicPalette = findViewById(R.id.harmonic_palette)
        imagePalette = findViewById(R.id.image_palette)

        customPalette?.setOnClickListener(this)
        harmonicPalette?.setOnClickListener(this)
        imagePalette?.setOnClickListener(this)

        my_palette.setOnClickListener(this)
        favourites.setOnClickListener(this)
        settings.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.custom_palette -> {
                val intent : Intent = Intent()
                intent.setClass(this, CustomPaletteActivity::class.java)
                startActivity(intent)
            }
            R.id.harmonic_palette -> {
                val intent : Intent = Intent()
                intent.setClass(this, HarmonicPaletteActivity::class.java)
                startActivity(intent)
            }
            R.id.image_palette -> {
                val intent : Intent = Intent()
                intent.setClass(this, HarmonicPaletteActivity::class.java)
                startActivity(intent)
            }
            R.id.my_palette -> {
                val intent = Intent()
                intent.setClass(this, MyPaletteActivity::class.java)
                startActivity(intent)
            }
            R.id.favourites -> {
                val intent = Intent()
                intent.setClass(this, FavouritesActivity::class.java)
                startActivity(intent)
            }
            R.id.settings -> {
                val intent = Intent()
                intent.setClass(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
    }
}