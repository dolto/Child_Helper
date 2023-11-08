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

    interface OnItemClickListener {
        fun onDeleteButtonClick(position: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    fun removeItem(position: Int) {
        Log.d("삭제 시도","삭제 시도")
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

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val button: Button = itemView.findViewById(R.id.Remove_address)

        fun bind(item: Regi_ItemData, removeItem: (position: Int) -> Unit) {
            button.setOnClickListener { removeItem(adapterPosition) }
            // 기타 필요한 바인딩...
        }
    }

    // 아이템 제거를 위한 메소드들



}
