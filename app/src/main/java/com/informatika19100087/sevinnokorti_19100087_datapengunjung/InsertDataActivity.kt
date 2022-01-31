package com.informatika19100087.sevinnokorti_19100087_datapengunjung

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.adapter.ListContent
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.model.ResponseActionPengunjung
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.model.ResponsePengunjung
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.network.koneksi
import kotlinx.android.synthetic.main.activity_insert_data.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InsertDataActivity : AppCompatActivity() {
    @SuppressLint("RestrictedApi")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_data)
        toolbar.title = "INSERT DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        btn_submit.setOnClickListener {
            val etNamaBarang = et_nama_Pengunjung.text
            val etJmlBarang = et_Usia_Pengunjung.text
            if (etJmlBarang.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Jumlah Barang Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else if (etNamaBarang.isEmpty()) {
                Toast.makeText(
                    this@InsertDataActivity,
                    "Nama Pengunjung Tidak Boleh Kosong",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                actionData(etNamaBarang.toString(), etJmlBarang.toString())
            }
        }

        btn_clean.setOnClickListener {
            formClear()
        }
        getData()
    }

    fun formClear() {
        et_nama_Pengunjung.text.clear()
        et_Usia_Pengunjung.text.clear()

    }

    fun actionData(namaPengunjung: String, UsiaPengunjung: String) {
        koneksi.service.insertPengunjung(namaPengunjung, UsiaPengunjung).enqueue(object : Callback<ResponseActionPengunjung> {
                override fun onFailure(call: Call<ResponseActionPengunjung>, t: Throwable) {
                    Log.d("pesan1", t.localizedMessage)
                }

                override fun onResponse(
                    call: Call<ResponseActionPengunjung>,
                    response: Response<ResponseActionPengunjung>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@InsertDataActivity,
                            "data berhasil disimpan",
                            Toast.LENGTH_LONG
                        ).show()
                        formClear()
                        getData()
                    }
                }
            })
    }

    fun getData() {
        koneksi.service.getPengunjung().enqueue(object : Callback<ResponsePengunjung> {
            override fun onFailure(call: Call<ResponsePengunjung>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponsePengunjung>,
                response: Response<ResponsePengunjung>
            ) {
                if (response.isSuccessful) {
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@InsertDataActivity, "InsertDataActivity")

                    val rv_data_pengunjung = null
                    rv_data_pengunjung.apply {
                        var adapter = rvAdapter
                        var layoutManager = LinearLayoutManager(this@InsertDataActivity)
                    }
                }
            }
        })
    }
}

fun Any.enqueue(callback: Callback<ResponsePengunjung>) {


}

