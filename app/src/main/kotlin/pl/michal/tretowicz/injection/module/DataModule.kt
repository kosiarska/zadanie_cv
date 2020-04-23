package pl.michal.tretowicz.injection.module

import android.app.Application
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import pl.michal.tretowicz.data.DataManager
import pl.michal.tretowicz.data.FieldsValidator
import pl.michal.tretowicz.data.remote.ApiService
import pl.michal.tretowicz.data.repository.session.SessionManager
import pl.michal.tretowicz.data.repository.session.SessionRepository
import pl.michal.tretowicz.injection.ApplicationContext
import javax.inject.Singleton

@Module(includes = [ApiModule::class])
class DataModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences {
        return app.getSharedPreferences("app", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    internal fun provideLoginManager(@ApplicationContext context: Context,apiService: ApiService, sessionRepo: SessionRepository): SessionManager {
        return SessionManager(context, apiService, sessionRepo)
    }

    @Provides
    @Singleton
    internal fun provideDataManager(@ApplicationContext context: Context, apiService: ApiService, sessionManager: SessionManager): DataManager {
        return DataManager(context, apiService, sessionManager)
    }

    @Provides
    @Singleton
    internal fun provideSessionRepository(): SessionRepository {
        return SessionRepository()
    }

    @Provides
    @Singleton
    internal fun provideFieldsValidator(): FieldsValidator {
        return FieldsValidator()
    }
}
