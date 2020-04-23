package pl.michal.tretowicz.data.remote.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class WorkInfoItem(

        @field:SerializedName("company_name")
        val companyName: String,

        @field:SerializedName("place")
        val place: String,

        @field:SerializedName("description")
        val description: String,

        @field:SerializedName("time")
        val time: String,

        @field:SerializedName("company_logo")
        val companyLogo: String,

        @field:SerializedName("url")
        val url: String? = null,

        @field:SerializedName("links")
        val links: List<String>
)