package com.example.child_helper

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface BioPassIDApi {
    @Headers("Content-Type: application/json", "Ocp-Apim-Subscription-Key: H4JF-U4XA-BXSJ-XHFI")
    @POST("multibiometrics/enroll")
    fun enrollPerson(@Body enrollPersonRequest: EnrollPersonRequest?): Call<EnrollPersonResponse?>?
}