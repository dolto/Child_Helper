package com.example.child_helper

import com.google.gson.annotations.SerializedName


class EnrollPersonRequest {
    @SerializedName("Person")
    var person: PersonRequest? = null

    constructor()
    constructor(person: PersonRequest?) {
        this.person = person
    }
}