package com.erkuai.myview

import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

// 就不用了自己写  operator fun getValue 之类的了
class Delegate : ReadOnlyProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return ""
    }

}

class Delegate1 : ReadWriteProperty<Any, String> {
    override fun getValue(thisRef: Any, property: KProperty<*>): String {
        return ""
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String) {
    }

}
