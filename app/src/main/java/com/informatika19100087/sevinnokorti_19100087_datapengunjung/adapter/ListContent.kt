package com.informatika19100087.sevinnokorti_19100087_datapengunjung.adapter

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.InsertDataActivity
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.MainActivity
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.R
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.UpdateDataActivity
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.model.DataItem
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.model.ResponseActionPengunjung
import com.informatika19100087.sevinnokorti_19100087_datapengunjung.network.koneksi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListContent(val ldata : List<DataItem?>?, val context: Context, val kondisi : String) :
        RecyclerView.Adapter<ListContent.ViewHolder>() {
        class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
            val namaPengunjung = view.findViewById<TextView>(R.id.tv_nama_pengunjung)
            val usiaPengunjung = view.findViewById<TextView>(R.id.tv_usia_pengunjung)
            val editPengunjung = view.findViewById<TextView>(R.id.tv_edit)
            val deletePengunjung = view.findViewById<TextView>(R.id.tv_delete)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_barang, parent, false)
        return ViewHolder(view)
    }
    override fun getItemCount(): Int {
        return ldata!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = ldata?.get(position)
        holder.namaPengunjung.text = model?.namaPengunjung
        holder.usiaPengunjung.text = model?.usiaPengunjung
        holder.editPengunjung.setOnClickListener {
            val i = Intent(context, UpdateDataActivity::class.java)
            i.putExtra("IDPENGUNJUNG", model?.id)
            i.putExtra("NAMAPENGUNJUNG", model?.namaPengunjung)
            i.putExtra("USIAPENGUNJUNG", model?.usiaPengunjung)
            context.startActivity(i)
        }
        holder.deletePengunjung.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete" + model?.namaPengunjung)
                .setMessage("Apakah Anda Ingin Mengahapus Data Ini?")
                .setPositiveButton("Ya", DialogInterface.OnClickListener { dialog, which ->

                    koneksi.service.deleteBarang(model?.id).enqueue(object : Callback<ResponseActionPengunjung>{
                        override fun onFailure(call: Call<ResponseActionPengunjung>, t: Throwable) {
                            Log.d("pesan1", t.localizedMessage)
                        }

                        override fun onResponse(
                            call: Call<ResponseActionPengunjung>,
                            response: Response<ResponseActionPengunjung>
                        ) {
                            if(response.isSuccessful){
                                Toast.makeText(context, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show()
                                notifyDataSetChanged()
                                notifyItemRemoved(position)
                                notifyItemChanged(position)
                                notifyItemRangeChanged(position, ldata!!.size)

                                if (kondisi == " InsertDataActivity"){
                                    val activity = (context as InsertDataActivity)
                                    activity.getData()
                                }else if (kondisi == " UpdateDataActivity"){
                                    val activity = (context as UpdateDataActivity)
                                    activity.getData()
                                }else{
                                    val activity = (context as MainActivity)
                                    activity.getData()
                                }

                                Log.d("bpesan", response.body().toString())

                            }
                        }
                    })
                })
                .setNegativeButton("No", DialogInterface.OnClickListener { dialog, which ->
                    dialog.cancel()
                })
                .show()
        }
    }


}