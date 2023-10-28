package com.example.child_helper

import com.google.gson.annotations.SerializedName


class PersonRequest {
    @SerializedName("CustomID")
    var customID: String? = null

    @SerializedName("Fingers")
    var fingers: List<FingerPersonRequest>? = null

    constructor()
    constructor(customID: String?, fingers: List<FingerPersonRequest>?) {
        this.customID = customID
        this.fingers = fingers
    }
}