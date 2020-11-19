package com.story.creator.picker.constants

import android.util.Log
import com.story.creator.picker.constant.Constant.TAG
import com.story.creator.picker.constants.Cons.COLOR_DATA1
import com.story.creator.picker.constants.Cons.allData
import com.story.creator.picker.model.bean.ColorItem
import kotlin.math.absoluteValue
import kotlin.random.Random

fun Int.toColorItem(): ColorItem {
    var colorItem = ColorItem();
    colorItem.colorR = this.shr(4)
    colorItem.colorG = this.and(0x00ff00).shr(2)
    colorItem.colorB = this.and(0x0000ff)
    return colorItem
}

fun getRandomColorItem(): ColorItem {
    var colorItem = ColorItem();

    Log.d(TAG, "getRandomColorItem int : " + Integer.toHexString(allData.get(((Math.abs(Math.random() * allData.size))).toInt())[0]))

    var x: Int = (System.currentTimeMillis() % 5).toInt();
    var dataIndex = Math.abs(Math.random() * allData.size);

    colorItem.setColorInt(allData.get((dataIndex).toInt())[x])

    return colorItem
}

fun getRandomData(): IntArray {
    return allData.get((System.currentTimeMillis() % allData.size).toInt())
}

object Cons {
    fun initAll() {
        allData.add(COLOR_DATA1)
        allData.add(COLOR_DATA2)
        allData.add(COLOR_DATA3)
        allData.add(COLOR_DATA4)
        allData.add(COLOR_DATA5)
        allData.add(COLOR_DATA6)
        allData.add(COLOR_DATA7)
    }

    val COLOR_DATA1 = intArrayOf(0x1532B1, 0xD31984, 0xB33D62, 0x1c0587, 0x339aba)
    val COLOR_DATA2 = intArrayOf(0x17a287, 0x04D2B1, 0x0ACFF5, 0xB25C2D, 0x8F1192)
    val COLOR_DATA3 = intArrayOf(0x17567B, 0xD1A8C4, 0xD53A5A, 0x0b3bb7, 0x99a94c)
    val COLOR_DATA4 = intArrayOf(0xDA4EE8, 0xA05C4A, 0x531FE0, 0x7935ba, 0x5b55c7)
    val COLOR_DATA5 = intArrayOf(0x01BF7B, 0xAD89B5, 0x373F21, 0x1fbf0f, 0x8a1361)
    val COLOR_DATA6 = intArrayOf(0x1ae9cf, 0x71D691, 0x1a6892, 0x34ec59, 0xed4348)
    val COLOR_DATA7 = intArrayOf(0xd9a75b, 0x8f6202, 0xa56a14, 0x241fe6, 0xb680db)
    val allData = ArrayList<IntArray>();
}