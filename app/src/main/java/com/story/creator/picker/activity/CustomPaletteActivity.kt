package com.story.creator.picker.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.SparseArray
import android.view.GestureDetector
import android.view.GestureDetector.OnGestureListener
import android.view.MotionEvent
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.story.creator.picker.R
import com.story.creator.picker.activity.fragment.*
import com.story.creator.picker.adapter.ColorAdapter
import com.story.creator.picker.constants.getRandomColorItem
import com.story.creator.picker.model.bean.ColorItem
import com.story.creator.picker.util.Util
import com.story.creator.picker.viewmodel.CustomVm
import jp.wasabeef.recyclerview.animators.FadeInAnimator
import kotlinx.android.synthetic.main.activity_custom.*

class CustomPaletteActivity : BaseActivity(), View.OnClickListener {

    var colorData: SparseArray<ColorItem> = SparseArray(8)

    //TODO setColorItem
    var colorItemX: ColorItem? = null

    var customVm: CustomVm? = null


    var curFragment: BaseFragment? = null
    var mAllFragments: ArrayList<BaseFragment> = ArrayList<BaseFragment>()
    var curPosition: Int = 0

    var colorAdapter: ColorAdapter? = null
    var textWatcher: TextWatcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_custom)

        color_recycler.setHasFixedSize(false)
        color_recycler.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        colorAdapter = ColorAdapter(this)

        val li: ColorAdapter.OnAddClickListener = object : ColorAdapter.OnAddClickListener {
            override fun addClick() {
                color_recycler.scrollToPosition(colorAdapter!!.itemCount - 1)
            }

            override fun onItemClick(colorItem: ColorItem?) {
                if (colorItem != null) {
                    //curFragment?.colorItem = colorItem
                    //curFragment?.invalite()
                    var len = customVm!!.customColorData.value!!.size

                }
            }
        }

        customVm = ViewModelProvider(this).get(CustomVm::class.java)
        customVm!!.loadCustomData(false)
        observeViewModel(customVm!!)
        colorAdapter!!.setColorData(customVm!!.customColorData.value)
        colorAdapter!!.setOnAddClickListener(li)
        color_recycler.adapter = colorAdapter

        color_recycler.itemAnimator = FadeInAnimator(OvershootInterpolator(2f))
        color_recycler.itemAnimator?.addDuration = 0
        color_recycler.itemAnimator?.removeDuration = 200
        color_recycler.itemAnimator?.moveDuration = 250
        color_recycler.itemAnimator?.changeDuration = 200

        setOnClickListener()

        color_value.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }
        })

        initFragment()
    }

    fun initFragment() {
        var rgbFragment = RGBFragment()
        var hsvFragment = HSVFragment()
        var cmykFragment = CMYKFragment()
        var ralFragment = RALFragment()

        mAllFragments.clear()
        mAllFragments.add(rgbFragment)
        mAllFragments.add(hsvFragment)
        mAllFragments.add(cmykFragment)
        mAllFragments.add(ralFragment)

        colorItemX = getRandomColorItem()
        //getRandomData()
        var transaction = supportFragmentManager.beginTransaction()

        curFragment = rgbFragment
        rgbFragment.rgbLiveData.value = colorItemX

        rgbFragment.rgbLiveData.observe(this, object : Observer<ColorItem> {
            override fun onChanged(t: ColorItem) {
                Log.d("xxl", "onChanged : " + t.toString())
                colorAdapter?.setSelectColorItem(t)
            }
        })
        transaction.replace(R.id.container, rgbFragment)
        transaction.commit()
        //init color RGB
        curFragment!!.invalite(customVm!!.customColorData.value?.get(0)!!)
    }

    fun setOnClickListener() {
        camera.setOnClickListener(this)
        rgb.setOnClickListener(this)
        hsv.setOnClickListener(this)
        cmyk.setOnClickListener(this)
        ral.setOnClickListener(this)
        color_value.setOnClickListener(this)
        copy.setOnClickListener(this)

        save.setOnClickListener(this)
        random.setOnClickListener(this)
        more.setOnClickListener(this)
    }

    private fun observeViewModel(viewModel: CustomVm) {
        viewModel.customColorData.observe(this, object : Observer<ArrayList<ColorItem>> {
            override fun onChanged(colorItems: ArrayList<ColorItem>) {

                Log.d("xxl", "onChanged: ")
                colorAdapter?.notifyDataSetChanged()
                colorItems.forEach {
                    if (it.isSelect) {
                        if (!it.getColor().toString().equals(copy.text)) {
                            copy.setText(it.getColor().toString())
                        }
                        curFragment?.invalite(it)
                    }
                }
            }
        })
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.camera -> {

                //container?.removeAllViews()
                //container?.addView()
                //Log.d("xxl","custom camera click")
            }

            R.id.rgb -> {
                if (curPosition != 0) {
                    curPosition = 0
                    curFragment = mAllFragments.get(curPosition)
                    var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    //fragment.rgbLiveData.value = colorItemX

                    curFragment!!.invalite(customVm!!.getSelect()!!)
                    transaction.replace(R.id.container, curFragment!!)
                    transaction.commit()
                }
            }

            R.id.hsv -> {
                if (curPosition != 1) {
                    curPosition = 1
                    curFragment = mAllFragments.get(curPosition)
                    var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    //fragment.rgbLiveData.value = colorItemX
                    transaction.replace(R.id.container, curFragment!!)
                    transaction.commit()
                }
            }

            R.id.cmyk -> {
//                var transaction : FragmentTransaction = supportFragmentManager.beginTransaction()
//                var fragment : Fragment = RGBFragment()
//                transaction.replace(R.id.container, fragment)
//                transaction.commit()

                if (curPosition != 2) {
                    curPosition = 2
                    curFragment = mAllFragments.get(curPosition)
                    var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    //fragment.rgbLiveData.value = colorItemX
                    transaction.replace(R.id.container, curFragment!!)
                    transaction.commit()
                }
            }

            R.id.ral -> {
//                var transaction :FragmentTransaction = supportFragmentManager.beginTransaction()
//                var fragment : Fragment = RGBFragment()
//                transaction.replace(R.id.container, fragment)
//                transaction.commit()

                if (curPosition != 3) {
                    curPosition = 3
                    curFragment = mAllFragments.get(curPosition)
                    var transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                    //fragment.rgbLiveData.value = colorItemX
                    transaction.replace(R.id.container, curFragment!!)
                    transaction.commit()
                }
            }

            R.id.copy -> {
                var text = color_value.text.toString();
                if (!TextUtils.isEmpty(text)) {
                    var value = Util.isHexInt(text)
                    if (value != -1) {
                        customVm!!.setSelectValue(value)
                    } else {
                        //TODO value 有问题
                    }
                }
//                var transaction :FragmentTransaction = supportFragmentManager.beginTransaction()
//                var fragment : Fragment = RGBFragment()
//                transaction.replace(R.id.container, fragment)
//                transaction.commit()
            }

            R.id.save -> {
                var text = name_palette.text.toString();
                if (text.isEmpty()) {
                    text = "Palette"
                }
                Util.save(false, text, colorAdapter?.getColorList())
            }

            R.id.random -> {
                customVm!!.loadCustomData(true)
            }
        }
    }

    private fun getView() {
        val detector = GestureDetector(this, object : OnGestureListener {
            override fun onShowPress(e: MotionEvent?) {
                TODO("Not yet implemented")
            }

            override fun onSingleTapUp(e: MotionEvent?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onDown(e: MotionEvent?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onFling(
                e1: MotionEvent?,
                e2: MotionEvent?,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onScroll(
                e1: MotionEvent?,
                e2: MotionEvent?,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onLongPress(e: MotionEvent?) {
                TODO("Not yet implemented")
            }
        })
        //detector.onTouchEvent()
    }
}
