package com.example.child_helper

import Regi_ItemData
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.child_helper.Adapter.RegisterAdapter
import com.example.child_helper.Data.AdressData
import com.example.child_helper.Data.FingerData
import com.example.child_helper.Data.MemoData
import com.example.child_helper.Data.NameData
import com.example.child_helper.Data.PhoneNumberData
import com.example.child_helper.Data.Profile
import com.example.child_helper.Data.convertProflieListToJson
import com.example.child_helper.client.Client
import kotlinx.serialization.json.Json

class RegisterActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // 리사이클러 뷰
        val recyclerView = findViewById<RecyclerView>(R.id.Regiser_recyclerview)
        // 리니어 레이아웃 매니저.
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        // 주소, 연락처, 메세지 저장소
        // mutableListOf: 주소 초기 데이터 설정이 없다는 뜻.
        val data = mutableListOf<String>()
        val data_address = mutableListOf<AdressData>()
        val data_phone = mutableListOf<PhoneNumberData>()
        val data_message = mutableListOf<MemoData>()




        // Adapter 기본 설정.
        val adapter_address = RegisterAdapter(data)
        recyclerView.adapter = adapter_address

        // 주소, 연락처, 메세지 추가 버튼.
        val address_Button = findViewById<Button>(R.id.Btn_address)
        val phone_Button = findViewById<Button>(R.id.Btn_Call)
        val message_Button = findViewById<Button>(R.id.Btn_message)


        // 등록 버튼
        val regi_Complete = findViewById<Button>(R.id.Regi_Complete)

        // 주소, 번호  리니어 레이아웃 추가를 위한 함수.
        address_Button.setOnClickListener {
            val newItem = Regi_ItemData("", "주소")
            adapter_address.addItem(newItem, "주소")
        }

        phone_Button.setOnClickListener {
            val newItem = Regi_ItemData("", "연락처")
            adapter_address.addItem(newItem, "연락처")
        }

        message_Button.setOnClickListener {
            val newItem = Regi_ItemData("", "메세지")
            adapter_address.addItem(newItem, "메세지 ")
        }

        // 주소 아이템 수정 버튼 클릭 이벤트에서 EditText의 값을 가져와 업데이트
        regi_Complete.setOnClickListener {
            val data_name = NameData(-1, "Test", "Test")
            val data_finger = FingerData(-1, "test", "test", "test","test")

            for (i in 0 until adapter_address.itemCount) {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(i) as RegisterAdapter.RegiViewHolder
                val modifiedText = viewHolder.regi_edt_address.text.toString()
                val text = adapter_address.getTextAtPosition(i)



                if (text == "주소"){
                    data_address.add(AdressData(modifiedText))
                }
                else if (text == "연락처"){
                    data_phone.add(PhoneNumberData(modifiedText))
                }
                else if (text == "메세지"){
                    data_message.add(MemoData(modifiedText))
                }
                else {
                }
            }

            val data_proflie = Profile(1, data_name, data_finger, data_address, data_phone, data_message)
            val d = convertProflieListToJson(data_proflie)
            Log.d("","Json"+ "$d")
            Client(this, "")
        }

        // RecyclerView에서 주소 아이템 제거하기
        adapter_address.setOnItemClickListener(object : RegisterAdapter.OnItemClickListener {
            override fun onDeleteButtonClick(position: Int) {
                adapter_address.removeItem(position)
            }
        })
    }


}