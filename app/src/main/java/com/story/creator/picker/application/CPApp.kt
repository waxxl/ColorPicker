package com.story.creator.picker.application

import android.app.Application
import android.content.Context
import com.story.creator.picker.constants.Cons
import io.realm.Realm

class CPApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        Cons.initAll()
    }
}


