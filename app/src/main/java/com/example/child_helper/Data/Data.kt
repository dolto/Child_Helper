package com.example.child_helper.Data

import android.util.Log
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class NameData(
    var id: Int, val name: String, val profile: String
)

@Serializable
data class FingerData(
    var id: Int, val finger1: String, val finger2: String, val finger3: String, val finger4: String
)

@Serializable
data class AdressData(
    val adress: String, val order : Int
)

@Serializable
data class PhoneNumberData(
    val phoneNumber: String, val order : Int
)

@Serializable
data class MemoData(
    val memo: String, val order : Int
)

@Serializable
data class Syndata(
    var data: String, var data_type : String
)

@Serializable
data class Profile(
    val id: Int,
    val nameDB: NameData, val fingerDB: FingerData,
    val adressDB: MutableList<AdressData>,
    val phoneNumberDB: MutableList<PhoneNumberData>,
    val memoDB: MutableList<MemoData>
)



@Serializable
data class Profile_regi_fin(
    val fingerDB: FingerData
)

fun convertProflieListToJson(list: Profile): String {

    return Json.encodeToString(list)
}

fun convertJsonToProfileList(jsonString: String): Profile {
    Log.d("","Json을 List로 변경하는 함수 진입")
    return Json.decodeFromString(jsonString)
    Log.d("","Json을 List로 변경 완료")
}

object Finger_singletone{
    var finger1: String =""
    var finger2: String =""
    var finger3: String =""
    var finger4: String =""
}

object test_json{
    var test1 : String = ""
}

class Data {
}