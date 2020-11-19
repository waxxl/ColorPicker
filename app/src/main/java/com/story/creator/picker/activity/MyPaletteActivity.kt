package com.story.creator.picker.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.GridLayoutManager
import com.story.creator.picker.R
import com.story.creator.picker.adapter.PaletteAdapter
import kotlinx.android.synthetic.main.activity_palette_detail.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class MyPaletteActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pattele)
        title_toolbar.text = getString(R.string.my_palette)
        back_toolbar.setOnClickListener(this)

        recycler_palette.setHasFixedSize(false)
        recycler_palette.layoutManager = GridLayoutManager(this, 2)
        recycler_palette.adapter = PaletteAdapter()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back_toolbar -> {
                onBackPressed()
            }
        }
    }
}