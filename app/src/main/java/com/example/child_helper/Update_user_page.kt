package com.example.child_helper

import SharedPrefManager
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.child_helper.Adapter.RegisterAdapter
import com.example.child_helper.Adapter.UserList_Adapter
import com.example.child_helper.Adapter.UserList_VewHolder
import com.example.child_helper.Data.NameData
import com.example.child_helper.Data.OnButtonClickListener
import com.example.child_helper.Data.Profile
import com.example.child_helper.Data.convertJsonToProfileList
import com.example.child_helper.Data.jsonToMutableList
import com.example.child_helper.Data.profile_id
import com.example.child_helper.Data.test_json
import com.example.child_helper.client.Client
import kotlin.concurrent.thread

class Update_user_page :  AppCompatActivity(), OnButtonClickListener {
    private lateinit var adapter: UserList_Adapter
    private lateinit var recyclerView: RecyclerView
    // 버튼 클릭 감지.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user_page)

        recyclerView = findViewById(R.id.UserList_recyclerview)
        // 초기 데이터 설정
        val itemList = jsonToMutableList(test_json.test1)
        // Adapter 설정
        adapter = UserList_Adapter(itemList, this)
        recyclerView.adapter = adapter

        // LayoutManager 설정
        recyclerView.layoutManager = LinearLayoutManager(this)


        var button :Button = findViewById(R.id.Btn_new_profile)

        button.setOnClickListener {
            profile_id.id = -1
            // 여기에서 실제로 원하는 동작을 수행하면 됩니다.
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onButtonClick(buttonText: String, position: Int) {
        // 초기 데이터 설정
        val itemList = jsonToMutableList(test_json.test1)
        Log.d("", "서버 응답 151 : " + "$buttonText")
        val id = itemList.firstOrNull { it.name == "$buttonText" }?.let { it.id }
        Log.d("", "서버 응답 152 : " + "$id")
        profile_id.id = id!!.toInt()
        // 여기에서 실제로 원하는 동작을 수행하면 됩니다.

        val intent = Intent(applicationContext, RegisterActivity::class.java)
        startActivity(intent)
    }

}