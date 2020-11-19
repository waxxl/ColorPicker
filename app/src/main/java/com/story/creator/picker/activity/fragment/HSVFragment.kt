package com.story.creator.picker.activity.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.story.creator.picker.R
import com.story.creator.picker.model.bean.ColorItem
import kotlinx.android.synthetic.main.hsv_layout.*
import kotlinx.android.synthetic.main.rgb_layout.*

class HSVFragment : BaseFragment() {
    override fun invalite(item: ColorItem) {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view: View = inflater?.inflate(R.layout.rgb_layout, null)
        colorItem?.colorR?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                h_color_seek_bar.setProgress(it, true)
            } else {
                h_color_seek_bar.setProgress(it)
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}