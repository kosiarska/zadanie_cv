package pl.michal.tretowicz.injection.module

import android.app.Application
import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import dagger.Module
import dagger.Provides
import pl.michal.tretowicz.data.DataManager
import pl.michal.tretowicz.data.remote.ApiService
import pl.michal.tretowicz.injection.ApplicationContext
import javax.inject.Singleton

@Module
class ApplicationTestModule(val application: Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(): Context {
        return application
    }

    /***** MOCKS *****/

    @Provides
    fun apiService(): ApiService {
        return mock()
    }

    @Provides
    fun dataManager(): DataManager {
        return mock()
    }
}
