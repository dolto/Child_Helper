package com.example.child_helper.Data

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import kotlinx.serialization.Serializable
import java.lang.reflect.Type


val gson = Gson()


@Serializable
data class NameData(
    var id: Int, val name: String, val UserID :String, val profile: String
)

@Serializable
data class FingerData(
    var id: Int, val finger1: String, val finger2: String, val finger3: String, val finger4: String
)

@Serializable
data class AdressData(
    val adress: String, val reg_order : Int
)

@Serializable
data class PhoneNumberData(
    val phoneNumber: String, val reg_order : Int
)

@Serializable
data class MemoData(
    val memo: String, val reg_order : Int
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
    return gson.toJson(list)
}

fun convertJsonToProfileList(jsonString: String): Profile {
    var profile_data :Profile = gson.fromJson(jsonString, Profile::class.java)
    return profile_data
}

fun jsonToMutableList(json: String): List<NameData> {
    val gson = Gson()
    val type: Type = object : TypeToken<List<NameData>>() {}.type
    return gson.fromJson(json, type)
}

fun stringToJson(str: String): JsonObject {
    val gson = Gson()
    return gson.fromJson(str, JsonObject::class.java)
}

fun convertFingerListToJson(list: FingerData): String {
    return gson.toJson(list)
}

fun convertJsonToFingerList(jsonString: String): FingerData {
    var profile_data :FingerData = gson.fromJson(jsonString, FingerData::class.java)
    return profile_data
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

object profile_id{
    var id : Int = 0
}

class Data {
}

data class LoginData(val username: String, val password: String)

interface OnButtonClickListener {
    fun onButtonClick(buttonText: String, position: Int)
}