package com.story.creator.picker.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import com.story.creator.picker.R
import com.story.creator.picker.adapter.PaletteDetailAdapter
import com.story.creator.picker.model.bean.ColorItem
import kotlinx.android.synthetic.main.activity_palette_detail.*

class PaletteDetailActiivty : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_palette_detail)
        recycler_palette.setHasFixedSize(false)
        recycler_palette.layoutManager = LinearLayoutManager(this)
        var adapter = PaletteDetailAdapter()
        adapter.colorData = intent.getParcelableArrayListExtra<ColorItem>("colorItem") as ArrayList<ColorItem>
        recycler_palette.adapter = adapter
    }

    fun handleIntent() {

    }
}