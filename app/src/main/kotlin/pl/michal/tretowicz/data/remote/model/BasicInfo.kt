package pl.michal.tretowicz.data.remote.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class BasicInfo(

        @field:SerializedName("address")
        val address: String,

        @field:SerializedName("name")
        val name: String,

        @field:SerializedName("photo")
        val photo: String,

        @field:SerializedName("phone_number")
        val phoneNumber: String,

        @field:SerializedName("linkedin")
        val linkedin: String,

        @field:SerializedName("age")
        val age: Int,

        @field:SerializedName("email")
        val email: String
)