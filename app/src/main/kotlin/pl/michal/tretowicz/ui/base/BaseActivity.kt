package pl.michal.tretowicz.ui.base

import android.content.Context
import android.os.Bundle
import android.util.LongSparseArray
import androidx.appcompat.app.AppCompatActivity
import pl.michal.tretowicz.MyApplication
import pl.michal.tretowicz.injection.component.ActivityComponent
import pl.michal.tretowicz.injection.component.ConfigPersistentComponent
import pl.michal.tretowicz.injection.component.DaggerConfigPersistentComponent
import pl.michal.tretowicz.injection.module.ActivityModule
import pl.michal.tretowicz.util.extension.getOrPut
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong
/**
 * Created by Michał Trętowicz
 */
open class BaseActivity: AppCompatActivity() {

    companion object {
        @JvmStatic private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        @JvmStatic private val NEXT_ID = AtomicLong(0)
        @JvmStatic private val componentsMap = LongSparseArray<ConfigPersistentComponent>()
    }

    private var activityId: Long = 0
    lateinit var activityComponent: ActivityComponent

    @Suppress("UsePropertyAccessSyntax")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()

        if (componentsMap[activityId] != null)
            Timber.i("Reusing ConfigPersistentComponent id=%d", activityId)

        val configPersistentComponent = componentsMap.getOrPut(activityId) {
            Timber.i("Creating new ConfigPersistentComponent id=%d", activityId)

            val component = (applicationContext as MyApplication).applicationComponent

            DaggerConfigPersistentComponent.builder()
                    .applicationComponent(component)
                    .build()
        }

        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", activityId)
            componentsMap.remove(activityId)
        }
        super.onDestroy()
    }

    fun getContext(): Context {
        return this
    }
}
