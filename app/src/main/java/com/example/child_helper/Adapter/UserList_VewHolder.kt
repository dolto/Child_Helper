package com.example.child_helper.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.child_helper.Data.NameData
import com.example.child_helper.Data.OnButtonClickListener
import com.example.child_helper.R
import com.example.child_helper.Update_user_page
import com.example.child_helper.client.Client


class UserList_VewHolder(itemView: View, private val listener: OnButtonClickListener) : RecyclerView.ViewHolder(itemView) {
    private val button: Button = itemView.findViewById(R.id.Btn_User_Profile)

    init {
        button.setOnClickListener { view ->
            val buttonText = (view as Button).text.toString()
            listener.onButtonClick(buttonText, adapterPosition)
        }
    }

    fun bind(item: NameData) {
        button.text = item.name // item.name은 NameData 클래스의 속성이라고 가정합니다.
    }
}

class UserList_Adapter(private val items: List<NameData>, private val listener: OnButtonClickListener) : RecyclerView.Adapter<UserList_VewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserList_VewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.update_user_item, parent, false)
        return UserList_VewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: UserList_VewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

//class UserList_VewHolder(itemList : View): RecyclerView.ViewHolder(itemList) {
//    private val button: Button = itemView.findViewById(R.id.Btn_User_Profile)
//
//    init {
//        itemView.setOnClickListener { view ->
//            val button = view as Button
//            val buttonText = button.text.toString()
//            buttonClicked(buttonText, adapterPosition)
//        }
//    }
//
//    private fun buttonClicked(buttonText: String, position: Int) : String {
//        Log.d("ItemViewHolder", "Button with text \"$buttonText\" clicked at position: $position")
//        return ""
//    }
//    fun bind(item: NameData) {
//        // 아이템 데이터를 View에 바인딩합니다.
//        button.text = item.name
//    }
//}