package com.example.child_helper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.child_helper.Adapter.CVadapter
import com.example.child_helper.Data.AdressData
import com.example.child_helper.Data.MemoData
import com.example.child_helper.Data.PhoneNumberData
import com.example.child_helper.Data.Profile
import com.example.child_helper.Data.Syndata
import com.example.child_helper.Data.convertJsonToProfileList
import com.example.child_helper.Data.test_json
import com.example.child_helper.databinding.ActivityCvactivityBinding

class CVActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCvactivityBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CVadapter



    // 데이터를 뛰우기 위한 메인 함수
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cvactivity)
        setTitle("지문 인식");

        val view_name = findViewById<TextView>(R.id.View_name)

        // Profile
        val Profile_dataList: Profile =  convertJsonToProfileList(test_json.test1)

        Log.d("","Json : 데이터 변경완료 ")

        // data_list들 정리
        val cvadressList: List<AdressData> = Profile_dataList.adressDB
        val cvphoneList: List<PhoneNumberData> = Profile_dataList.phoneNumberDB
        val cvmemoList: List<MemoData> = Profile_dataList.memoDB

        Log.d("","Json : 데이터 분할 정리 완료")

        view_name.text = Profile_dataList.nameDB.name

        Log.d("","Json : 이름 정리 완료.")

        // dataList를 사용할 수 있도록 Syndata로 만들어야 함
        val cv_un_dataList = (mergeLists(cvadressList, cvphoneList, cvmemoList)).toMutableList()

        // 어댑터 설정
        recyclerView = findViewById(R.id.cv_recyclerview)
        adapter = CVadapter(this)
        recyclerView.adapter = adapter

        Log.d("","Json : 데이터 : " + cv_un_dataList)

        // 데이터를 RecyclerView에 추가하고 어댑터에 알림
        adapter.datas = cv_un_dataList
        adapter.notifyDataSetChanged()
    }

    // Address,
    fun mergeLists(add_list: List<AdressData>, phone_list: List<PhoneNumberData>, memo_list: List<MemoData>): ArrayList<Syndata> {
        val mergedListSize = add_list.size + phone_list.size + memo_list.size
        var mergedList = ArrayList<Syndata>(mergedListSize)

        for (i in 0..(mergedListSize-1)){
            mergedList.add(Syndata("", ""))
        }



        for(i in 0..(add_list.size -1)){
            mergedList = insert_AtIndex(mergedList, add_list[i].order, add_list[i].adress, "주소")
        }
        for(i in 0..(phone_list.size-1)){
            mergedList = insert_AtIndex(mergedList, phone_list[i].order, phone_list[i].phoneNumber, "연략처")
        }
        for(i in 0..(memo_list.size-1)){
            mergedList = insert_AtIndex(mergedList, memo_list[i].order, memo_list[i].memo, "메세지")
        }

        return mergedList
    }

    fun insert_AtIndex(list: ArrayList<Syndata>, index: Int, element: String, type : String) : ArrayList<Syndata> {
        if (index >= 0 && index <= list.size) {
            list[index].data = element
            list[index].data_type = type
        } else {
            throw IndexOutOfBoundsException("Index $index is out of bounds for MutableList of size ${list.size}")
        }
        return list;
    }
}