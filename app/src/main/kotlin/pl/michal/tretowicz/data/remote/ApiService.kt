package pl.michal.tretowicz.data.remote

import pl.michal.tretowicz.BuildConfig


/**
 * Created by Michał Trętowicz
 */
interface ApiService {

    companion object {
        const val URL = BuildConfig.API
    }
}
