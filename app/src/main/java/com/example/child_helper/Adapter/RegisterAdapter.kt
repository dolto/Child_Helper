package com.example.child_helper.Adapter

import Regi_ItemData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
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
        val textView: TextView = itemView.findViewById(R.id.Type)

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
        holder.textView.setText(currentItem)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    // 아이템 추가 메서드.
    fun addItem(item: Regi_ItemData, Type: String) {
        // 아이템 추가
        itemList.add("")

        // 추가된 아이템의 내용 변경 (예: Type에 따라 내용 설정)
        val position = itemList.size - 1
        itemList[position] = Type

        // RecyclerView에 추가 및 변경 사항 알림
        notifyItemInserted(position)
    }



    // Type 반환
    fun getTextAtPosition(position: Int): String {
        return itemList[position]
    }
}
