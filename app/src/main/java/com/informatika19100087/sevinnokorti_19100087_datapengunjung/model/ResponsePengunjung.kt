package com.informatika19100087.sevinnokorti_19100087_datapengunjung.model

import com.google.gson.annotations.SerializedName

data class ResponsePengunjung(

    @field:SerializedName( "pesan")
    val pesan: String? = null,

    @field:SerializedName("data")
    val data: List<DataItem?>? = null,

    @field:SerializedName("status")
    val status: Boolean? = null
)

data class DataItem(

    @field:SerializedName("Nama_pengunjung")
    val namaPengunjung: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("Jumlah_pengunjung")
    val usiaPengunjung: String? =null
)