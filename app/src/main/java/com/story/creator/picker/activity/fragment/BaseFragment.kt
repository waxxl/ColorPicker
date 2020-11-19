package com.story.creator.picker.activity.fragment

import androidx.fragment.app.Fragment
import com.story.creator.picker.model.bean.ColorItem

abstract class BaseFragment: Fragment() {
    var colorItem : ColorItem? = null

    abstract fun invalite(colorItem: ColorItem);
}