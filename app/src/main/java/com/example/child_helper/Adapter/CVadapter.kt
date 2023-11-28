package com.example.child_helper.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.child_helper.Data.Syndata
import com.example.child_helper.R


class CVadapter(private val context: Context) : RecyclerView.Adapter<CVadapter.ViewHolder>() {

    var datas = mutableListOf<Syndata>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d ("", "Json : Adapter onCreate")
        val view = LayoutInflater.from(context).inflate(R.layout.view_item_layout,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = datas.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d ("", "Json : Adapter onBind")
        holder.bind(datas[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val cv_type: TextView = itemView.findViewById(R.id.cv_Type)
        private val cv_data: TextView = itemView.findViewById(R.id.cv_Data)

        fun bind(item: Syndata) {
            Log.d ("", "Json : " + item)
            cv_type.text = item.data_type
            cv_data.text = item.data
        }
    }


}
