package com.example.child_helper

import com.google.gson.annotations.SerializedName


class PersonResponse {
    @SerializedName("ClientID")
    var clientID: String? = null

    @SerializedName("CustomID")
    var customID: String? = null

    @SerializedName("BioPassID")
    var bioPassID: String? = null

    constructor()
    constructor(clientID: String?, customID: String?, bioPassID: String?) {
        this.clientID = clientID
        this.customID = customID
        this.bioPassID = bioPassID
    }
}