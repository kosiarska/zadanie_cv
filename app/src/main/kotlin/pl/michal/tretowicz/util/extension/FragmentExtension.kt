package pl.michal.tretowicz.util.extension

import android.app.Activity
import android.os.Binder
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.BundleCompat
import org.jetbrains.anko.internals.AnkoInternals

/**
 * Pass arguments to a Fragment without the hassle of
 * creating a static newInstance() method for every Fragment.
 *
 * Declared outside any class to have full access in any
 * part of your package.
 *
 * Usage: instanceOf<MyFragment>("foo" to true, "bar" to 0)
 *
 * @return Returns an instance of Fragment as the specified generic type with the params applied as arguments
 */
//inline fun <reified T : androidx.fragment.app.Fragment> instanceOf(vararg params: Pair<String, Any>) = T::class.java.newInstance().apply {
//    arguments = bundleOf(params)
//}

inline fun <reified T : Activity> androidx.fragment.app.Fragment.startActivity(vararg params: Pair<String, Any>) {
    activity?.let { AnkoInternals.internalStartActivity(it, T::class.java, params) }
}


/**
 * Eases the Fragment.newInstance ceremony by marking the fragment's args with this delegate
 * Just write the property in newInstance and read it like any other property after the fragment has been created
 *
 * Inspired by Jake Wharton, he mentioned it during his IO/17 talk about Kotlin
 */
class FragmentArgumentDelegate<T : Any> : kotlin.properties.ReadWriteProperty<androidx.fragment.app.Fragment, T> {

    var value: T? = null

    override operator fun getValue(thisRef: androidx.fragment.app.Fragment, property: kotlin.reflect.KProperty<*>): T {
        if (value == null) {
            val args = thisRef.arguments
                    ?: throw IllegalStateException("Cannot read property ${property.name} if no arguments have been set")
            @Suppress("UNCHECKED_CAST")
            value = args.get(property.name) as T
        }
        return value ?: throw IllegalStateException("Property ${property.name} could not be read")
    }

    override operator fun setValue(thisRef: androidx.fragment.app.Fragment, property: kotlin.reflect.KProperty<*>, value: T) {
        if (thisRef.arguments == null) thisRef.arguments = android.os.Bundle()

        val args = thisRef.arguments
        val key = property.name

        args?.let {
            when (value) {
                is String -> it.putString(key, value)
                is Int -> it.putInt(key, value)
                is Short -> it.putShort(key, value)
                is Long -> it.putLong(key, value)
                is Byte -> it.putByte(key, value)
                is ByteArray -> it.putByteArray(key, value)
                is Char -> it.putChar(key, value)
                is CharArray -> it.putCharArray(key, value)
                is CharSequence -> it.putCharSequence(key, value)
                is Float -> it.putFloat(key, value)
                is Bundle -> it.putBundle(key, value)
                is Binder -> BundleCompat.putBinder(it, key, value)
                is android.os.Parcelable -> it.putParcelable(key, value)
                is java.io.Serializable -> it.putSerializable(key, value)
                else -> throw IllegalStateException("Type ${value.javaClass.canonicalName} of property ${property.name} is not supported")
            }
        }
    }
}


/**
 * Example usage of FragmentArgumentDelegate
 */
class WeatherCityFragment : androidx.fragment.app.Fragment() {

    private var cityId by FragmentArgumentDelegate<String>()

    companion object {
        fun newInstance(cityId: String) = WeatherCityFragment().apply {
            this.cityId = cityId
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Toast.makeText(activity, cityId, Toast.LENGTH_SHORT).show()
    }


}