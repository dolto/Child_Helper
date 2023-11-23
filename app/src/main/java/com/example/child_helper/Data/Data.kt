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
    val adress: String
)

@Serializable
data class PhoneNumberData(
    val phoneNumber: String
)

@Serializable
data class MemoData(
    val memo: String
)

@Serializable
data class Profile(
    val id: Int,
    val nameDB: NameData, val fingerDB: FingerData,
    val adressDB: MutableList<AdressData>,
    val phoneNumberDB: MutableList<PhoneNumberData>,
    val memoDB: MutableList<MemoData>
)
var singlethon_profile = Profile;
fun convertProflieListToJson(list: Profile): String {

    Log.d("태그", "Json 처리 중 직접처리3")
    return Json.encodeToString(list)
}

class Data {
}