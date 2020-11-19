package com.story.creator.picker.model.bean

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import com.story.creator.picker.constant.Constant.TAG

class ColorItem() : Parcelable{

    var colorR : Int = 255;
    var colorA : Int = 255;
    var colorG : Int = 255;
    var colorB : Int = 255;
    var isSelect : Boolean = false;

    constructor(parcel: Parcel) : this() {
        colorR = parcel.readInt()
        colorG = parcel.readInt()
        colorB = parcel.readInt()
        isSelect = parcel.readByte() != 0.toByte()
    }

    fun setColor(colorR : Int, colorB : Int, colorG : Int) {
        this.colorR = colorR;
        this.colorG = colorG;
        this.colorB = colorB;
    }

    fun setColorInt(color: Int) {
        colorR = color.shr(16)
        colorG = color.and(0x00ff00).shr(8)
        colorB = color.and(0x0000ff)

        Log.d(TAG, "colorR: $colorR colorG: $colorG colorB: $colorB")
    }

    fun setSelect2(select : Boolean) {
        this.isSelect = select
    }

    fun getColor() : Int {
        Log.d("xxl", "colorvalue : " + colorR.shl(16).or(colorG.shl(8)).or(colorB))
        return colorR.shl(16).or(colorG.shl(8)).or(colorB)
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(colorR)
        dest?.writeInt(colorG)
        dest?.writeInt(colorB)
        dest?.writeBoolean(isSelect)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ColorItem> {
        override fun createFromParcel(parcel: Parcel): ColorItem {
            return ColorItem(parcel)
        }

        override fun newArray(size: Int): Array<ColorItem?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return "colorR: $colorR colorG: $colorG colorB: $colorB"
    }
}


