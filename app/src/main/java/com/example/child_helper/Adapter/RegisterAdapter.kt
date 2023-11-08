package com.example.child_helper.Adapter

import Regi_ItemData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.child_helper.R

class RegisterAdapter(private val itemList: MutableList<String>) : RecyclerView.Adapter<RegisterAdapter.RegiViewHolder>(){

    // 아이템 제거를 위한 메소드들
    interface OnItemClickListener {
        fun onDeleteButtonClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    // 아이템 제거 메소드
    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

    inner class RegiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val regi_edt_address: EditText = itemView.findViewById(R.id.Edt_regi_address)
        val regi_btn_address: Button = itemView.findViewById(R.id.Remove_address)

        init {
            regi_btn_address.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClickListener?.onDeleteButtonClick(position)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegiViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.register_item_layout, parent, false)
        return RegiViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RegiViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.regi_edt_address.setText(currentItem)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 아이템 추가 메서드.
    fun addItem(item: Regi_ItemData) {
        itemList.add("")
        notifyItemInserted(itemList.size - 1)
    }
}
