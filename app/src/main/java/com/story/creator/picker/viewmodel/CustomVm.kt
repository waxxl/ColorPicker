package com.story.creator.picker.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.story.creator.picker.constants.Cons
import com.story.creator.picker.constants.getRandomData
import com.story.creator.picker.constants.toColorItem
import com.story.creator.picker.model.bean.ColorItem
import com.story.creator.picker.model.bean.ColorValue
import com.story.creator.picker.model.bean.PaletteModules
import com.story.creator.picker.model.database.RealmData
import io.realm.RealmList
import io.realm.RealmResults
import kotlin.collections.ArrayList

class CustomVm : ViewModel() {
    var colorItem: ColorItem? = null
    val liveData = MutableLiveData<RealmResults<PaletteModules>>()

    //Custom ColorData
    var customColorData = MutableLiveData<ArrayList<ColorItem>>()

    fun loadData() {
        //添加静态数据
        colorItem = Cons.COLOR_DATA1[0].toColorItem()
        //liveData.value = colorItem
    }

    fun loadCustomData(needClear: Boolean) {
        customColorData.value = ArrayList<ColorItem>()
        var array = getRandomData()
        if(needClear) {
            customColorData.value?.clear()
        }
        array.forEach {
            var colorItem = ColorItem()
            colorItem.setColorInt(it)

            customColorData.value?.add(colorItem)
        }
        customColorData.value!!.get(0)!!.isSelect = true
    }

    fun setSelectValue(value: Int) {
        customColorData.value?.forEach {
            if(it.isSelect) {
                it.setColorInt(value)
            }
        }
    }

    fun getSelect() : ColorItem? {
        customColorData.value?.forEach {
            if(it.isSelect) {
                return it;
            }
        }
        return null
    }


    //for paletteActivity
    fun loadDataFromDatabase() {
        //从数据库添加数据
        var realmData = RealmData()

        liveData.value = realmData.findAll()
    }

    fun save(
        isFavorite: Boolean,
        name: String?,
        list: RealmList<ColorValue>
    ) {
        val realmData = RealmData()
        realmData.insertPalette(isFavorite, name!!, list)
    }

    fun queryAll(): ArrayList<PaletteModules>? {
        val realmData = RealmData()
        val results = realmData.findAll()
        val arrList = ArrayList<PaletteModules>()
        for (i in results.indices) {
            arrList.add(results[i])
        }
        return arrList
    }
}