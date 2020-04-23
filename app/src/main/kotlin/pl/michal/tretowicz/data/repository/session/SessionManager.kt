package pl.michal.tretowicz.data.repository.session


import android.content.Context
import pl.michal.tretowicz.data.remote.ApiService
import pl.michal.tretowicz.injection.ApplicationContext
import pl.michal.tretowicz.util.Preferences

import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Michał Trętowicz
 */
@Singleton
class SessionManager @Inject constructor(
        @ApplicationContext private val context: Context,
        private val apiService: ApiService,
        private val session: SessionRepository) : Preferences() {

}

