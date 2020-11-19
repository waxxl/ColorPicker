package com.story.creator.picker.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import com.story.creator.picker.R
import com.story.creator.picker.model.bean.ColorItem

class ColorView : View {
    private var data : ColorItem = ColorItem()
    var bitmap: Bitmap? = null

    constructor(context : Context?) : super(context) {
        initBitmap(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        initBitmap(context)
    }

    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        initBitmap(context)
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        initBitmap(context)
    }

    private fun initBitmap(context: Context?) {
        bitmap = BitmapFactory.decodeResource(context!!.resources, R.drawable.rectangle)
    }

    public fun setArbg(a: Int, r: Int, g: Int, b: Int) {
        data.colorR = r
        data.colorG = g
        data.colorB = b
    }

    fun setData(colorItem: ColorItem) {
        this.data = colorItem
    }

    fun setSelect(select: Boolean) {
        data.isSelect = select
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (data != null) {
            canvas?.drawARGB(data.colorA, data.colorR, data.colorG, data.colorB)
        }

        if (bitmap != null) {
            if (data.isSelect && !bitmap!!.isRecycled) {
                Log.d("xxl", "height: " + height + " width: " + width + " bitmap.height: " + bitmap!!.height + " bitmap.width: " + bitmap!!.width)
                canvas?.drawBitmap(
                    bitmap!!,
                    ((width - bitmap!!.width) / 2).toFloat(),
                    (height - bitmap!!.height).toFloat(),
                    Paint()
                )
            }
        }
    }
}