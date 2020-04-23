package pl.michal.tretowicz.injection.module

import android.app.Activity
import android.content.Context
import dagger.Module
import dagger.Provides
import pl.michal.tretowicz.data.ToastManager
import pl.michal.tretowicz.injection.ActivityContext
import pl.michal.tretowicz.injection.PerActivity

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    @PerActivity
    internal fun provideActivity(): Activity {
        return activity
    }

    @Provides
    @PerActivity
    @ActivityContext
    internal fun providesContext(): Context {
        return activity
    }

    @Provides
    @ActivityContext
    internal fun providesToastManager(): ToastManager {
        return ToastManager(activity)
    }
}
