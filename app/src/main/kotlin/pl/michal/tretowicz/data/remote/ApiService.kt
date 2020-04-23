package pl.michal.tretowicz.data.remote

import io.reactivex.Observable
import okhttp3.ResponseBody
import pl.michal.tretowicz.BuildConfig
import pl.michal.tretowicz.data.remote.model.CvResponse
import retrofit2.http.GET


/**
 * Created by Michał Trętowicz
 */
interface ApiService {

    companion object {
        const val URL = BuildConfig.API
    }

    @GET("kosiarska/240a482ee9c3ec377ccd66478aecc814/raw/1b6e5c2b19596237df4032e855e41ffbac4d30d7/cv1.json")
    fun getCvData() : Observable<CvResponse>
}
