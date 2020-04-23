package pl.michal.tretowicz.data

import android.content.Context
import pl.michal.tretowicz.data.remote.ApiService

import pl.michal.tretowicz.data.repository.session.SessionManager
import pl.michal.tretowicz.injection.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Michał Trętowicz
 */
@Singleton
class DataManager @Inject constructor(@ApplicationContext private val context: Context, private val apiService: ApiService, private val sessionManager: SessionManager) {


    fun getCvData() = apiService.getCvData()
}
