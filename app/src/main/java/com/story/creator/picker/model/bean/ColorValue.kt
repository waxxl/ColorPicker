package com.story.creator.picker.model.bean

import io.realm.ProxyState
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.annotations.Index
import io.realm.internal.RealmObjectProxy

open class ColorValue:  RealmObject(), RealmObjectProxy {
    var value: Int = 0
    override fun `realmGet$proxyState`(): ProxyState<*> {
        return ProxyState(this)
    }
}