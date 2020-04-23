package pl.michal.tretowicz


import io.fabric.sdk.android.Fabric
import pl.michal.tretowicz.injection.component.ApplicationComponent
import pl.michal.tretowicz.injection.component.DaggerApplicationComponent
import pl.michal.tretowicz.injection.module.ApplicationModule
import pl.michal.tretowicz.util.Preferences


open class MyApplication : android.app.Application() {

    lateinit var applicationComponent: ApplicationComponent
        private set

    override fun onCreate() {
        super.onCreate()
        initDaggerComponent()
        Preferences.init(this)
        Fabric.with(this)
    }


    @androidx.annotation.VisibleForTesting
    private fun initDaggerComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}
