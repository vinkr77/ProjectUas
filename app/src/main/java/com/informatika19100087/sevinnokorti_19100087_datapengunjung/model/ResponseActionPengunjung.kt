package com.informatika19100087.sevinnokorti_19100087_datapengunjung.model

import com.google.gson.annotations.SerializedName

data class ResponseActionPengunjung(

    @field:SerializedName("pesan")
    val pesan: Any? = null,

    @field:SerializedName("data")
    val data: List<Boolean?>? = null,

    @field:SerializedName("status")
    val status: String? = null,
    )
