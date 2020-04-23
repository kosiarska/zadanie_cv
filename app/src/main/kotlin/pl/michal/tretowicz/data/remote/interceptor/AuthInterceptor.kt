package pl.michal.tretowicz.data.remote.interceptor

import pl.michal.tretowicz.data.repository.session.SessionRepository
import okhttp3.Interceptor
import okhttp3.Response
import pl.michal.tretowicz.data.RxEventBus
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Michał Trętowicz
 */
@Singleton
class AuthInterceptor @Inject constructor(private val sessionStore: SessionRepository, private val rxEventBus: RxEventBus) : Interceptor {

    companion object {
        private const val AUTH_HEADER = "Authorization"
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val builder = request.newBuilder()
        return chain.proceed(builder.build())
    }
}
