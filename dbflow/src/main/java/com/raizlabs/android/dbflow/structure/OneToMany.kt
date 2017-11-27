package com.raizlabs.android.dbflow.structure

import com.raizlabs.android.dbflow.query.ModelQueriable
import com.raizlabs.android.dbflow.query.list
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Description:
 */
fun <T : Any> oneToMany(query: () -> ModelQueriable<T>) = OneToMany(query)

/**
 * Description: Wraps a [OneToMany] annotation getter into a concise property setter.
 */
class OneToMany<T : Any>(private val query: () -> ModelQueriable<T>) : ReadWriteProperty<Any, List<T>?> {

    private var list: List<T>? = null

    override fun getValue(thisRef: Any, property: KProperty<*>): List<T>? {
        if (list?.isEmpty() != false) {
            list = query().list
        }
        return list
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: List<T>?) {
        list = value
    }
}