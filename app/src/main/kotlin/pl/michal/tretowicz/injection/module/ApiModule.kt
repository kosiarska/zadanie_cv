package pl.michal.tretowicz.injection.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
 import pl.michal.tretowicz.data.remote.ApiService
import pl.michal.tretowicz.data.remote.interceptor.AuthInterceptor
import pl.michal.tretowicz.data.repository.session.SessionRepository
import pl.michal.tretowicz.util.retrofit.RxThreadingWithErrorHandlingCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pl.michal.tretowicz.BuildConfig
import pl.michal.tretowicz.data.RxEventBus
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                .excludeFieldsWithoutExposeAnnotation()
                .create()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(sessionRepo: SessionRepository, rxEventBus: RxEventBus): OkHttpClient {
        val logger = HttpLoggingInterceptor()
        logger.level = if (BuildConfig.DEBUG)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE


        return OkHttpClient.Builder()
                .addInterceptor(AuthInterceptor(sessionRepo, rxEventBus))
                .addInterceptor(logger)
                .readTimeout(25000, TimeUnit.MILLISECONDS)
                .writeTimeout(25000, TimeUnit.MILLISECONDS)
                .connectTimeout(25000, TimeUnit.MILLISECONDS)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiService(okHttpClient: OkHttpClient, gson: Gson): ApiService {
        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiService.URL)
                .validateEagerly(BuildConfig.DEBUG)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxThreadingWithErrorHandlingCallAdapterFactory.create())
                .build()
                .create(ApiService::class.java)
    }


}
