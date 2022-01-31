package com.informatika19100087.sevinnokorti_19100087_datapengunjung.model

import com.google.gson.annotations.SerializedName

data class ResponseAdmin(

	@field:SerializedName("pesan")
	val pesan: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem1?>? = null,

	@field:SerializedName("status")
	val status: Boolean? = null
)

data class DataItem1(

	@field:SerializedName("Username")
	val username: String? = null,

	@field:SerializedName("Password")
	val password: String? = null
)
