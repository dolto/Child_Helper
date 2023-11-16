//package com.example.child_helper.Data
//import java.lang.StringBuilder
//import java.sql.Connection
//
//data class NameData (var id: Int, val name: String, val profile: String)
//
//data class FingerData(
//    var id: Int, val finger1: String, val finger2: String, val finger3: String, val finger4: String
//)
//data class AdressData(
//    val adress: String
//)
//data class PhoneNumberData(
//    val phoneNumber: String
//)
//data class MemoData(
//    val memo: String
//)
//data class Profile(
//    var id: Int,
//    val nameDB: NameData, val fingerDB: FingerData,
//    val adressDB: MutableList<AdressData>,
//    val phoneNumberDB: MutableList<PhoneNumberData>,
//    val memoDB: MutableList<MemoData>
//)
//fun getProfile(database: Connection, id: Int):Profile{
//    val finger = getFingerData(database, id)
//    val name = getNameData(database,id)
//    val adress = getAdressData(database,id)
//    val phoneNumber = getPhoneNumberData(database, id)
//    val memo = getMemoData(database, id)
//
//    return Profile(id, name!!, finger!!, adress, phoneNumber, memo)
//}