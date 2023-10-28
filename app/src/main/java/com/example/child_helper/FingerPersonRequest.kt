package com.example.child_helper

import com.google.gson.annotations.SerializedName

class FingerPersonRequest {
    @SerializedName("Finger-1")
    var finger1: String? = null

    @SerializedName("Finger-2")
    var finger2: String? = null

    @SerializedName("Finger-3")
    var finger3: String? = null

    @SerializedName("Finger-4")
    var finger4: String? = null

    constructor()
    constructor(finger1: String?, finger2: String?, finger3: String?, finger4: String?) {
        this.finger1 = finger1
        this.finger2 = finger2
        this.finger3 = finger3
        this.finger4 = finger4
    }
}