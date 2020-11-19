package com.story.creator.picker.model.database

import android.content.Context
import android.util.Log
import com.story.creator.picker.model.bean.ColorValue
import com.story.creator.picker.model.bean.PaletteModules
import io.realm.*


open class RealmData {
    fun initPaletteRealm(): Realm {
//        var config = RealmConfiguration.Builder()
//            .name("color_picker_palette")
//            .schemaVersion(1)
//            .migration(MyMigration())
//            //.modules(PaletteModules())
//            .build()

        var config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded().build()
        var realm = Realm.getInstance(config)
        return realm
    }

    fun insertPalette(isFavorite: Boolean, name: String, list: RealmList<ColorValue>) {
        var paletteRealm = initPaletteRealm()
        var  id = findAll().size + 1

        paletteRealm.beginTransaction()
        var paletteModules =  PaletteModules()
        paletteModules.isFavorite = isFavorite
        paletteModules.name = name
        paletteModules.colorArray = list
        paletteModules.id = id
        paletteRealm.insert(paletteModules)
        paletteRealm.commitTransaction()
    }

    fun updatePalette(id: Int, isFavorite: Boolean, name: String) {
        var paletteRealm = initPaletteRealm()
        paletteRealm.beginTransaction()
        var paletteModules = paletteRealm.createObject(PaletteModules::class.java)
        paletteModules.isFavorite = isFavorite
        paletteModules.name = name
        paletteModules.id = id
        paletteRealm.insertOrUpdate(paletteModules)
        paletteRealm.commitTransaction()
    }

    fun findAll(): RealmResults<PaletteModules> {
        var paletteRealm = initPaletteRealm()
        paletteRealm.beginTransaction()
        var paletteAllData = paletteRealm.where(PaletteModules::class.java).findAll()
        paletteRealm.commitTransaction()
        return paletteAllData
    }

    fun delete(id: Int) {
        var paletteRealm = initPaletteRealm()
        var allData = findAll()
        paletteRealm.beginTransaction()

        for(paletteModule : PaletteModules in allData) {
            if(paletteModule.id == id) {
                paletteModule.deleteFromRealm()
                break
            }
        }
//        paletteRealm.executeTransaction {Realm.Transaction() {
//
//        }}
        //var paletteAllData = paletteRealm.delete()
        paletteRealm.commitTransaction()
    }

    class MyMigration : RealmMigration {
        override fun migrate(realm: DynamicRealm?, oldVersion: Long, newVersion: Long) {
            Log.d("xxl", "DynamicRealm : realm")
        }
    }
}