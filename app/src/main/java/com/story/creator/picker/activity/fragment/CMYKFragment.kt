package com.story.creator.picker.activity.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.story.creator.picker.R
import com.story.creator.picker.model.bean.ColorItem
import kotlinx.android.synthetic.main.rgb_layout.*
import kotlinx.android.synthetic.main.rgb_layout.view.*

class CMYKFragment : BaseFragment() {
    var rgbLiveData = MutableLiveData<ColorItem>()

    var r_seekBar: AppCompatSeekBar? = null
    var g_seekBar: AppCompatSeekBar? = null
    var b_seekBar: AppCompatSeekBar? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.rgb_layout, null)
        r_seekBar = view.findViewById(R.id.r_color_seek_bar)
        g_seekBar = view.findViewById(R.id.g_color_seek_bar)
        b_seekBar = view.findViewById(R.id.b_color_seek_bar)
        return view
    }

    override fun invalite(item: ColorItem) {
        colorItem = item;
        setProgress()
    }

    fun setProgress() {
        if (colorItem != null) {
            colorItem!!.colorR?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    r_seekBar?.setProgress(it, true)
                } else {
                    r_seekBar?.setProgress(it)
                }
            }

            colorItem?.colorG?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    g_seekBar?.setProgress(it, true)
                } else {
                    g_seekBar?.setProgress(it)
                }
            }

            colorItem?.colorB?.let {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    b_seekBar?.setProgress(it, true)
                } else {
                    b_seekBar?.setProgress(it)
                }
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgress()
        r_color_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (colorItem != null && colorItem is ColorItem) {
                    colorItem?.colorR = progress
                }
                r_value.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        g_color_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (colorItem != null && colorItem is ColorItem) {
                    colorItem?.colorG = progress
                }
                g_value.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })

        b_color_seek_bar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (colorItem != null && colorItem is ColorItem) {
                    colorItem?.colorB = progress
                }
                b_value.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }
        })
    }
}