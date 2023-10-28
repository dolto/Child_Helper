package com.example.child_helper

import com.google.gson.annotations.SerializedName


class EnrollPersonResponse {
    @SerializedName("Person")
    var person: PersonResponse? = null

    @SerializedName("Message")
    var message: String? = null

    constructor()
    constructor(person: PersonResponse?, message: String?) {
        this.person = person
        this.message = message
    }
}