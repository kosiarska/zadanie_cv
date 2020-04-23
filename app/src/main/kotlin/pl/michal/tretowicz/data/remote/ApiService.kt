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

    @GET("kosiarska/240a482ee9c3ec377ccd66478aecc814/raw/384ab3ce7b4a3ad9335b526b18ae67209fce4c4f/cv1.json")
    fun getCvData() : Observable<CvResponse>
}
