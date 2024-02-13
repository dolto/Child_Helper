//package com.example.child_helper.Adapter
//
//import android.content.Intent
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.RecyclerView
//import com.example.child_helper.Data.NameData
//import com.example.child_helper.FPActivity
//import com.example.child_helper.R
//
//
//
//class UserList_Adapter (private val items: List<NameData>) : RecyclerView.Adapter<UserList_VewHolder>() {
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserList_VewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.update_user_item, parent, false)
//        return UserList_VewHolder(view)
//    }
//
//    override fun onBindViewHolder(holder: UserList_VewHolder, position: Int) {
//        // 아이템 데이터를 ViewHolder에 바인딩합니다.
//        val item = items[position]
//        holder.bind(item)
//    }
//
//    override fun getItemCount(): Int {
//        return items.size
//    }
//}