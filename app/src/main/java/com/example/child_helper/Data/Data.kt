package com.example.child_helper.Data

import android.util.Log
import com.google.gson.Gson
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


val gson = Gson()


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