package com.informatika19100087.sevinnokorti_19100087_datapengunjung.network

import com.informatika19100087.sevinnokorti_19100087_datapengunjung.model.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {
    @GET("read.php")
    fun getPengunjun(): Call<ResponsePengunjung>

    @FormUrlEncoded
    @POST("create.php")
    fun insertBarang(
        @Field("Nama_pengunjung") namaPengunjung: String?,
        @Field("Usia_Pengunjung") usiaPengunjung: String?
    ): Call<ResponseActionPengunjung>

    @FormUrlEncoded
    @POST("update.php")
    fun updateBarang(
        @Field("id") id: String?,
        @Field("Nama_pengunjung") namaPengunjung: String?,
        @Field("Usia_pengunjung") usiaPengunjung: String?
    ): Call<ResponseActionPengunjung>

    @FormUrlEncoded
    @POST("delete.php")
    fun deleteBarang(
        @Field("id") id: String?
    ): Call<ResponseActionPengunjung>

    @FormUrlEncoded
    @POST("login.php")
    fun logIn(
        @Field("Username") Username : String?,
        @Field("Password") Password : String?
    ):Call<ResponseAdmin>

    @FormUrlEncoded
    @POST("register.php")
    fun register(
        @Field("Username") Username : String?,
        @Field("Password") Password : String?
    ):Call<ResponseAdmin>

    fun insertPengunjung(namaPengunjung: String, usiaPengunjung: String): Any
    fun getPengunjung(): Any
}