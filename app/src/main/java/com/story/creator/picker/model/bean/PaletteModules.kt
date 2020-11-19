package com.story.creator.picker.model.bean

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmModule

open class PaletteModules : RealmObject() {
    var name : String? = null
    var isFavorite : Boolean = false
    @PrimaryKey
    var id : Int = 0

    //var intArray : IntArray? = null
    var colorArray : RealmList<ColorValue> = RealmList()

    var type : Int = 1
}