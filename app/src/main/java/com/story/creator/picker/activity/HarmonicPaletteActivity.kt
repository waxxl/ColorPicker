package com.story.creator.picker.activity

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.annotation.Nullable
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.story.creator.picker.R
import com.story.creator.picker.activity.fragment.BaseFragment
import com.story.creator.picker.activity.fragment.HSVFragment
import com.story.creator.picker.activity.fragment.RGBFragment
import com.story.creator.picker.adapter.ColorAdapter
import com.story.creator.picker.model.bean.ColorItem
import com.story.creator.picker.model.bean.PaletteModules
import com.story.creator.picker.viewmodel.CustomVm
import io.realm.RealmResults
import jp.wasabeef.recyclerview.animators.FadeInAnimator
import kotlinx.android.synthetic.main.activity_custom.*

class HarmonicPaletteActivity : BaseActivity, View.OnClickListener {
    constructor() {

    }

    var colorData : SparseArray<ColorItem> = SparseArray(8)

    //TODO setColorItem
    var colorItemX : ColorItem? = null

    var customVm : CustomVm? = null
    var curFragment : BaseFragment? = null
    var curPosition : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_custom)

        color_recycler.setHasFixedSize(false)
        color_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        var colorAdapter = ColorAdapter(this)

        val li: ColorAdapter.OnAddClickListener = object : ColorAdapter.OnAddClickListener {
            override fun addClick() {
                color_recycler.scrollToPosition(colorAdapter.itemCount - 1)
            }

            override fun onItemClick(colorItem : ColorItem?) {
                if (colorItem != null) {
                    colorItemX = colorItem
                    curFragment?.colorItem = colorItem
                    curFragment?.invalite(colorItem)
                }
            }
        }

        colorAdapter.setOnAddClickListener(li)
        color_recycler.adapter = colorAdapter
        color_recycler.itemAnimator = FadeInAnimator(OvershootInterpolator(2f))
        color_recycler.itemAnimator?.addDuration = 0
        color_recycler.itemAnimator?.removeDuration = 200
        color_recycler.itemAnimator?.moveDuration = 250
        color_recycler.itemAnimator?.changeDuration = 200

        var transaction = supportFragmentManager.beginTransaction()
        var fragment  = RGBFragment()
        curFragment = fragment
        fragment.rgbLiveData.value = colorItemX

        fragment.rgbLiveData.observe(this, object : Observer<ColorItem> {
            override fun onChanged(t: ColorItem) {
                colorAdapter.setSelectColorItem(t)
            }
        })

        transaction.replace(R.id.container, fragment)
        transaction.commit()

        setOnClickListener()

        customVm = ViewModelProvider(this).get(CustomVm::class.java)
        observeViewModel(customVm!!)
        customVm!!.loadDataFromDatabase()
    }

    fun setOnClickListener() {
        camera.setOnClickListener(this)
        rgb.setOnClickListener(this)
        hsv.setOnClickListener(this)
        cmyk.setOnClickListener(this)
        ral.setOnClickListener(this)
        color_value.setOnClickListener(this)
    }

    private fun observeViewModel(viewModel : CustomVm) {
        // Observe project data
        viewModel.liveData.observe(this, object : Observer<RealmResults<PaletteModules>?> {
            override fun onChanged(@Nullable result: RealmResults<PaletteModules>?) {
                if (result != null) {
                    Log.d("xxl", "observeViewModel colorItem changed")
                    //binding.setIsLoading(false)
                    //viewModel.setProject(project)
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.camera -> {

                //container?.removeAllViews()
                //container?.addView()
                //Log.d("xxl","custom camera click")
            }

            R.id.rgb -> {
                Log.d("xxl", "onClick R.id.rgb")
                var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
                var fragment = RGBFragment()
                curFragment = RGBFragment()
                fragment.rgbLiveData.value = colorItemX
                transaction.replace(R.id.container, fragment)
                transaction.commit()
            }

            R.id.hsv -> {
                var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
                var fragment = HSVFragment()
                curFragment = HSVFragment()
                transaction.replace(R.id.container, fragment)
                transaction.commit()
            }

            R.id.cmyk -> {
//                var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
//                var fragment : Fragment = RGBFragment()
//                transaction.replace(R.id.container, fragment)
//                transaction.commit()
            }

            R.id.ral -> {
//                var transaction :FragmentTransaction = supportFragmentManager.beginTransaction()
//                var fragment : Fragment = RGBFragment()
//                transaction.replace(R.id.container, fragment)
//                transaction.commit()
            }

            R.id.color_value -> {
//                var transaction :FragmentTransaction = supportFragmentManager.beginTransaction()
//                var fragment : Fragment = RGBFragment()
//                transaction.replace(R.id.container, fragment)
//                transaction.commit()
            }
        }
    }

}