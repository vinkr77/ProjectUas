package com.informatika19100087.sevinnokorti_19100087_datapengunjung

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
import kotlinx.android.synthetic.main.activity_update_data.*
import kotlinx.android.synthetic.main.activity_update_data.et_Usia_Pengunjung
import kotlinx.android.synthetic.main.activity_update_data.et_nama_Pengunjung
import kotlinx.android.synthetic.main.activity_update_data.rv_data_barang
import kotlinx.android.synthetic.main.activity_update_data.toolbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.annotation.SuppressLint as SuppressLint1

class UpdateDataActivity : AppCompatActivity() {
    @Suppress("RestrictedApi")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_data)
        toolbar.title = "UPDATE DATA"
        toolbar.setTitleTextColor(Color.WHITE)

        val i = intent
        val idBarang = i.getStringExtra("IDPENGUNJUNG")
        val namaBarang = i.getStringExtra("NAMAPENGUNJUNG")
        val jumlahBarang = i.getStringExtra("JUMLAHPENGUNJUNG")

        et_nama_Pengunjung.setText(namaBarang)
        et_Usia_Pengunjung.setText(jumlahBarang)
        btn_submit.setOnClickListener {
            val etNamaBarang = et_nama_Pengunjung.text
            val etJmlBarang = et_Usia_Pengunjung.text
            if (etJmlBarang.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Jumlah Barang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else if (etNamaBarang.isEmpty()){
                Toast.makeText(this@UpdateDataActivity, "Nama Barang Tidak Boleh Kosong", Toast.LENGTH_LONG).show()
            }else{
                actionData(idBarang.toString(), etNamaBarang.toString(), etJmlBarang.toString())
            }
        }
        btn_back.setOnClickListener {
            finish()
        }
        getData()
    }
    fun actionData(id : String, namaBarang : String, jmlBarang : String){
        koneksi.service.updateBarang(id, namaBarang, jmlBarang).enqueue(object : Callback<ResponseActionPengunjung>{
            override fun onFailure(call: Call<ResponseActionPengunjung>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponseActionPengunjung>,
                response: Response<ResponseActionPengunjung>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@UpdateDataActivity, "data berhasil diupdate", Toast.LENGTH_LONG).show()
                    getData()
                }
            }
        })
    }
    fun getData(){
        koneksi.service.getPengunjun().enqueue(object : Callback<ResponsePengunjung>{
            override fun onFailure(call: Call<ResponsePengunjung>, t: Throwable) {
                Log.d("pesan1", t.localizedMessage)
            }

            override fun onResponse(
                call: Call<ResponsePengunjung>,
                response: Response<ResponsePengunjung>
            ) {
                if (response.isSuccessful){
                    val dataBody = response.body()
                    val datacontent = dataBody!!.data

                    val rvAdapter = ListContent(datacontent, this@UpdateDataActivity, "UpdateDataActivity")


                    rv_data_barang.apply {
                        adapter = rvAdapter
                        layoutManager = LinearLayoutManager(this@UpdateDataActivity)
                    }

                    }
                }

        })
    }
}