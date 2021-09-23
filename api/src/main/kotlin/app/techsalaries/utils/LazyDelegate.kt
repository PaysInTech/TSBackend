package app.techsalaries.utils

import dagger.Lazy
import kotlin.reflect.KProperty

operator fun <T> Lazy<T>.getValue(thisRef: Any?, property: KProperty<*>): T = get()
