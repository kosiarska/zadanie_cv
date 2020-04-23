package pl.michal.tretowicz.util.extension

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso

/**
 * Created by Michał Trętowicz
 */
fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    return cm.activeNetworkInfo != null
}

fun Context.toggleAndroidComponent(componentClass: Class<*>, enable: Boolean) {
    val componentName = ComponentName(this, componentClass)

    val newState = if (enable)
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    else
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED

    packageManager.setComponentEnabledSetting(componentName, newState, PackageManager.DONT_KILL_APP)
}

public val Context.picasso: Picasso
    get() = Picasso.with(this)

fun Context.color(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

