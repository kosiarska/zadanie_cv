package pl.michal.tretowicz.data.repository.session

import pl.michal.tretowicz.util.Preferences
import javax.inject.Singleton

/**
 * Created by Michał Trętowicz
 */
@Singleton
class SessionRepository : Preferences() {
    var accessToken by stringPref(defaultValue = "")
}

