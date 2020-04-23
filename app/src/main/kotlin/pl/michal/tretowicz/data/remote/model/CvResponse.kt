package pl.michal.tretowicz.data.remote.model

import javax.annotation.Generated
import com.google.gson.annotations.SerializedName

@Generated("com.robohorse.robopojogenerator")
data class CvResponse(

	@field:SerializedName("basic_info")
	val basicInfo: BasicInfo,

	@field:SerializedName("work_info")
	val workInfo: List<WorkInfoItem>
)